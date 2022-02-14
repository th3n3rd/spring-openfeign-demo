package com.example.consumer;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClientBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
class PactClientHelper {
    private final static ErrorDecoder defaultDecoder = new ErrorDecoder.Default();
    private final FeignClientBuilder clientBuilder;

    PactClientHelper(ApplicationContext context) {
        this.clientBuilder = new FeignClientBuilder(context);
    }

    <T> T createClient(Class<T> type, String name, String url) {
        return clientBuilder
            .forType(type, name)
            .customize(customizer -> customizer.errorDecoder(PactClientHelper.ErrorDecoder))
            .url(url)
            .build();
    }

    void runClean(Runnable code) {
        try {
            code.run();
        } catch (UnexpectedRequest e) {
            // ignore it to allow the test instrumentation to work properly
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final static ErrorDecoder ErrorDecoder = (String methodKey, Response response) -> {
        if (response.headers().containsKey("X-Pact-Unexpected-Request")) {
            return new UnexpectedRequest();
        }
        return defaultDecoder.decode(methodKey, response);
    };

    private static class UnexpectedRequest extends RuntimeException {}
}
