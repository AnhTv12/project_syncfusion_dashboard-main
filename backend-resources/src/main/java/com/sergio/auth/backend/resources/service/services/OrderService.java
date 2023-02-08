package com.sergio.auth.backend.resources.service.services;

import com.sergio.auth.backend.resources.dto.OrderDto;
import com.sergio.auth.backend.resources.model.Order;

import java.text.ParseException;
import java.util.List;


public interface OrderService {

    List<Order> listOrders();

    Order findOrder(int id);

    Order addOrder(Order payload);

    Order editOrder(Order payload);

    String deleteOrder(int id);

    String deleteAllOrders();

    OrderDto dtoMapper(Order order);

    Order EntityMapper(OrderDto orderDto);
}
