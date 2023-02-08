package com.sergio.auth.backend.resources.service.services;

import com.sergio.auth.backend.resources.dto.EmployeesDto;
import com.sergio.auth.backend.resources.model.Employees;

import java.util.List;


public interface EmployeeService {

    List<Employees> listEmployees();

    Employees findEmployee(int id);

    Employees addEmployee(Employees employees);

    void addAll(Employees... employees);
    Employees editEmployee(Employees payload);

    String deleteEmployee(int id);

    String deleteAllEmployees();

    EmployeesDto dtoMapper(Employees employees);

    Employees EntityMapper(EmployeesDto employeesDto);
}
