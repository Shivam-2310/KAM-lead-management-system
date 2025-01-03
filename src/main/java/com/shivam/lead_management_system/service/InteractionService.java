package com.shivam.lead_management_system.service;

import com.shivam.lead_management_system.entity.Interaction;
import com.shivam.lead_management_system.repository.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteractionService {

    @Autowired
    private InteractionRepository interactionRepository;

    public Interaction addInteraction(Interaction interaction) {
        return interactionRepository.save(interaction);
    }
}
