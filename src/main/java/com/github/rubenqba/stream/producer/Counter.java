package com.github.rubenqba.stream.producer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class Counter {
    private String name;
    private Integer count;

    @JsonCreator
    public Counter(
            @JsonProperty("name") String name,
            @JsonProperty("count") Integer count) {
        this.name = name;
        this.count = count;
    }
}
