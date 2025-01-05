package com.shivam.lead_management_system.service;

import com.shivam.lead_management_system.entity.CallPlanning;
import com.shivam.lead_management_system.entity.Restaurant;
import com.shivam.lead_management_system.repository.CallPlanningRepository;
import com.shivam.lead_management_system.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CallPlanningService {

    @Autowired
    private CallPlanningRepository callPlanningRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public CallPlanning createOrUpdateCallPlanning(CallPlanning callPlanning, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID: " + restaurantId));

        callPlanning.setRestaurant(restaurant);
        restaurant.setCallPlanning(callPlanning);

        return callPlanningRepository.save(callPlanning);
    }

    public Optional<CallPlanning> getCallPlanningByRestaurantId(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(Restaurant::getCallPlanning);
    }

    public void deleteCallPlanning(Long callPlanningId) {
        callPlanningRepository.deleteById(callPlanningId);
    }
}
