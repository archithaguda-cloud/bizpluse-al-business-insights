package com.bizpulse.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(length = 50)
    private String id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 100)
    private String category;
    @Column(nullable = false)
    private Double price;
    private Integer sales;
    private Double revenue;
    private Integer stock;
    @Column(length = 50)
    private String status;
    @Column(name = "ai_sentiment", length = 150)
    private String aiSentiment;
    @Column(length = 20)
    private String margin;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    public Product() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public Product(String id, String name, String category, Double price, Integer sales, Double revenue, Integer stock, String status, String aiSentiment, String margin) {
        this.id = id;
        this.name = name;