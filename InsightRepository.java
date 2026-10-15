package com.bizpulse.repository;
import com.bizpulse.entity.BusinessInsight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface InsightRepository extends JpaRepository<BusinessInsight, String> {
    List<BusinessInsight> findByCategory(String category);
    List<BusinessInsight> findAllByOrderByConfidenceDesc();
}