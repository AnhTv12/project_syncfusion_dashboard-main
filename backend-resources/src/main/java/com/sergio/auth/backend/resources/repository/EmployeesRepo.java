package com.sergio.auth.backend.resources.repository;

import com.sergio.auth.backend.resources.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepo extends JpaRepository<Employees, Integer> {
    Employees findByEmployeeId(int id);
}
