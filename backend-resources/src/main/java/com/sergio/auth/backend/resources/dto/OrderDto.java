package com.sergio.auth.backend.resources.dto;

import com.sergio.auth.backend.resources.model.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class OrderDto {

    private int orderId;

    private int branchId;

    private int serviceId;

    private int customerId;

    private long price;

    private long discount;

    private long totalPrice;

}
