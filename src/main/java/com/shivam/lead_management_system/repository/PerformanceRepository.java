package com.shivam.lead_management_system.repository;

import com.shivam.lead_management_system.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PerformanceRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT r.name AS restaurantName, r.businessType, i.orderValue " +
            "FROM Restaurant r JOIN r.interactions i " +
            "WHERE i.orderValue IS NOT NULL " +
            "ORDER BY r.name, i.orderValue DESC")
    List<Object[]> findRestaurantOrderData();
}
