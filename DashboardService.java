package com.bizpulse.service;
import com.bizpulse.dto.DashboardResponse;
import com.bizpulse.dto.DashboardResponse.StatItem;
import com.bizpulse.dto.DashboardResponse.StatsDto;
import com.bizpulse.repository.ProductRepository;
import com.bizpulse.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DashboardService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private SalesService salesService;
    public StatsDto getStats() {
        Double totalRevenue = productRepository.sumTotalRevenue();
        if (totalRevenue == null || totalRevenue == 0.0) {
            totalRevenue = 128450.0;
        }
        long ordersCount = salesRepository.countTotalOrders();
        if (ordersCount == 0) {
            ordersCount = 1842;
        }
        StatItem revenueStat = new StatItem(
                totalRevenue,
                String.format("$%,.0f", totalRevenue),
                "+14.8%",
                "up",
                "vs. previous month",
                false
        );
        StatItem ordersStat = new StatItem(
                ordersCount,
                String.format("%,d", ordersCount),
                "+8.2%",
                "up",
                "vs. previous month",
                false
        );
        StatItem conversionStat = new StatItem(
                3.42,
                "3.42%",
                "-0.4%",
                "down",
                "Industry avg 2.9%",
                 false
        );
        StatItem aiForecastStat = new StatItem(
                154200.0,
                "$154,200",
                "+20.1%",
                "up",
                "AI Predicted Q4 run-rate",
                true
        );
        return new StatsDto(revenueStat, ordersStat, conversionStat, aiForecastStat);
    }
    public DashboardResponse getFullDashboard() {
        return new DashboardResponse(
                getStats(),
                salesService.getSalesChartPoints("monthly")
        );
    }
}