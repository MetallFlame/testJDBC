package com.akriskovets.dao;

import com.akriskovets.model.Employee;
import com.akriskovets.model.EmployeeProject;
import com.akriskovets.model.FullInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ManagerDao {

    private final JdbcTemplate jdbcTemplate;
    private final PositionDao positionDao;
    private final EmployeeDao employeeDao;
    private final CustomerDao customerDao;
    private final ProjectDao projectDao;

    public ManagerDao(JdbcTemplate jdbcTemplate, PositionDao positionDao, EmployeeDao employeeDao, CustomerDao customerDao, ProjectDao projectDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.positionDao = positionDao;
        this.employeeDao = employeeDao;
        this.customerDao = customerDao;

        this.projectDao = projectDao;
    }

    public void clearFields() {
        String sql = "TRUNCATE employee, project, position, employee_project, customer RESTART IDENTITY";
        jdbcTemplate.execute(sql);
    }

    public void fillTables() {
        positionDao.save("junior developer");
        positionDao.save("middle developer");
        positionDao.save("senior developer");
        positionDao.save("manager");

        String[] names = new String[] {"Alex", "John", "Sofia", "Geller", "Mike", "Vladimir", "Tony", "Frank"};

        for(String name: names) {
            employeeDao.save(name, (long) (Math.random()*4 + 1));
        }

        customerDao.save("Google");
        customerDao.save("Yandex");
        customerDao.save("Tesla");
        customerDao.save("Microsoft");

        String[] projects = new String[] {"Search engine", "Calculator", "Browser", "File manager", "Monitor driver", "Photo editor", "Music player", "IDE"};

        for(String project: projects) {
            projectDao.save(project, (long) (Math.random()*4 + 1));
        }

        Set<EmployeeProject> connectionTable = new HashSet<>();

        while(connectionTable.size() < 35) {
            connectionTable.add(new EmployeeProject((long) (Math.random()*8 + 1), (long) (Math.random()*8 + 1)));
        }

        for(EmployeeProject currentConnection: connectionTable) {
            String sql = "INSERT INTO employee_project (employee_id, project_id) VALUES (?, ?)";
            jdbcTemplate.update(sql, currentConnection.getEmployeeId(), currentConnection.getProjectId());
        }

    }

    public void addEmployeeAndPosition() {
        String sql = "INSERT INTO position (name) VALUES ('Body guard'); INSERT INTO employee (name, position_id) VALUES ('Vasya', (SELECT MAX(id) FROM position));";
        jdbcTemplate.execute(sql);
    }

    public void addCustomerAndProject() {
        String sql = "INSERT INTO customer (name) VALUES ('Gasprom'); INSERT INTO project (name, customer_id) VALUES ('Terminal', (SELECT MAX(id) FROM customer));";
        jdbcTemplate.execute(sql);
    }

    public List<FullInfo> getFullInfo() {
        String sql = "SELECT employee.name AS employee, position.name AS position, project.name AS project, customer.name AS customer " +
                "FROM employee_project " +
                "INNER JOIN employee ON employee_project.employee_id = employee.id " +
                "INNER JOIN project ON employee_project.project_id = project.id " +
                "INNER JOIN customer ON project.customer_id = customer.id " +
                "INNER JOIN position ON employee.position_id = position.id;";
        List<FullInfo> fullInfo = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(FullInfo.class));
        return fullInfo;
    }
}
