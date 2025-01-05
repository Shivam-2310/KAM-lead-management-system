package com.shivam.lead_management_system.service;

import com.shivam.lead_management_system.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PerformanceService {
    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private OllamaService ollamaService;

    public String generatePerformanceAnalysis() {
        List<Object[]> restaurantOrderData = performanceRepository.findRestaurantOrderData();
        String prompt = createPrompt(restaurantOrderData);
        return ollamaService.generateAnalysis(prompt);
    }

    private String createPrompt(List<Object[]> restaurantOrderData) {
        StringBuilder prompt = new StringBuilder("Analyze the performance of the following restaurants based on their order values, write in brief, and dont write anything in bold:" +
                "\n\n");

        for (Object[] data : restaurantOrderData) {
            String restaurantName = (String) data[0];
            String businessType = (String) data[1];
            Double orderValue = (Double) data[2];
            prompt.append("Restaurant Name: ").append(restaurantName)
                    .append(", Business Type: ").append(businessType)
                    .append(", Order Value: $").append(String.format("%.2f", orderValue))
                    .append("\n");
        }

        prompt.append("\nProvide insights on overall performance, top performers, and areas for improvement.");
        return prompt.toString();
    }
}
