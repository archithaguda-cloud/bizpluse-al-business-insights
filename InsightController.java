package com.bizpulse.controller;
import com.bizpulse.dto.DashboardResponse.AskAiRequest;
import com.bizpulse.dto.DashboardResponse.AskAiResponse;
import com.bizpulse.entity.BusinessInsight;
import com.bizpulse.service.InsightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/insights")
@CrossOrigin(origins = "*")
public class InsightController {
    @Autowired
    private InsightService insightService;
    @GetMapping
    public ResponseEntity<List<BusinessInsight>> getInsights() {
        return ResponseEntity.ok(insightService.getAllInsights());
    }
    @PostMapping("/{id}/apply")
    public ResponseEntity<?> applyInsight(@PathVariable String id) {
        try {
            BusinessInsight updated = insightService.applyInsight(id);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
    @PostMapping("/ask")
    public ResponseEntity<AskAiResponse> askAI(@RequestBody AskAiRequest request) {
        return ResponseEntity.ok(insightService.askAI(request));
    }
}