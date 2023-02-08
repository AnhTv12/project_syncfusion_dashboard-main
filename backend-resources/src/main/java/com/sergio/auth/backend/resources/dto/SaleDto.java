package com.sergio.auth.backend.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaleDto {

    private int id;
    private long amount;
    private String brandName;
    private String serviceName;

}
