package com.akriskovets.dao;

import com.akriskovets.model.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAll() {
        String sql = "SELECT employee.id, employee.name, position.name position FROM employee LEFT JOIN position ON position.id = employee.position_id";
        List<Employee> listEmployee = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Employee.class));
        return listEmployee;
    }

    public void save(String employeeName, Long positionId) {
        String sql = "INSERT INTO employee (name, position_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, employeeName, positionId);
    }

    public Employee get(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        Object[] args = {id};
        Employee employee = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    public void update(Employee employee) {
        String sql = "UPDATE employee SET name=? WHERE id=?";
        jdbcTemplate.update(sql, employee.getName(), employee.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

}
