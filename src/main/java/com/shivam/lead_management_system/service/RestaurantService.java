package com.shivam.lead_management_system.service;

import com.shivam.lead_management_system.entity.Contact;
import com.shivam.lead_management_system.entity.Restaurant;
import com.shivam.lead_management_system.repository.ContactRepository;
import com.shivam.lead_management_system.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ContactRepository contactRepository;

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public List<Contact> addContacts(Restaurant restaurant, List<Contact> contacts) {
        contacts.forEach(contact -> contact.setRestaurant(restaurant));
        return contactRepository.saveAll(contacts);
    }

}
