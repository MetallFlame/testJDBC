package com.akriskovets.controller;

import com.akriskovets.dao.PositionDao;
import com.akriskovets.model.Position;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/position")
public class PositionController {

    private final PositionDao positionDao;

    public PositionController(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @PostMapping("/update")
    public String updatePosition(Model model, @RequestParam Position position) {
        positionDao.update(position);
        model.addAttribute("message", "position updated!");
        return "message";
    }

    @PostMapping("/delete")
    public String deletePosition(Model model, @RequestParam Integer positionId) {
        positionDao.delete(positionId);
        model.addAttribute("message", "position deleted!");
        return "message";
    }

    @PostMapping("/create")
    public String createPosition(Model model, @RequestParam String positionName) {
        positionDao.save(positionName);
        model.addAttribute("message", "position created!");
        return "message";
    }

    @GetMapping("")
    public String positionList(Model model) {
        model.addAttribute("positionList", positionDao.list());
        return "position";
    }
}
