package com.shivam.lead_management_system.repository;

import com.shivam.lead_management_system.entity.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {

    List<Interaction> findByRestaurantIdOrderByInteractionDateDesc(Long restaurantId);

    @Query("SELECT i FROM Interaction i WHERE i.restaurant.id = :restaurantId ORDER BY i.createdAt DESC LIMIT 1")
    Optional<Interaction> findTopByRestaurantIdOrderByInteractionDateDesc(@Param("restaurantId") Long restaurantId);


}
