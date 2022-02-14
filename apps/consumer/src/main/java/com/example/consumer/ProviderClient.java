package com.example.consumer;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "provider", url = "${provider.url}")
interface ProviderClient {

    @GetMapping("/greetings/{somebody}")
    GreetResponse greet(@PathVariable String somebody);

}

@Data
@NoArgsConstructor
class GreetResponse {
    private String greeting;
}
