package com.bizpulse.controller;
import com.bizpulse.dto.DashboardResponse.ChartPointDto;
import com.bizpulse.entity.Sale;
import com.bizpulse.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SalesController {
    @Autowired
    private SalesService salesService;
    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(salesService.getAllSales());
    }
    @GetMapping("/chart")
    public ResponseEntity<List<ChartPointDto>> getSalesChart(@RequestParam(defaultValue = "monthly") String timeframe) {
        return ResponseEntity.ok(salesService.getSalesChartPoints(timeframe));
    }
}