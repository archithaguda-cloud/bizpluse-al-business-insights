package com.bizpulse.service;
import com.bizpulse.dto.DashboardResponse.AskAiRequest;
import com.bizpulse.dto.DashboardResponse.AskAiResponse;
import com.bizpulse.entity.BusinessInsight;
import com.bizpulse.repository.InsightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class InsightService {
    @Autowired
    private InsightRepository insightRepository;
    public List<BusinessInsight> getAllInsights() {
        List<BusinessInsight> list = insightRepository.findAllByOrderByConfidenceDesc();
        if (list.isEmpty()) {
            seedDefaultInsights();
            list = insightRepository.findAllByOrderByConfidenceDesc();
        }
        return list;
    }
    public BusinessInsight applyInsight(String id) {
        BusinessInsight insight = insightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insight not found: " + id));
        insight.setIsApplied(true);
        return insightRepository.save(insight);
    }
    public AskAiResponse askAI(AskAiRequest request) {
        String query = request.getQuery() != null ? request.getQuery() : "";
        String lower = query.toLowerCase();
        String answer;
        int confidence = 94;
        List<String> actions = new ArrayList<>();
        if (lower.contains("stock") || lower.contains("inventory")) {
            answer = "Inventory telemetry flags critical burn rate on 'AeroNoise ANC Headphones' (12 units remaining). At current sales velocity, stock out occurs in ~18 hours. Immediate supplier reorder of 150 units will preserve $7,900 weekend gross sales.";
            confidence = 98;
            actions.add("Transmit automated PO #492 to Audio OEM Supplier");
            actions.add("Set dynamic safety stock buffer from 10 to 35 units");
            actions.add("Enable pre-order checkout with 3-day delivery ETA");
        } else if (lower.contains("price") || lower.contains("discount") || lower.contains("elasticity")) {
            answer = "Elasticity analysis indicates positive margin sensitivity on 'BioSensor Vertical Mouse'. Price can be increased from $69.99 to $76.99 without dropping conversion below 98% of baseline, generating +$5,880 recurring monthly margin.";
            confidence = 95;
            actions.add("Update SKU price tier in Shopify & Stripe");
            actions.add("Initiate 7-day A/B split price test");
            actions.add("Track checkout drop-off rate via telemetry");
        } else {
            answer = "Neural synthesis for query: \"" + query + "\". Enterprise data shows Q4 momentum is driven by 43% cross-sell affinity between Docking Stations and Smart Hubs. Bundling complementary accessories will lift Average Order Value by $38.40.";
            confidence = 92;
            actions.add("Deploy 1-click bundle widget to high-traffic product pages");
            actions.add("Trigger personalized email campaign to recent electronics purchasers");
            actions.add("Align warehouse shipping batches for paired SKUs");
        }
        return new AskAiResponse(answer, confidence, actions);
    }
    private void seedDefaultInsights() {
        insightRepository.saveAll(List.of(
                new BusinessInsight("INS-101", "growth", "Dynamic Pricing Recommendation for BioSensor Mouse",
                        "Demand elasticity analysis indicates customers are insensitive to a $7 price increase. Increasing price from $69.99 to $76.99 could yield an additional $5,880 monthly profit with < 2% volume impact.",
                        96, "+$5,880 / month", "Revenue Optimization", "Medium", "Update Price via Catalog"),
                new BusinessInsight("INS-102", "alert", "Imminent Stockout Risk: AeroNoise ANC Headphones",
                        "Current burn rate is 22 units/day with only 12 units remaining. At this velocity, stock out occurs in under 18 hours, risking ~$7,900 in weekend lost sales.",
                        99, "$7,900 Loss Prevention", "Supply Chain AI", "High", "Trigger Emergency Reorder (150 Units)"),
                new BusinessInsight("INS-103", "retention", "Cart Abandonment Spike on High-Tier Office Furniture",
                        "Checkout exit rates rose by 14% on orders above $600. Checkout telemetry flags unexpected shipping charges at the payment step. Recommend testing tiered free shipping.",
                        91, "+4.2% Checkout Conversion", "Customer Behavior", "Medium", "Activate Free Freight Threshold A/B Test"),
                new BusinessInsight("INS-104", "opportunity", "Cross-Sell Bundle Affinity: Thunderbolt Dock + Smart Hub",
                        "Machine learning market-basket analysis identified 43% co-purchase intent between TitanCore Dock and OmniDesk Hub. Creating a 10% off bundle could lift AOV by $38.",
                        88, "+12% Average Order Value", "Merchandising AI", "Low", "Create 1-Click Bundle")
        ));
    }
}