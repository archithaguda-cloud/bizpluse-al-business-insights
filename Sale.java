package com.bizpulse.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id", nullable = false, unique = true, length = 50)
    private String orderId;
    @Column(name = "client_name", nullable = false, length = 150)
    private String clientName;
    @Column(name = "items_description", nullable = false, length = 255)
    private String itemsDescription;
    @Column(nullable = false)
    private Double amount;
    @Column(length = 50)
    private String status;
    @Column(length = 100)
    private String channel;
    @Column(name = "sale_date")
    private LocalDateTime saleDate;
    public Sale() {
        this.saleDate = LocalDateTime.now();
    }
    public Sale(String orderId, String clientName, String itemsDescription, Double amount, String status, String channel) {
        this.orderId = orderId;
        this.clientName = clientName;
        this.itemsDescription = itemsDescription;
        this.amount = amount;
        this.status = status;
        this.channel = channel;
        this.saleDate = LocalDateTime.now();
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    public String getItemsDescription() {
        return itemsDescription;
    }
    public void setItemsDescription(String itemsDescription) {
        this.itemsDescription = itemsDescription;
    }

    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }

     public void setChannel(String channel) {
        this.channel = channel;
    }
    public LocalDateTime getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }
