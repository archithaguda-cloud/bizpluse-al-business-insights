package com.bizpulse.repository;
import com.bizpulse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByCategory(String category);
    @Query("SELECT COUNT(p) FROM Product p WHERE p.status = 'Low Stock' OR p.status = 'Critical Alert'")
    long countLowStockProducts();
    @Query("SELECT COALESCE(SUM(p.revenue), 0.0) FROM Product p")
    Double sumTotalRevenue();
}