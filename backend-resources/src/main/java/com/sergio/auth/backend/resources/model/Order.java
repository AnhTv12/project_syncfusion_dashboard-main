package com.sergio.auth.backend.resources.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.util.Calendar;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_list")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", length = 50)
    private int orderId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    private Branch branch;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "service_id", referencedColumnName = "service_id")
    @ToString.Exclude
    private Service services;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ToString.Exclude
    private Customers customers;

    @Basic
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "order_time", length = 20)
    private Calendar orderedTime;

    @Column(name = "price", length = 11)
    private long price;

    @Column(name = "discount", length = 11)
    private long discount;

    @Column(name = "totalPrice", length = 11)
    private long totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id", referencedColumnName = "id")
    private Sales sales;

    public Order(Branch branch, Service services, Customers customers, Calendar orderedTime, long price, long discount, long totalPrice) {
        this.branch = branch;
        this.services = services;
        this.customers = customers;
        this.orderedTime = orderedTime;
        this.price = price;
        this.discount = discount;
        this.totalPrice = totalPrice;
    }
}
