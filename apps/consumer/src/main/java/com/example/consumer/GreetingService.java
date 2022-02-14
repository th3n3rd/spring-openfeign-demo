package com.example.consumer;

import org.springframework.stereotype.Service;

@Service
class GreetingService {

    private final ProviderClient providerClient;

    GreetingService(ProviderClient providerClient) {
        this.providerClient = providerClient;
    }

    String greet(String somebody) {
        return providerClient.greet(somebody).getGreeting();
    }
}
