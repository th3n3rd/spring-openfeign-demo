package com.example.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "provider", url = "${provider.url}")
interface ProviderClient {

    @GetMapping("/greetings/{somebody}")
    String greet(@PathVariable String somebody);

}
