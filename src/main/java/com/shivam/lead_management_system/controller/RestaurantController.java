package com.shivam.lead_management_system.controller;

import com.shivam.lead_management_system.entity.Contact;
import com.shivam.lead_management_system.entity.Restaurant;
import com.shivam.lead_management_system.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantService.addRestaurant(restaurant);

        Map<String, Object> response = new HashMap<>();
        response.put("id", savedRestaurant.getId());
        response.put("message", "Restaurant added successfully.");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping("/{id}/addContacts")
    public ResponseEntity<Map<String, Object>> addContactsToRestaurant(
            @PathVariable("id") Long restaurantId,
            @RequestBody List<Contact> contacts) {

        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "Restaurant not found"));
        }

        contacts.forEach(contact -> contact.setRestaurant(restaurant));

        List<Contact> addedContacts = restaurantService.addContacts(restaurant, contacts);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Contacts added successfully.");
        response.put("restaurant", restaurant);
        response.put("addedContacts", addedContacts);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{id}/contacts")
    public ResponseEntity<List<Contact>> getContactsByRestaurantId(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Contact> contacts = restaurant.getContacts();

        return ResponseEntity.ok(contacts);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> updateRestaurantStatus(
            @PathVariable Long id,
            @RequestParam("status") String status) {

        if (!List.of("NEW", "IN_PROGRESS", "CLOSED").contains(status.toUpperCase())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("message", "Invalid status. Allowed values: NEW, IN_PROGRESS, CLOSED."));
        }

        Restaurant updatedRestaurant = restaurantService.updateRestaurantStatus(id, status.toUpperCase());
        if (updatedRestaurant == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("message", "Restaurant not found."));
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Restaurant status updated successfully.");
        response.put("updatedRestaurant", updatedRestaurant);

        return ResponseEntity.ok(response);
    }



}
