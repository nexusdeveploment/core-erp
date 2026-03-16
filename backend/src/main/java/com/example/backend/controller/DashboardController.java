package com.example.backend.controller;

import com.example.backend.dto.DashboardDTO;
import com.example.backend.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardDTO obterDashboard() {
        return dashboardService.obterDadosDashboard();
    }
}
