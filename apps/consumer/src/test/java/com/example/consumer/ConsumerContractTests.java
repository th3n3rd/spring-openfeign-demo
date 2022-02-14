package com.example.consumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.LambdaDsl;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonBody;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
class ConsumerContractTests {

    @Autowired
    private PactClientHelper pactHelper;

    @Pact(consumer = "consumer", provider = "provider")
    public RequestResponsePact greetEverybody(PactDslWithProvider builder) {
        return builder
            .uponReceiving("a request to greet somebody")
            .method("GET")
            .path("/greetings/Everybody")
            .willRespondWith()
            .body(
                newJsonBody(body -> {
                    body.stringType("greeting", "Hello Everybody! long time no see");
                }).build()
            )
            .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "greetEverybody", pactVersion = PactSpecVersion.V3)
    void greetSomebody(MockServer mockServer) {
        pactHelper.runClean(() -> {
            var client = createClient(mockServer);
            var response = client.greet("Everybody");
            assertThat(response.getGreeting()).contains("Hello Everybody");
        });
    }

    private ProviderClient createClient(MockServer mockServer) {
        return pactHelper.createClient(
            ProviderClient.class,
            "provider",
            mockServer.getUrl()
        );
    }
}
