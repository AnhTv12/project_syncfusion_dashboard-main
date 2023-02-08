package com.sergio.auth.backend.resources.dto.saleDataDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class YearSaleDto {
    private int year;
    private List<MonthSaleDto> monthSaleList;
}
