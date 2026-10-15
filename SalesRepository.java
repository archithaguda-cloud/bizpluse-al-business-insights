package com.bizpulse.repository;
import com.bizpulse.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {
    List<Sale> findAllByOrderBySaleDateDesc();
    @Query("SELECT COALESCE(SUM(s.amount), 0.0) FROM Sale s")
    Double sumTotalSalesAmount();
    @Query("SELECT COUNT(s) FROM Sale s")
    long countTotalOrders();
}