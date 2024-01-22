import carservice.ClientApplication;
import carservice.controller.AbstractControllerTest;
import carservice.dto.CarResponseDto;
import carservice.entity.Car;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ClientApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CarControllerTest extends AbstractControllerTest {
    @Test
    void testCreateCar() {
        final Car car = getDefaultCar();
        final CarResponseDto carResponseDto = sendData(HttpMethod.POST, "/api/cars", car, CarResponseDto.class);
        assertEquals(car.getBrand(), carResponseDto.getBrand());
    }

    @Test
    void testGetCarById() {
        addCar(getDefaultCar());
        final CarResponseDto carResponseDto = getData("/api/cars/1", CarResponseDto.class);
        assertEquals("Bentley", carResponseDto.getBrand());
    }

    @Test
    void testUpdateCar() {
        addCar(getDefaultCar());
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
        addCar(getDefaultCar());
        final CarResponseDto carResponseDto =
                sendData(HttpMethod.DELETE, "/api/cars/1", null, CarResponseDto.class);
        assertEquals("Bentley", carResponseDto.getBrand());
    }

    private void addCar(Car car) {
        sendData(HttpMethod.POST, "/api/cars", car, CarResponseDto.class);
    }

    private Car getDefaultCar() {
        return Car.builder()
                .brand("Bentley")
                .model("Continental")
                .mileage(0)
                .productionDate("01.2024")
                .build();
    }
}


