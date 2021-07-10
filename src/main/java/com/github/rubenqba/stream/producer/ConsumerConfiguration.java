package com.github.rubenqba.stream.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class ConsumerConfiguration {

    @Bean
    public Consumer<Integer> ints() {
        return value -> log.info("integer: {}", value);
    }

    @Bean
    public Consumer<Event<Counter>> counter(CounterHolder holder) {
        return event -> {
            log.info("counter: {}", event);
            holder.update(event.getBody().getName());
        };
    }
}
