package com.github.rubenqba.stream.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class QueueCtrl {

    private final StreamBridge streamBridge;
    private final CounterHolder holder;

    @PostMapping("/{counter}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delegateToSupplier(@PathVariable String counter) {
        final var event = new Event<>("counter", holder.getCounter(counter), Instant.now());
        final var message = MessageBuilder.withPayload(event)
                .setHeader("type", "counter")
                .build();
        streamBridge.send("events-out-0", message);
    }
}
