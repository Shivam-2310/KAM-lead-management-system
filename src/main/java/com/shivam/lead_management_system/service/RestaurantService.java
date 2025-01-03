package com.shivam.lead_management_system.service;

import com.shivam.lead_management_system.entity.Contact;
import com.shivam.lead_management_system.entity.Restaurant;
import com.shivam.lead_management_system.exception.ResourceNotFoundException;
import com.shivam.lead_management_system.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant addRestaurant(Restaurant restaurant) {
        validateRestaurant(restaurant);

        for (Contact contact : restaurant.getContacts()) {
            contact.setRestaurant(restaurant);
        }

        return restaurantRepository.save(restaurant);
    }


    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, Restaurant updatedRestaurant) {
        validateRestaurant(updatedRestaurant);

        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));

        // Update existing restaurant
        existingRestaurant.setName(updatedRestaurant.getName());
        existingRestaurant.setAddress(updatedRestaurant.getAddress());

        // If you have contacts, you might want to update them accordingly
        // For example, replacing the existing contacts list:
        // existingRestaurant.setContacts(updatedRestaurant.getContacts());

        return restaurantRepository.save(existingRestaurant);
    }

    @Transactional
    public Restaurant partialUpdateRestaurant(Long id, Map<String, Object> updates) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));

        updates.forEach((key, value) -> {
            try {
                Field field = Restaurant.class.getDeclaredField(key);
                field.setAccessible(true);
                field.set(existingRestaurant, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new IllegalArgumentException("Invalid field: " + key, e);
            }
        });

        return restaurantRepository.save(existingRestaurant);
    }

    @Transactional
    public void deleteRestaurant(Long id) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id " + id));
        restaurantRepository.delete(existingRestaurant);
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (restaurant.getName() == null || restaurant.getName().isEmpty()) {
            throw new IllegalArgumentException("Restaurant name cannot be null or empty");
        }
        if (restaurant.getAddress() == null || restaurant.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Restaurant address cannot be null or empty");
        }
        // Add additional validations if necessary, such as for contact number
    }
}
