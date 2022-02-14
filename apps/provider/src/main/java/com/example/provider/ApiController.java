package com.example.provider;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ApiController {

    @GetMapping("/greetings/{somebody}")
    GreetResponse greet(@PathVariable String somebody) {
        return new GreetResponse(String.format("Hello %s! long time no see", somebody));
    }

}

@Data
@AllArgsConstructor
class GreetResponse {
    private String greeting;
}
