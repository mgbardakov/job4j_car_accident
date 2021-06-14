package ru.job4j.accident.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
@Profile("jdbc")
public class JdbcAccidentTypeRepository implements AccidentTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcAccidentTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public AccidentType create(AccidentType model) {
        var keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into accident_type (name)" +
                    " values (?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, model.getName());
            return ps;
        }, keyHolder);
        model.setId((Integer) Objects.requireNonNull(keyHolder.getKeys()).get("id"));
        return model;
    }

    @Override
    public AccidentType read(Class<AccidentType> type, Integer id) {
        return jdbcTemplate.query("select id, name from accident_type" +
                        " where id = ?", ps -> {
                    ps.setInt(1, id);
                }, rs -> {
                    rs.next();
                    var accidentType = new AccidentType();
                    accidentType.setId(id);
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                }
        );
    }

    @Override
    public void update(AccidentType model) {
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("update accident_type set name = ?" +
                    " where id = ?");
            ps.setString(1, model.getName());
            ps.setInt(2, model.getId());
            return ps;
        });
    }

    @Override
    public void delete(AccidentType model) {
        jdbcTemplate.update("delete from accident_type where id = ?", model.getId());
    }

    @Override
    public List<AccidentType> findAll(Class<AccidentType> type) {
        return jdbcTemplate.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }
}
