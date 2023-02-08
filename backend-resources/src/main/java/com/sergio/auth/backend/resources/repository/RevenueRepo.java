package com.sergio.auth.backend.resources.repository;

import com.sergio.auth.backend.resources.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RevenueRepo extends JpaRepository<Revenue, Integer> {
    Revenue findRevenueById(int id);
    @Query("select re from Revenue re where extract(YEAR from re.time) =:year and extract(MONTH from re.time)=:month ")
    Revenue findRevenue(@Param("year") int year, @Param("month") int month);
}
