package com.sergio.auth.backend.resources.service.impl;

import com.sergio.auth.backend.resources.dto.SaleDto;
import com.sergio.auth.backend.resources.dto.saleDataDto.MonthSaleDto;
import com.sergio.auth.backend.resources.dto.saleDataDto.YearSaleDto;
import com.sergio.auth.backend.resources.model.Order;
import com.sergio.auth.backend.resources.model.Sales;
import com.sergio.auth.backend.resources.repository.RevenueRepo;
import com.sergio.auth.backend.resources.repository.SalesRepo;
import com.sergio.auth.backend.resources.repository.ServiceRepo;
import com.sergio.auth.backend.resources.service.services.RevenueService;
import com.sergio.auth.backend.resources.service.services.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
public class SalesServiceImp implements SalesService {
    private final SalesRepo salesRepo;
    private final RevenueService revenueService;
    @Override
    public List<Sales> listSales() {
        return salesRepo.findAll();
    }
    @Override
    public List<YearSaleDto> findSalesPerYear(int start, int end) {
        List<YearSaleDto> res = new ArrayList<>();
        for (int i = start; i <= end ; i++) {
            List<MonthSaleDto> monthSale = dataPerMonthConvert(salesRepo.findSalesByYear(i));
            YearSaleDto yearSale = new YearSaleDto(i,monthSale);
            res.add(yearSale);
        }
        return res;
    }
    @Override
    public List<YearSaleDto> findBranchSalesPerYear(int start, int end, String branchesName) {
        List<YearSaleDto> yearSaleDtos = new ArrayList<>();
            for (int i = start; i <= end ; i++) {
                List<Sales> branchSalesList = salesRepo.findBranchSales(i,branchesName);
                List<MonthSaleDto> monthSaleDtoList= dataPerMonthConvert(branchSalesList);
                YearSaleDto yearSaleDto = new YearSaleDto(i,monthSaleDtoList);

                yearSaleDtos.add(yearSaleDto);
            }
        return yearSaleDtos;
    }
//    @Override
//    public List<?> findBranchSalesPerMonth(int start, int end, List<String> branchesName) {
//        List<List<?>> data = new ArrayList<>();
//        branchesName.forEach(name->{
//            List<List<?>> yearData = new ArrayList<>();
//            for (int i = start; i <= end ; i++) {
//                List<?> monthData = dataPerMonthConvert(salesRepo.findBranchSales(i,name));
//                yearData.add(monthData);
//            }
//            data.add(yearData);
//        });
//        return data;
//    }
    @Override
    public List<YearSaleDto> findServiceSalesPerYear(int start, int end, String serviceName) {
        List<YearSaleDto> yearSaleDtos = new ArrayList<>();
            for (int i = start; i <= end ; i++) {
                List<Sales> serviceSalesList = salesRepo.findServiceSales(i,serviceName);
                List<MonthSaleDto> monthSaleDtoList= dataPerMonthConvert(serviceSalesList);
                YearSaleDto yearSaleDto = new YearSaleDto(i,monthSaleDtoList);
                yearSaleDtos.add(yearSaleDto);
            }
        return yearSaleDtos;
    }

//    @Override
//    public List<?> findServiceSalesPerMonth(int start, int end, List<String> serviceName) {
//        List<List<?>> data = new ArrayList<>();
//        serviceName.forEach(name->{
//            List<List<?>> yearData = new ArrayList<>();
//            for (int i = start; i <= end ; i++) {
//                List<?> monthData = dataPerMonthConvert(salesRepo.findServiceSales(i,name));
//                yearData.add(monthData);
//            }
//            data.add(yearData);
//        });
//        return data;
//    }

    private List<MonthSaleDto> dataPerMonthConvert(List<Sales> sales) {
        List<MonthSaleDto> monthData = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            int month = i;
            MonthSaleDto monthSaleDto = new MonthSaleDto(i,sales.stream().filter(sale -> sale.getTime().get(Calendar.MONTH)==month).toList());
            monthData.add(monthSaleDto);
        }
        return monthData;
    }
    @Override
    public Sales findSaleById(int id) {
        return salesRepo.findSalesById(id);
    }
    @Override
    public Sales findSalePerDay(Calendar calendar) {
        return salesRepo.findSalesPerDay(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public List<Sales> findSalesPerMonth(Calendar calendar) {
        return salesRepo.findSalesPerMonth(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH));
    }
    @Override
    public Sales addSale(Sales sale) {
        return null;
    }
    @Override
    public Sales editSale(Sales payload) {
        return null;
    }
    @Override
    @Transactional
    public Order addOrderAndCalculateRevenue(Order order) {
            Sales newSale = new Sales();
            newSale.setTime(order.getOrderedTime());
            newSale.setBrandName(order.getBranch().getBranchName());
            newSale.setServiceName(order.getServices().getName());
            newSale.setSaleAmount(order.getTotalPrice());
            revenueService.addRevenue(order);
            order.setSales(newSale);
            salesRepo.save(newSale);
        return order;
    }
    @Override
    public String deleteSale(int id) {
        return null;
    }
    @Override
    public String deleteAllSales() {
        return null;
    }
    @Override
    public SaleDto dtoMapper(Sales sales) {

        return new SaleDto(sales.getId(),sales.getSaleAmount(),sales.getBrandName(),sales.getServiceName());
    }
    @Override
    public Sales EntityMapper(SaleDto saleDto) {
        return salesRepo.findSalesById(saleDto.getId());
    }

    public SalesServiceImp(SalesRepo salesRepo, RevenueService revenueService) {
        this.salesRepo = salesRepo;
        this.revenueService = revenueService;
    }

}
