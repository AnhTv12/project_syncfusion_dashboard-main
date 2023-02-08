package com.sergio.auth.backend.resources.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.util.Calendar;


@Entity
@Setter
@Getter
@RequiredArgsConstructor
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM")
    @Column(name = "time", length = 6)
    private Calendar time;

    @Column(name = "revenue", length = 100)
    private Long revenue;


}
