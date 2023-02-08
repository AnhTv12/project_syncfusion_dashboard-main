package com.sergio.auth.backend.resources.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "branch")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id", length = 5)
    private int branchId;

    @Column(name = "branch_name", length = 50, nullable = false)
    private String branchName;

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @Column(name = "manager", length = 25, nullable = false)
    private String manager;

    @Column(name = "customers")
    @OneToMany(mappedBy = "branch")
    @ToString.Exclude
    private List<Customers> customers;

    @Column(name = "employees")
    @OneToMany(mappedBy = "branch")
    @ToString.Exclude
    private List<Employees> employees;

    @Column(name = "orders")
    @OneToMany(mappedBy = "branch")
    @ToString.Exclude
    private List<Order> orders;

}