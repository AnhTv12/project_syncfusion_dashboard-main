package com.sergio.auth.backend.resources.service.services;

import com.sergio.auth.backend.resources.dto.RevenueDto;
import com.sergio.auth.backend.resources.model.Order;
import com.sergio.auth.backend.resources.model.Revenue;

import java.util.List;


public interface RevenueService {

    List<Revenue> listRevenues();

    Revenue findRevenue(int id);

    Revenue addRevenue(Revenue revenue);

    Revenue editRevenue(Revenue payload);

    void addRevenue(Order order);

    String deleteRevenue(int id);

    String deleteAllRevenues();

    RevenueDto dtoMapper(Revenue revenue);

    Revenue EntityMapper(RevenueDto revenueDto);
}
