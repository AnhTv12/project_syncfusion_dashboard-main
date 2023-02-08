package com.sergio.auth.backend.resources.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", length = 8)
    private int customerId;

    @Column(name = "customers", length = 30 , nullable = false)
    private String customerName;

    @Column(name = "email", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "avatar", length = 100)
    private String avatarPath;

    @Column(name = "active_day", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Calendar activeDay;

    @Column(name = "phone",length = 20)
    private String phone;
    @ManyToMany()
    @JoinTable(
            name = "customer_registration",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    @ToString.Exclude
    private List<Service> services;

    @OneToMany(mappedBy = "customers")
    @ToString.Exclude
    private List<Order> orders;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    private Branch branch;

}
