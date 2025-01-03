package com.shivam.lead_management_system.repository;

import com.shivam.lead_management_system.entity.KAM;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KAMRepository extends JpaRepository<KAM, Long> {
    KAM findByUsername(String username);
}

