package com.akriskovets.controller;

import com.akriskovets.dao.EmployeeDao;
import com.akriskovets.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeDao employeeDao;

    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @GetMapping("")
    public String employeeList(Model model) {
        model.addAttribute("listEmployee", employeeDao.getAll());
        return "employee";
    }

    @PostMapping("/update")
    public String updateEmployee(Model model, @RequestParam Employee employee) {
        employeeDao.update(employee);
        model.addAttribute("message", "employee updated!");
        return "message";
    }

    @PostMapping("/delete")
    public String deleteEmployee(Model model, @RequestParam Integer employeeId) {
        employeeDao.delete(employeeId);
        model.addAttribute("message", "employee deleted!");
        return "message";
    }

    @PostMapping("/create")
    public String createPosition(Model model, @RequestParam String employeeName, @RequestParam Long positionId) {
        employeeDao.save(employeeName, positionId);
        model.addAttribute("message", "employee created!");
        return "message";
    }

}
