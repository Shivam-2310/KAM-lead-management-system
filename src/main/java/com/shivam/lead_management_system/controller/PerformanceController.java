package com.shivam.lead_management_system.controller;

import com.shivam.lead_management_system.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @GetMapping("/analysis")
    public ResponseEntity<String> getPerformanceAnalysis() {
        String analysis = performanceService.generatePerformanceAnalysis();
        return ResponseEntity.ok(analysis);
    }
}
