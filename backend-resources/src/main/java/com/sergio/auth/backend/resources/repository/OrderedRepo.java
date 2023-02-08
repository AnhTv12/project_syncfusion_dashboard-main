package com.sergio.auth.backend.resources.repository;
import com.sergio.auth.backend.resources.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedRepo extends JpaRepository<Order, Integer> {
    Order findByOrderId(int id);
}
