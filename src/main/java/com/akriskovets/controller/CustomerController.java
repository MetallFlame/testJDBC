package com.akriskovets.controller;

import com.akriskovets.dao.CustomerDao;
import com.akriskovets.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerDao customerDao;

    public CustomerController(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @PostMapping("/update")
    public String updateCustomer(Model model, @RequestParam Customer customer) {
        customerDao.update(customer);
        model.addAttribute("message", "customer updated!");
        return "message";
    }

    @PostMapping("/delete")
    public String deleteCustomer(Model model, @RequestParam Integer customerId) {
        customerDao.delete(customerId);
        model.addAttribute("message", "customer deleted!");
        return "message";
    }

    @PostMapping("/create")
    public String createCustomer(Model model, @RequestParam String customerName) {
        customerDao.save(customerName);
        model.addAttribute("message", "customer created!");
        return "message";
    }

    @GetMapping("")
    public String customerList(Model model) {
        model.addAttribute("customerList", customerDao.list());
        return "customer";
    }
}
