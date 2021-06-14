package ru.job4j.accident.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
@Profile("jdbc")
public class JdbcRuleRepository implements RuleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRuleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Rule create(Rule model) {
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into rule (name)" +
                    " values (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, model.getName());
            return ps;
        }, keyHolder);
        model.setId((Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
        return model;
    }

    @Override
    public Rule read(Class<Rule> type, Integer id) {
        return jdbcTemplate.query("select id, name from rule" +
                        " where id = ?", ps -> {
                    ps.setInt(1, id);
                }, rs -> {
                    rs.next();
                    var rule = new Rule();
                    rule.setId(id);
                    rule.setName(rs.getString("name"));
                    return rule;
                }
        );
    }

    @Override
    public void update(Rule model) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("update rule set name = ?" +
                    " where id = ?");
            ps.setString(1, model.getName());
            ps.setInt(2, model.getId());
            return ps;
        });
    }

    @Override
    public void delete(Rule model) {
        jdbcTemplate.update("delete from rule where id = ?", model.getId());
    }

    @Override
    public List<Rule> findAll(Class<Rule> type) {
        return jdbcTemplate.query("select id, name from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }
}
