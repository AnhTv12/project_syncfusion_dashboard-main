package com.sergio.auth.backend.resources.repository;

import com.sergio.auth.backend.resources.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepo extends JpaRepository<Customers, Integer> {

    Customers findByCustomerId(int id);

    Customers findByEmail(String email);
}
