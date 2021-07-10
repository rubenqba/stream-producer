package com.github.rubenqba.stream.producer;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CounterHolder {
    private final Map<String, Counter> counters = new ConcurrentHashMap<>();

    public Counter getCounter(String key) {
        counters.putIfAbsent(key, new Counter(key, 0));
        return counters.get(key);
    }

    public void update(String key) {
        counters.computeIfPresent(key, (k, counter) -> {
            counter.setCount(counter.getCount() + 1);
            return counter;
        });
    }
}
