package com.example.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ApiController {

    private final GreetingService greetingService;

    ApiController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/")
    String index() {
        return greetingService.greet("Everybody");
    }

}
