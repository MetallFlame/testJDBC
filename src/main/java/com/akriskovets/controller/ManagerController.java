package com.akriskovets.controller;

import com.akriskovets.dao.ManagerDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerDao managerDao;

    public ManagerController(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    @GetMapping("/clear")
    public String clearFields(Model model) {
        model.addAttribute("message", "DB cleared!");
        managerDao.clearFields();
        return "message";
    }

    @GetMapping("/fill_db")
    public String fillTablesWithRandomData(Model model) {
        model.addAttribute("message", "DB filled with random data!");
        managerDao.fillTables();
        return "message";
    }

    @GetMapping("/employee_position")
    public String addEmployeeAndPosition(Model model) {
        model.addAttribute("message", "Employee and position added!");
        managerDao.addEmployeeAndPosition();
        return "message";
    }


    @GetMapping("/project_customer")
    public String addCustomerAndProject(Model model) {
        model.addAttribute("message", "Customer and project added!");
        managerDao.addCustomerAndProject();
        return "message";
    }

    @GetMapping("/full_info")
    public String fullInfo(Model model) {
        model.addAttribute("fullInfoList", managerDao.getFullInfo());
        return "full_info";
    }
}
