package com.sergio.auth.backend.resources.service.services;

import com.sergio.auth.backend.resources.dto.SaleDto;
import com.sergio.auth.backend.resources.dto.saleDataDto.MonthSaleDto;
import com.sergio.auth.backend.resources.dto.saleDataDto.YearSaleDto;
import com.sergio.auth.backend.resources.model.Order;
import com.sergio.auth.backend.resources.model.Sales;

import java.util.Calendar;
import java.util.List;
import java.util.Map;


public interface SalesService {

    List<Sales> listSales();

    List<Sales> findSalesPerMonth(Calendar calendar);

    Sales findSalePerDay(Calendar calendar);
    Sales findSaleById(int id);

    Sales addSale(Sales sale);

    Sales editSale(Sales payload);

    Order addOrderAndCalculateRevenue(Order order);
    String deleteSale(int id);

    String deleteAllSales();

    SaleDto dtoMapper(Sales sales);

    Sales EntityMapper(SaleDto saleDto);

    List<?> findSalesPerYear(int start, int end);

    List<YearSaleDto> findBranchSalesPerYear(int start, int end, String branchesName);

//    MonthSaleDto findBranchSalesPerMonth(int start, int end, List<String> branchesName);

    List<YearSaleDto> findServiceSalesPerYear(int start, int end, String serviceName);

//    MonthSaleDto findServiceSalesPerMonth(int start, int end, List<String> serviceName);
}
