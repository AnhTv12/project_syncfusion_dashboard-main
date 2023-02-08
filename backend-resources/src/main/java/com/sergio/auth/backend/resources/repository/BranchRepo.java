package com.sergio.auth.backend.resources.repository;

import com.sergio.auth.backend.resources.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface BranchRepo extends JpaRepository<Branch, Integer> {
    Branch findBranchByBranchId(int id);

}
