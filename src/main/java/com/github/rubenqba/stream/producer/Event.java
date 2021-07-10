package com.github.rubenqba.stream.producer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
class Event<T> {
    private final String type;
    private final T body;
    private final Instant createdAt;

    @JsonCreator
    public Event(
            @JsonProperty("type") String type,
            @JsonProperty("body") T body,
            @JsonProperty("createAt") Instant createdAt) {
        this.type = type;
        this.body = body;
        this.createdAt = createdAt;
    }
}
