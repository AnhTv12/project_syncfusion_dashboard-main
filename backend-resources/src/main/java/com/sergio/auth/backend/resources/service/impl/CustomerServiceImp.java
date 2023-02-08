package com.sergio.auth.backend.resources.service.impl;
import com.sergio.auth.backend.resources.dto.CustomersDto;
import com.sergio.auth.backend.resources.model.Branch;
import com.sergio.auth.backend.resources.model.Order;
import com.sergio.auth.backend.resources.repository.CustomersRepo;
import com.sergio.auth.backend.resources.model.Customers;
import com.sergio.auth.backend.resources.service.services.BranchService;
import com.sergio.auth.backend.resources.service.services.CustomerService;
import com.sergio.auth.backend.resources.service.services.OrderService;
import com.sergio.auth.backend.resources.service.services.ServiceHandle;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CustomerServiceImp implements CustomerService {
    private final CustomersRepo customersRepo;
    private final BranchService branchService;
    @Override
    public List<Customers> listCustomers() {
        return customersRepo.findAll();
    }
    @Override
    public Customers findCustomer(int id) {
        return customersRepo.findByCustomerId(id);
    }
    @Override
    public Customers addCustomer(Customers customers) {
        customers.setActiveDay(Calendar.getInstance());
        branchService.addCustomer(customers);
        return customersRepo.save(customers);
    }

    @Override
    public void addAll(Customers... customers) {
        customersRepo.saveAll(Arrays.asList(customers));
    }

    @Override
    public Customers editCustomer(Customers payload) {
        return null;
    }
    @Override
    @Transactional
    public void takeOrder(Order order) {
        Customers customers = findCustomer(order.getCustomers().getCustomerId());
        customers.getOrders().add(order);
        addCustomer(customers);
    }
    @Override
    public String deleteCustomer(int id) {
        customersRepo.deleteById(id);
        return "delete Customer Successful";
    }
    @Override
    public String deleteAllCustomers() {
        customersRepo.deleteAll();
        return "delete all Customers Successful";
    }
    @Override
    public CustomersDto dtoMapper(Customers customer) {
        List<Integer> serviceIds = customer.getServices().stream().map(com.sergio.auth.backend.resources.model.Service::getServiceId).toList();
        List<Integer> orderIds = customer.getOrders().stream().map(Order::getOrderId).toList();
        return new CustomersDto(customer.getCustomerId(),
                customer.getCustomerName(),
                customer.getAvatarPath(),
                customer.getActiveDay(),
                serviceIds,
                orderIds,
                customer.getBranch().getBranchId(),
                customer.getEmail());
    }
    @Override
    public Customers EntityMapper(CustomersDto customersDto) {
        Optional<Customers> resul = Optional.ofNullable(customersRepo.findByCustomerId(customersDto.getCustomerId()));
        if (resul.isEmpty()){
            Branch branch = branchService.findBranch(customersDto.getBranchId());
            Customers customers = new Customers();
            customers.setCustomerName(customers.getCustomerName());
            customers.setEmail(customers.getEmail());
            customers.setAvatarPath(customers.getAvatarPath());
            customers.setBranch(branch);
            customers.setServices(new ArrayList<>());
            customers.setOrders(new ArrayList<>());
            return customers;
        }
        return customersRepo.findByCustomerId(customersDto.getCustomerId());
    }

    public CustomerServiceImp(CustomersRepo customersRepo, BranchService branchService) {
        this.customersRepo = customersRepo;
        this.branchService = branchService;
    }

}
