package carservice.time;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DateTimeProvider {

    public Instant nowAsInstant() {
        return Instant.now();
    }
}