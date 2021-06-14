package ru.job4j.accident.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
@Profile("jdbc")
public class JdbcAccidentRepository implements AccidentRepository {

    private final JdbcTemplate jdbcTemplate;
    private final AccidentTypeRepository accidentTypeRepository;

    public JdbcAccidentRepository(JdbcTemplate jdbcTemplate, AccidentTypeRepository accidentTypeRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.accidentTypeRepository = accidentTypeRepository;
    }

    @Override
    public Accident create(Accident model) {
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("insert into accident (name, text, address, accident_type_id)" +
                " values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, model.getName());
                ps.setString(2, model.getText());
                ps.setString(3, model.getAddress());
                ps.setInt(4, model.getAccidentType().getId());
                return ps;
                }, keyHolder);
        model.setId((Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
        addRulesByAccidentId(model.getRules(), model.getId());
        return model;
    }

    @Override
    public Accident read(Class<Accident> type, Integer id) {
        return jdbcTemplate.query("select accident_id, text, accident_name, address, accident_type_id," +
                        " rule_id, rule_name from (select a.id as accident_id, a.text, a.name as accident_name," +
                        " a.address, accident_type_id, r.id as rule_id, r.name as rule_name from accident a" +
                        " left join accident_rule a_r on a.id = a_r.accident_id" +
                        " left join rule r on a_r.rule_id = r.id) as full_accident" +
                        " where accident_id = ?", ps -> {
                    ps.setInt(1, id);
                }, rs -> {
                    return createAccidentList(rs).get(0);
                }
                );
    }

    @Override
    public void update(Accident model) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("update accident set (name, text, address, accident_type_id)" +
                    " = (?, ?, ?, ?) where id = ?");
            ps.setString(1, model.getName());
            ps.setString(2, model.getText());
            ps.setString(3, model.getAddress());
            ps.setInt(4, model.getAccidentType().getId());
            ps.setInt(5, model.getId());
            return ps;
        });
        clearRulesByAccidentId(model.getId());
        addRulesByAccidentId(model.getRules(), model.getId());
    }

    @Override
    public void delete(Accident model) {
        jdbcTemplate.update("delete from accident where id = ?", model.getId());
        clearRulesByAccidentId(model.getId());
    }

    @Override
    public List<Accident> findAll(Class<Accident> type) {
        return jdbcTemplate.query("select a.id as accident_id, a.text, a.name as accident_name," +
                        " a.address, accident_type_id, r.id as rule_id, r.name as rule_name from accident a" +
                        " left join accident_rule a_r on a.id = a_r.accident_id" +
                        " left join rule r on a_r.rule_id = r.id", this::createAccidentList
        );
    }

    private List<Accident> createAccidentList(ResultSet rs) throws SQLException {
        var rslList = new ArrayList<Accident>();
        Accident currentAccident = new Accident();
        currentAccident.setId(0);
        while(rs.next()) {
            if (currentAccident.getId() != rs.getInt("accident_id")) {
                Accident accident = new Accident();
                accident.setId(rs.getInt("accident_id"));
                accident.setName(rs.getString("accident_name"));
                accident.setText(rs.getString("text"));
                accident.setAddress(rs.getString("address"));
                accident.setAccidentType(accidentTypeRepository.read(AccidentType.class,
                        rs.getInt("accident_type_id")));
                var rule = new Rule();
                rule.setId(rs.getInt("rule_id"));
                rule.setName(rs.getString("rule_name"));
                accident.getRules().add(rule);
                rslList.add(accident);
                currentAccident = accident;
            } else {
                var rule = new Rule();
                rule.setId(rs.getInt("rule_id"));
                rule.setName(rs.getString("rule_name"));
                currentAccident.getRules().add(rule);
            }
        }
        return rslList;
    }

    private void addRulesByAccidentId(Set<Rule> rules, Integer accidentId) {
        for(Rule rule : rules){
            jdbcTemplate.update("insert into accident_rule (accident_id, rule_id)" +
                    " values (?, ?)", accidentId, rule.getId());
        }
    }

    private void clearRulesByAccidentId(Integer accidentId) {
        jdbcTemplate.update("delete from accident_rule where accident_id = ?", accidentId);
    }
}
