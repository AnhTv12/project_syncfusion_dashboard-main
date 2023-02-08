package com.sergio.auth.backend.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class EmployeesDto {

    private int EmployeeId;

    private String EmployeeName;

    private String AvatarPath;

    private int BranchId;
}
