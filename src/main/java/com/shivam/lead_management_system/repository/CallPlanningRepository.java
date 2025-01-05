package com.shivam.lead_management_system.repository;

import com.shivam.lead_management_system.entity.CallPlanning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallPlanningRepository extends JpaRepository<CallPlanning, Long> {
}
