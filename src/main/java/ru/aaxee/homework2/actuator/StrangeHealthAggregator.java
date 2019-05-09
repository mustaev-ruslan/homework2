package ru.aaxee.homework2.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StrangeHealthAggregator implements HealthAggregator {
    @Override
    public Health aggregate(Map<String, Health> healths) {
        Health.Builder builder = new Health.Builder();
        int upCount = 0;
        for (String indictorName : healths.keySet()) {
            builder.withDetail(indictorName, healths.get(indictorName));
            if (healths.get(indictorName).getStatus() == Status.UP) {
                upCount++;
            }
        }
        if (upCount > 3) {
            builder.up();
        } else {
            builder.down();
        }
        return builder.build();
    }
}
