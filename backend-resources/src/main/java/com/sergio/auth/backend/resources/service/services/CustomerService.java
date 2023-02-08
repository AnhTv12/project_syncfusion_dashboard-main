package com.sergio.auth.backend.resources.service.services;

import com.sergio.auth.backend.resources.dto.CustomersDto;
import com.sergio.auth.backend.resources.model.Customers;
import com.sergio.auth.backend.resources.model.Order;

import java.util.List;


public interface CustomerService {

    List<Customers> listCustomers();

    Customers findCustomer(int id);

    Customers addCustomer(Customers customers);

    void addAll(Customers... customers);
    Customers editCustomer(Customers payload);

    void takeOrder(Order order);

    String deleteCustomer(int id);

    String deleteAllCustomers();

    CustomersDto dtoMapper(Customers customer);

    Customers EntityMapper(CustomersDto customersDto);
}
