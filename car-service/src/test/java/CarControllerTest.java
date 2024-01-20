import carservice.ClientApplication;
import carservice.controller.AbstractControllerTest;
import carservice.dto.CarResponseDto;
import carservice.entity.Car;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ClientApplication.class)
@Sql(scripts = "/db/test-data.sql", config = @SqlConfig(commentPrefix = "--#"))
public class CarControllerTest extends AbstractControllerTest {
    @Test
    void testCreateCar() {
        final Car car = Car.builder()
                .brand("Bentley")
                .model("Continental")
                .mileage(0)
                .productionDate("01.2024")
                .build();
        final CarResponseDto carResponseDto = sendData(HttpMethod.POST, "/api/cars", car, CarResponseDto.class);
        assertEquals(car.getBrand(), carResponseDto.getBrand());
    }

    @Test
    void testGetCarById() {
        final CarResponseDto carResponseDto = getData("/api/cars/1", CarResponseDto.class);
        assertEquals("John", carResponseDto.getBrand());
    }

    @Test
    void testUpdateCar() {
        final Car car = Car.builder()
                .brand("Bentley")
                .model("Continental")
                .mileage(0)
                .productionDate("01.2024")
                .build();
        final CarResponseDto carResponseDto =
                sendData(HttpMethod.PUT, "/api/cars/1", car, CarResponseDto.class);

        assertEquals(car.getBrand(), carResponseDto.getBrand());
    }

    @Test
    void testDeleteCar() {
        final CarResponseDto carResponseDto =
                sendData(HttpMethod.DELETE, "/api/cars/1", null, CarResponseDto.class);
        assertEquals("John", carResponseDto.getBrand());
    }
}


