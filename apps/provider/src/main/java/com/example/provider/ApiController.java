package com.example.provider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ApiController {

    @GetMapping("/greetings/{somebody}")
    String greet(@PathVariable String somebody) {
        return String.format("Hello %s! long time no see", somebody);
    }

}
