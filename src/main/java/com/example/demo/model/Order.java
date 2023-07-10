package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity @Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    private String orderNumber;

    @Column (nullable = false)
    private float orderAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column (nullable = false)
    private String date;

    @ManyToMany
    @JoinTable(name = "orders_books",
            joinColumns = @JoinColumn(name = "order_number"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
}
