package com.bizpulse.controller;
import com.bizpulse.dto.DashboardResponse;
import com.bizpulse.dto.DashboardResponse.ChartPointDto;
import com.bizpulse.dto.DashboardResponse.StatsDto;
import com.bizpulse.service.DashboardService;
import com.bizpulse.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private SalesService salesService;
    @GetMapping
    public ResponseEntity<DashboardResponse> getFullDashboard() {
        return ResponseEntity.ok(dashboardService.getFullDashboard());
    }
    @GetMapping("/stats")
    public ResponseEntity<StatsDto> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getStats());
    }
    @GetMapping("/sales-chart")
    public ResponseEntity<List<ChartPointDto>> getSalesChart(@RequestParam(defaultValue = "monthly") String timeframe) {
        return ResponseEntity.ok(salesService.getSalesChartPoints(timeframe));
    }
}