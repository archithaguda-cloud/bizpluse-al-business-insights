package com.bizpulse.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, unique = true, length = 150)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(length = 50)
    private String role;
    @Column(length = 150)
    private String company;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    public User() {
        this.createdAt = LocalDateTime.now();
    }
    public User(String name, String email, String password, String role, String company) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.company = company;
        this.createdAt = LocalDateTime.now();
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;