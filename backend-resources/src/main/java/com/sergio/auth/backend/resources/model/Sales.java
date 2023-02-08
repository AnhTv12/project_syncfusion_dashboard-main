package com.sergio.auth.backend.resources.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "time", length = 10)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Calendar time;

    @Column(name = "amout",length = 15)
    private long saleAmount;
    @Column(name = "branch", length = 30)
    private String brandName;
    @Column(name = "service",length = 30)
    private String serviceName;

    @OneToMany(mappedBy = "sales")
    @ToString.Exclude
    @Column(name = "orders")
    private List<Order> orders;

    public Sales(int id, Calendar time, long saleAmount, String brandName, String serviceName) {
        this.id = id;
        this.time = time;
        this.saleAmount = saleAmount;
        this.brandName = brandName;
        this.serviceName = serviceName;
    }

    public Sales(int id, Calendar time, long saleAmount, String brandName, String serviceName, List<Order> orders) {
        this.id = id;
        this.time = time;
        this.saleAmount = saleAmount;
        this.brandName = brandName;
        this.serviceName = serviceName;
        this.orders = orders;
    }
}
