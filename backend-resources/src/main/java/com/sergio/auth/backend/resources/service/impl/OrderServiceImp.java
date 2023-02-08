package com.sergio.auth.backend.resources.service.impl;
import com.sergio.auth.backend.resources.dto.OrderDto;
import com.sergio.auth.backend.resources.model.*;
import com.sergio.auth.backend.resources.repository.OrderedRepo;
import com.sergio.auth.backend.resources.service.services.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class OrderServiceImp implements OrderService {

    private final OrderedRepo orderedRepo;
    private final CustomerService customerService;
    private final BranchService branchService;
    private final ServiceHandle serviceHandle;
    private final SalesService salesService;

    @Override
    public List<Order> listOrders() {
        return orderedRepo.findAll();
    }
    @Override
    public Order findOrder(int id) {
        return orderedRepo.findByOrderId(id);
    }
    @Override
    @Transactional
    public Order addOrder(Order payload) {
        customerService.takeOrder(payload);
        branchService.receiveOrder(payload);
        payload = serviceHandle.receiveOrder(payload);
        payload.setTotalPrice(payload.getPrice()-payload.getDiscount());
        payload = salesService.addOrderAndCalculateRevenue(payload);
        return orderedRepo.save(payload);
    }

    @Override
    public Order editOrder(Order payload) {
        return orderedRepo.save(payload);
    }

    @Override
    public String deleteOrder(int id) {
        orderedRepo.deleteById(id);
        return "delete successfully";
    }
    @Override
    public String deleteAllOrders() {
        orderedRepo.deleteAll();
        return "delete successfully";
    }
    @Override
    public OrderDto dtoMapper(Order order) {
        return new OrderDto(order.getOrderId(), order.getBranch().getBranchId(), order.getServices().getServiceId(),order.getCustomers().getCustomerId(),
                            order.getPrice(), order.getDiscount(), order.getTotalPrice());
    }
    @Override
    public Order EntityMapper(OrderDto orderDto) {
        Optional<Order> result = Optional.ofNullable(orderedRepo.findByOrderId(orderDto.getOrderId()));
        if (result.isEmpty()){
            Order order = new Order();
            order.setBranch(branchService.findBranch(orderDto.getBranchId()));
            order.setServices(serviceHandle.findService(orderDto.getServiceId()));
            order.setCustomers(customerService.findCustomer(orderDto.getCustomerId()));
            order.setOrderedTime(Calendar.getInstance());
            order.setPrice(orderDto.getPrice());
            order.setDiscount(orderDto.getDiscount());
            order.setTotalPrice(orderDto.getTotalPrice());
            return order;
        }
        return result.get();
    }

    public OrderServiceImp(OrderedRepo orderedRepo, CustomerService customerService, BranchService branchService, ServiceHandle serviceHandle, EmployeeService employeeService, SalesService salesService) {
        this.orderedRepo = orderedRepo;
        this.customerService = customerService;
        this.branchService = branchService;
        this.serviceHandle = serviceHandle;
        this.salesService = salesService;
    }
}
