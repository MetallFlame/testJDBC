package com.akriskovets.dao;

import com.akriskovets.model.Project;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDao {

    private final JdbcTemplate jdbcTemplate;

    public ProjectDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Project> list() {
        String sql = "SELECT project.id, project.name, customer.name customer FROM project LEFT JOIN customer ON customer.id = project.customer_id";
        List<Project> listProject = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Project.class));
        return listProject;
    }

    public void save(String projectName, Long customerId) {
        String sql = "INSERT INTO project (name, customer_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, projectName, customerId);
    }

    public Project get(int id) {
        String sql = "SELECT * FROM project WHERE id = ?";
        Object[] args = {id};
        Project project = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Project.class));
        return project;
    }

    public void update(Project project) {
        String sql = "UPDATE project SET name=? WHERE id=?";
        jdbcTemplate.update(sql, project.getName(), project.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM project WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
