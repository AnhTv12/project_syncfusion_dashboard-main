package com.sergio.auth.backend.resources.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class BranchDto {

    private int BranchId;

    private String name;

    private String location;

    private String manager;

    private List<Integer> customerIds;

    private List<Integer> employeeIds;

    private List<Integer> orderIds;
}