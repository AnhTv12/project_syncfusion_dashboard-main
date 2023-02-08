package com.sergio.auth.backend.resources.service.services;

import com.sergio.auth.backend.resources.dto.BranchDto;
import com.sergio.auth.backend.resources.model.Branch;
import com.sergio.auth.backend.resources.model.Customers;
import com.sergio.auth.backend.resources.model.Employees;
import com.sergio.auth.backend.resources.model.Order;

import java.util.List;


public interface BranchService {

    List<Branch> listBranches();

    Branch findBranch(int id);

    Branch addBranch(Branch branch);

    Branch editBranch(Branch payload);

    void receiveOrder(Order order);

    String deleteBranch(int id);

    String deleteAllBranches();

    BranchDto dtoMapper(Branch branch);

    Branch EntityMapper(BranchDto branchDto);

    void addCustomer(Customers customers);

    void addEmployee(Employees employees);
}
