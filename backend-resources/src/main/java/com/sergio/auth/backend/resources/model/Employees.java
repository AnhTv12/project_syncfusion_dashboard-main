package com.sergio.auth.backend.resources.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    private String fistName;

    private String lastName;

    private String avatarPath;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    private Branch branch;
}
