package carservice.time;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.OffsetDateTime;

@Component
public class DateTimeProvider {


    public OffsetDateTime nowAsOffsetDateTime() {
        return OffsetDateTime.now();
    }

    public Instant nowAsInstant() {
        return Instant.now();
    }


}