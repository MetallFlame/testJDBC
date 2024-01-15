package com.akriskovets.dao;

import com.akriskovets.model.Position;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionDao {

    private final JdbcTemplate jdbcTemplate;

    public PositionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Position> list() {
        String sql = "SELECT * FROM position";
        List<Position> listPosition = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Position.class));
        return listPosition;
    }

    public void save(String positionName) {
        String sql = "INSERT INTO position (name) VALUES (?)";
        jdbcTemplate.update(sql, positionName);
    }

    public Position get(int id) {
        String sql = "SELECT * FROM position WHERE id = ?";
        Object[] args = {id};
        Position position = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Position.class));
        return position;
    }

    public void update(Position position) {
        String sql = "UPDATE position SET name=? WHERE id=?";
        jdbcTemplate.update(sql, position.getName(), position.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM position WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
