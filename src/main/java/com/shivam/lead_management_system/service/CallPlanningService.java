package com.shivam.lead_management_system.service;

import com.shivam.lead_management_system.dto.CallPlanningResponseDTO;
import com.shivam.lead_management_system.entity.CallPlanning;
import com.shivam.lead_management_system.entity.Restaurant;
import com.shivam.lead_management_system.repository.CallPlanningRepository;
import com.shivam.lead_management_system.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

        calculateNextCallDate(callPlanning);
        callPlanning.setRestaurant(restaurant);
        restaurant.setCallPlanning(callPlanning);

        return callPlanningRepository.save(callPlanning);
    }

    private void calculateNextCallDate(CallPlanning callPlanning) {
        LocalDate today = LocalDate.now();

        switch (callPlanning.getCallFrequency()) {
            case DAILY:
                callPlanning.setNextCallDate(today.plusDays(1));
                break;
            case WEEKLY:
                callPlanning.setNextCallDate(today.plusWeeks(1));
                break;
            case MONTHLY:
                callPlanning.setNextCallDate(today.plusMonths(1));
                break;
            case CUSTOM:
                if (callPlanning.getNextCallDate() == null) {
                    callPlanning.setNextCallDate(today.plusWeeks(2));
                }
                break;
        }
    }

    public List<CallPlanningResponseDTO> getTodaysCalls() {
        LocalDate today = LocalDate.now();
        List<CallPlanning> pendingCalls = callPlanningRepository.findPendingCalls(today);
        List<CallPlanningResponseDTO> response = new ArrayList<>();

        for (CallPlanning callPlanning : pendingCalls) {
            CallPlanningResponseDTO dto = new CallPlanningResponseDTO();
            Restaurant restaurant = callPlanning.getRestaurant();

            dto.setRestaurantId(restaurant.getId());
            dto.setRestaurantName(restaurant.getName());
            dto.setCallFrequency(callPlanning.getCallFrequency().toString());
            dto.setPreviousInteractions(restaurant.getInteractions());

            response.add(dto);
        }

        return response;
    }

    public Optional<CallPlanning> getCallPlanningByRestaurantId(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(Restaurant::getCallPlanning);
    }

    public void deleteCallPlanning(Long callPlanningId) {
        callPlanningRepository.deleteById(callPlanningId);
    }
}