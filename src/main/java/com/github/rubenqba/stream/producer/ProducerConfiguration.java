package com.github.rubenqba.stream.producer;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
public class ProducerConfiguration {

    private final Random random = new SecureRandom();

//    @Bean
    public Supplier<Message<?>> intGenerator() {
        return () -> MessageBuilder.withPayload(random.nextInt(100))
                .setHeaderIfAbsent("type", "ints")
                .build();
    }

//    @Bean
    public Supplier<Message<?>> events(CounterHolder holder) {
        return () -> {
            final var event = new Event<>("counter", holder.getCounter("COUNTER" + random.nextInt(10)), Instant.now());
            return MessageBuilder.withPayload(event)
                    .setHeader("type", "counter")
                    .build();
        };
    }
}

