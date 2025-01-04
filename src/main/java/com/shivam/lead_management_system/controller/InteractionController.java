package com.shivam.lead_management_system.controller;

import com.shivam.lead_management_system.entity.Interaction;
import com.shivam.lead_management_system.service.InteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{id}/interactions")
public class InteractionController {

    @Autowired
    private InteractionService interactionService;

    @PostMapping
    public ResponseEntity<Interaction> recordInteraction(@PathVariable("id") Long restaurantId,
                                                         @RequestBody Interaction interaction) {
        Interaction createdInteraction = interactionService.recordInteraction(restaurantId, interaction);
        return new ResponseEntity<>(createdInteraction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Interaction>> getInteractions(@PathVariable("id") Long restaurantId) {
        List<Interaction> interactions = interactionService.getInteractionsByRestaurant(restaurantId);
        return ResponseEntity.ok(interactions);
    }

    @GetMapping("/latest")
    public ResponseEntity<Interaction> getLatestInteraction(@PathVariable("id") Long restaurantId) {
        Interaction latestInteraction = interactionService.getLatestInteractionByRestaurantId(restaurantId);
        return ResponseEntity.ok(latestInteraction);
    }

}
