package com.bizpulse.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "business_insights")
public class BusinessInsight {
    @Id
    @Column(length = 50)
    private String id;
    @Column(nullable = false, length = 50)
    private String type;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private Integer confidence;
    @Column(nullable = false, length = 100)
    private String impact;
    @Column(nullable = false, length = 100)
    private String category;
    @Column(length = 50)
    private String urgency;
    @Column(name = "action_text", nullable = false, length = 150)
    private String actionText;
    @Column(name = "is_applied")
    private Boolean isApplied;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    public BusinessInsight() {
        this.isApplied = false;
        this.createdAt = LocalDateTime.now();
    }
    public BusinessInsight(String id, String type, String title, String description, Integer confidence, String impact, String category, String urgency, String actionText) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.confidence = confidence;
        this.impact = impact;
        this.category = category;
        this.urgency = urgency;
        this.actionText = actionText;
        this.isApplied = false;
        this.createdAt = LocalDateTime.now();
        
        public String getImpact() {
        return impact;
    }
    public void setImpact(String impact) {
        this.impact = impact;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getUrgency() {
        return urgency;
    }
    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }
    public String getActionText() {
        return actionText;
    }
    public void setActionText(String actionText) {
        this.actionText = actionText;
    }
    public Boolean getIsApplied() {
        return isApplied;
    }
    public void setIsApplied(Boolean isApplied) {
        this.isApplied = isApplied;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}