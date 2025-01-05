package com.shivam.lead_management_system.controller;

import com.shivam.lead_management_system.entity.CallPlanning;
import com.shivam.lead_management_system.service.CallPlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/call-planning")
public class CallPlanningController {

    @Autowired
    private CallPlanningService callPlanningService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<CallPlanning> createOrUpdateCallPlanning(
            @PathVariable Long restaurantId,
            @RequestBody CallPlanning callPlanning) {
        CallPlanning savedCallPlanning = callPlanningService.createOrUpdateCallPlanning(callPlanning, restaurantId);
        return ResponseEntity.ok(savedCallPlanning);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<CallPlanning> getCallPlanningByRestaurantId(@PathVariable Long restaurantId) {
        return callPlanningService.getCallPlanningByRestaurantId(restaurantId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{callPlanningId}")
    public ResponseEntity<Void> deleteCallPlanning(@PathVariable Long callPlanningId) {
        callPlanningService.deleteCallPlanning(callPlanningId);
        return ResponseEntity.noContent().build();
    }
}
