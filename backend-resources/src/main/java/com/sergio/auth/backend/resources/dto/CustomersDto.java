package com.sergio.auth.backend.resources.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sergio.auth.backend.resources.model.Order;
import com.sergio.auth.backend.resources.model.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CustomersDto {

    private int customerId;

    private String customerName;

    private String avatarPath;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Calendar activeDay;

    private List<Integer> serviceIds;

    private List<Integer> orderIds;

    private int branchId;

    private String email;

}
