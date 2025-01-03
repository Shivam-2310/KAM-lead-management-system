package com.shivam.lead_management_system.repository;

import com.shivam.lead_management_system.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Long> {
}
