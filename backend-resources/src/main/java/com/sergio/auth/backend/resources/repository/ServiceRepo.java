package com.sergio.auth.backend.resources.repository;

import com.sergio.auth.backend.resources.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Service, Integer> {
    Service findServiceByServiceId(int id);
}
