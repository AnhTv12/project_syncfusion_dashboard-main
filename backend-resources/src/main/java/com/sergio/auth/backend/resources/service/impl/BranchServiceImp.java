package com.sergio.auth.backend.resources.service.impl;
import com.sergio.auth.backend.resources.dto.BranchDto;
import com.sergio.auth.backend.resources.model.Customers;
import com.sergio.auth.backend.resources.model.Employees;
import com.sergio.auth.backend.resources.model.Order;
import com.sergio.auth.backend.resources.repository.BranchRepo;
import com.sergio.auth.backend.resources.model.Branch;
import com.sergio.auth.backend.resources.service.services.BranchService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImp implements BranchService {
    private final BranchRepo branchRepo;
    @Override
    public List<Branch> listBranches() {
        return branchRepo.findAll();
    }
    @Override
    public Branch findBranch(int id) {
        return branchRepo.findBranchByBranchId(id);
    }
    @Override
    public Branch addBranch(Branch branch) {
        return branchRepo.save(branch);
    }
    @Override
    public Branch editBranch(Branch payload) {
        return null;
    }
    @Override
    @Transactional
    public void receiveOrder(Order order) {
        Branch branch = findBranch(order.getBranch().getBranchId());
        branch.getOrders().add(order);
        addBranch(branch);
    }
    @Override
    public void addEmployee(Employees employees) {
        Branch branch = branchRepo.findBranchByBranchId(employees.getBranch().getBranchId());
        branch.getEmployees().add(employees);
        branchRepo.save(branch);
    }
    @Override
    public void addCustomer(Customers customers) {
        Branch branch = branchRepo.findBranchByBranchId(customers.getBranch().getBranchId());
        branch.getCustomers().add(customers);
        branchRepo.save(branch);
    }
    @Override
    public String deleteBranch(int id) {
        branchRepo.deleteById(id);
        return "delete Branch Successful";
    }
    @Override
    public String deleteAllBranches()   {
        branchRepo.deleteAll();
        return "delete all Branch Successful";
    }
    @Override
    public BranchDto dtoMapper(Branch branch) {
        return new BranchDto(
                        branch.getBranchId(),
                        branch.getBranchName(),
                        branch.getLocation(),
                        branch.getManager(),
                        getCustomerIds(branch),
                        getEmployeeIds(branch),
                        getOrderIds(branch));
    }
    @Override
    public Branch EntityMapper(BranchDto branchDto) {
        Optional<Branch> resul = Optional.ofNullable(branchRepo.findBranchByBranchId(branchDto.getBranchId()));
        if (resul.isEmpty()){
            return new Branch(branchDto.getBranchId(), branchDto.getName(), branchDto.getLocation(), branchDto.getManager(),new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        }
        return resul.get();
    }
    private List<Integer> getCustomerIds(Branch branch) {
        return branch.getCustomers().stream().map(Customers::getCustomerId).toList();
    }
    private List<Integer> getEmployeeIds(Branch branch) {
        return branch.getEmployees().stream().map(Employees::getEmployeeId).toList();
    }
    private List<Integer> getOrderIds(Branch branch) {
        return branch.getOrders().stream().map(Order::getOrderId).toList();
    }
    public BranchServiceImp(BranchRepo branchRepo) {
        this.branchRepo = branchRepo;
    }
}
