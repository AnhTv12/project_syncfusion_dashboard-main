package com.sergio.auth.backend.resources.service.impl;
import com.sergio.auth.backend.resources.dto.RevenueDto;
import com.sergio.auth.backend.resources.model.Order;
import com.sergio.auth.backend.resources.model.Revenue;
import com.sergio.auth.backend.resources.repository.RevenueRepo;
import com.sergio.auth.backend.resources.service.services.RevenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class RevenueServiceImp implements RevenueService {

    private final RevenueRepo revenueRepo;

    @Override
    public List<Revenue> listRevenues() {
        return revenueRepo.findAll();
    }

    @Override
    public Revenue findRevenue(int id) {
        return revenueRepo.findRevenueById(id);
    }

    @Override
    public Revenue addRevenue(Revenue revenue) {
        return revenueRepo.save(revenue);
    }

    @Override
    public Revenue editRevenue(Revenue payload) {
        return null;
    }

    @Override
    public void addRevenue(Order order) {
        Calendar calendar = order.getOrderedTime();
        Revenue result = revenueRepo.findRevenue(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1);
//        Calendar calendar1 = result.getTime();
//        log.info("Id: {}", result.getId());
//        log.info("Year: {} Month: {} Day: {}",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
//        log.info("Year: {} Month: {} Day: {}",calendar1.get(Calendar.YEAR),calendar1.get(Calendar.MONTH),calendar1.get(Calendar.DAY_OF_MONTH));
        Revenue revenue;
        if (result==null){
            revenue = new Revenue();
            revenue.setTime(calendar);
            revenue.setRevenue(order.getTotalPrice());
        }else {
            revenue = result;
            revenue.setRevenue(revenue.getRevenue()+order.getTotalPrice());
        }
        revenueRepo.save(revenue);
    }

    @Override
    public String deleteRevenue(int id) {
        return null;
    }

    @Override
    public String deleteAllRevenues() {
        return null;
    }

    @Override
    public RevenueDto dtoMapper(Revenue revenue) {
        return new RevenueDto(revenue.getId(), revenue.getTime().get(Calendar.YEAR),
                revenue.getTime().get(Calendar.MONTH), revenue.getTime().get(Calendar.DAY_OF_MONTH),
                revenue.getRevenue());
    }

    @Override
    public Revenue EntityMapper(RevenueDto revenueDto) {
        return revenueRepo.findRevenueById(revenueDto.getId());
    }

    public RevenueServiceImp(RevenueRepo revenueRepo) {
        this.revenueRepo = revenueRepo;
    }
}
