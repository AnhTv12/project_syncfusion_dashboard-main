package com.sergio.auth.backend.resources.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", length = 5)
    private int serviceId;

    @Column(name = "product_name",length = 100)
    private String name;

    @Column(name = "description", length = 512)
    @Lob
    private String description;
    @Enumerated(EnumType.STRING)
    private ServiceShift serviceShift;

    @ManyToMany(mappedBy = "services")
    @ToString.Exclude
    private Set<Customers> customers;

    @OneToMany(mappedBy = "services")
    @ToString.Exclude
    private Set<Order> orders;

    private Long price;

    public Service(int serviceId, String serviceName, String description, String serviceShift, Set<Customers> customers, Set<Order> orders, Long price) {
        this.serviceId = serviceId;
        this.name = serviceName;
        this.description = description;
        this.serviceShift = pushShift(serviceShift);
        this.customers = customers;
        this.orders = orders;
        this.price = price;
    }

    public ServiceShift pushShift(String shift){
        return switch (shift) {
            case "MORNING" -> ServiceShift.MORNING;
            case "AFTERNOON" -> ServiceShift.AFTERNOON;
            case "EVENING" -> ServiceShift.EVENING;
            case "FULL_TIME" -> ServiceShift.FULL_TIME;
            default -> null;
        };
    }

    public String getShift(ServiceShift serviceShift) {
        return serviceShift.toString();
    }
}
    enum ServiceShift{
    MORNING, AFTERNOON, EVENING, FULL_TIME;
}