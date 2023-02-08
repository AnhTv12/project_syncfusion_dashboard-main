package com.sergio.auth.backend.resources.service.impl;

import com.sergio.auth.backend.resources.dto.EmployeesDto;
import com.sergio.auth.backend.resources.model.Branch;
import com.sergio.auth.backend.resources.repository.EmployeesRepo;
import com.sergio.auth.backend.resources.model.Employees;
import com.sergio.auth.backend.resources.service.services.BranchService;
import com.sergio.auth.backend.resources.service.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeesRepo employeesRepo;
    private final BranchService branchService;

    @Override
    public List<Employees> listEmployees() {
        return employeesRepo.findAll();
    }
    @Override
    public Employees findEmployee(int id) {
        return employeesRepo.findByEmployeeId(id);
    }
    @Override
    public Employees addEmployee(Employees employees) {
        branchService.addEmployee(employees);
        return employeesRepo.save(employees);
    }

    @Override
    public void addAll(Employees... employees) {
        employeesRepo.saveAll(Arrays.asList(employees));
    }

    @Override
    public Employees editEmployee(Employees payload) {
        return null;
    }
    @Override
    public String deleteEmployee(int id) {
        employeesRepo.deleteById(id);
        return "delete employee successfully";
    }
    @Override
    public String deleteAllEmployees() {
        employeesRepo.deleteAll();
        return "delete all employees successfully";
    }
    @Override
    public EmployeesDto dtoMapper(Employees employees) {
        return new EmployeesDto(employees.getEmployeeId(), employees.getFistName(), employees.getAvatarPath(), employees.getBranch().getBranchId());
    }
    @Override
    public Employees EntityMapper(EmployeesDto employeesDto) {
        Optional<Employees> result = Optional.ofNullable(employeesRepo.findByEmployeeId(employeesDto.getEmployeeId()));
        if (result.isEmpty()){
        Branch branch = branchService.findBranch(employeesDto.getBranchId());
        Employees employees = new Employees();
        employees.setFistName(employeesDto.getEmployeeName());
        employees.setAvatarPath(employeesDto.getAvatarPath());
        employees.setBranch(branch);
        return employees;
        }
        return result.get();
    }
    public EmployeeServiceImp(EmployeesRepo employeesRepo, BranchService branchService) {
        this.employeesRepo = employeesRepo;
        this.branchService = branchService;
    }
}
