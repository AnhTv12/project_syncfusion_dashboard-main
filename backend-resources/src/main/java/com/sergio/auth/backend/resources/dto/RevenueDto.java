package com.sergio.auth.backend.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RevenueDto {
    private int id;
    private int Year;
    private int Month;
    private int Day;
    private long revenue;
}
