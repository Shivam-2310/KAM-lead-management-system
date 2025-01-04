package com.shivam.lead_management_system.service;

import com.shivam.lead_management_system.entity.Interaction;
import com.shivam.lead_management_system.entity.Restaurant;
import com.shivam.lead_management_system.exception.ResourceNotFoundException;
import com.shivam.lead_management_system.repository.InteractionRepository;
import com.shivam.lead_management_system.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InteractionService {

    @Autowired
    private InteractionRepository interactionRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Interaction recordInteraction(Long restaurantId, Interaction interaction) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with id: " + restaurantId));
        interaction.setRestaurant(restaurant);
        return interactionRepository.save(interaction);
    }

    public List<Interaction> getInteractionsByRestaurant(Long restaurantId) {
        return interactionRepository.findByRestaurantIdOrderByInteractionDateDesc(restaurantId);
    }


    public Interaction getLatestInteractionByRestaurantId(Long restaurantId) {
        return interactionRepository
                .findTopByRestaurantIdOrderByInteractionDateDesc(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("No interactions found for restaurant ID: " + restaurantId));
    }


}
