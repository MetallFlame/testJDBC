package com.akriskovets.dao;

import com.akriskovets.model.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> list() {
        String sql = "SELECT * FROM customer";
        List<Customer> listCustomer = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Customer.class));
        return listCustomer;
    }

    public void save(String customerName) {
        String sql = "INSERT INTO customer (name) VALUES (?)";
        jdbcTemplate.update(sql, customerName);
    }

    public Customer get(int id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        Object[] args = {id};
        Customer customer = jdbcTemplate.queryForObject(sql, args, BeanPropertyRowMapper.newInstance(Customer.class));
        return customer;
    }

    public void update(Customer customer) {
        String sql = "UPDATE customer SET name=? WHERE id=?";
        jdbcTemplate.update(sql, customer.getName(), customer.getId());
    }

    public void delete(int id) {
        String sql = "DELETE FROM customer WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
}
