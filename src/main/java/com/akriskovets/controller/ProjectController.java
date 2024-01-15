package com.akriskovets.controller;

import com.akriskovets.dao.ProjectDao;
import com.akriskovets.model.Employee;
import com.akriskovets.model.Position;
import com.akriskovets.model.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectDao projectDao;


    public ProjectController(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }


    @GetMapping("")
    public String test(Model model) {
        List<Project> listProject = projectDao.list();
        model.addAttribute("listProject", listProject);
        return "project";
    }

    @PostMapping("/update")
    public String updateProject(Model model, @RequestParam Project project) {
        projectDao.update(project);
        model.addAttribute("message", "project updated!");
        return "message";
    }

    @PostMapping("/delete")
    public String deleteProject(Model model, @RequestParam Integer projectId) {
        projectDao.delete(projectId);
        model.addAttribute("message", "project deleted!");
        return "message";
    }

    @PostMapping("/create")
    public String createProject(Model model, @RequestParam String projectName, @RequestParam Long customerId) {
        projectDao.save(projectName, customerId);
        model.addAttribute("message", "project created!");
        return "message";
    }
}
