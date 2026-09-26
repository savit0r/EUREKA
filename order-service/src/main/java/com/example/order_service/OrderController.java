package com.example.order_service;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

@RestController
public class OrderController {

    private final DiscoveryClient discoveryClient;

    private final RestClient restClient;

    public OrderController(DiscoveryClient discoveryClient) {

        this.discoveryClient = discoveryClient;

        this.restClient = RestClient.builder().build();
    }

    @GetMapping("/orders")
    public String getOrders() {

        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");

        if (instances.isEmpty()) {
            return "USER-SERVICE is unavailable";
        }

        ServiceInstance instance = instances.get(0);

        String url = instance.getUri() + "/users";

        return restClient
                .get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }
}