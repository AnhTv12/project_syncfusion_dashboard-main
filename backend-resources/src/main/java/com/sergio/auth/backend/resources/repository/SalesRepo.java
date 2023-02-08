package com.sergio.auth.backend.resources.repository;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sergio.auth.backend.resources.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesRepo extends JpaRepository<Sales, Integer> {

    Sales findSalesById(int id);
    @Query("select s from Sales s where extract(YEAR from s.time) =:year and extract(MONTH from s.time)=:month ")
    List<Sales> findSalesPerMonth(@Param("year") int year, @Param("month") int month);

    @Query("select s from Sales s where extract(YEAR from s.time) =:year " +
            "and extract(MONTH from s.time)=:month and extract(DAY from s.time)=:day ")
    Sales findSalesPerDay(@Param("year") int year,@Param("month") int month, @Param("day") int day);

    @Query("select new Sales(s.id,s.time,s.saleAmount,s.brandName,s.serviceName) from Sales s where extract(YEAR from s.time) =:year ")
    List<Sales> findSalesByYear(@Param("year")int year);

    @Query("select new Sales(s.id,s.time,s.saleAmount,s.brandName,s.serviceName) from Sales s where extract(YEAR from s.time) =:year and s.brandName=:branch")
    List<Sales> findBranchSales(@Param("year")int year,@Param("branch")String branch);

    @Query("select new Sales(s.id,s.time,s.saleAmount,s.brandName,s.serviceName) from Sales s where extract(YEAR from s.time) =:year and s.serviceName=:service")
    List<Sales> findServiceSales(@Param("year")int year,@Param("service")String service);
}
