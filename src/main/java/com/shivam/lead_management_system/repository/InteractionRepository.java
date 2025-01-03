package com.shivam.lead_management_system.repository;

import com.shivam.lead_management_system.entity.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionRepository extends JpaRepository<Interaction, Integer> {
}
