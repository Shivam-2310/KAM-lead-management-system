package com.shivam.lead_management_system.repository;

import com.shivam.lead_management_system.entity.CallPlanning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CallPlanningRepository extends JpaRepository<CallPlanning, Long> {
    @Query("SELECT cp FROM CallPlanning cp WHERE cp.nextCallDate <= :date")
    List<CallPlanning> findPendingCalls(LocalDate date);
}