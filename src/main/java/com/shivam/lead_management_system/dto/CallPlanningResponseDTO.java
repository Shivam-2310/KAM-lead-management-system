package com.shivam.lead_management_system.dto;

import com.shivam.lead_management_system.entity.Interaction;
import java.util.List;

public class CallPlanningResponseDTO {
    private Long restaurantId;
    private String restaurantName;
    private String callFrequency;
    private List<Interaction> previousInteractions;

    // Getters and setters
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCallFrequency() {
        return callFrequency;
    }

    public void setCallFrequency(String callFrequency) {
        this.callFrequency = callFrequency;
    }

    public List<Interaction> getPreviousInteractions() {
        return previousInteractions;
    }

    public void setPreviousInteractions(List<Interaction> previousInteractions) {
        this.previousInteractions = previousInteractions;
    }
}