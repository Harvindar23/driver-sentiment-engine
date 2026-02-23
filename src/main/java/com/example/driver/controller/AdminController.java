package com.example.driver.controller;

import com.example.driver.repository.AlertRepository;
import org.springframework.stereotype.Controller;
import com.example.driver.repository.DriverRepository;
import com.example.driver.repository.AlertRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DriverRepository driverRepository;
    private final AlertRepository alertRepository;

    public AdminController(DriverRepository driverRepository,
                           AlertRepository alertRepository) {
        this.driverRepository = driverRepository;
        this.alertRepository = alertRepository;
    }

    @GetMapping("/page")
    public String showAdminPage(Model model) {
        model.addAttribute("drivers", driverRepository.findAll());
        model.addAttribute("alerts", alertRepository.findAll());
        return "admin";
    }
}