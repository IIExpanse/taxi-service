package carservice.controller;


import carservice.dto.CarRequestDto;
import carservice.dto.CarResponseDto;
import carservice.entity.Car;
import carservice.exception.NotFoundException;
import carservice.service.CarService;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(CarController.class)
class CarControllerTest extends AbstractControllerTest {
    @MockBean
    private CarService carService;

    @Nested
    class GetCar {
        @Test
        void shouldGetCarWhenClientExistById() {
            when(carService.getCarById(1)).thenReturn(
                    Car.builder()
                            .id(1)
                            .brand("Bentley")
                            .model("Continental")
                            .mileage(0)
                            .productionDate("01.2024")
                            .build());

            val actual = getData("/api/cars/1", CarResponseDto.class);
            val expected = CarResponseDto.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .build();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnExceptionWhenIdIsNotFound() {
            when(carService.getCarById(1)).thenThrow(new NotFoundException("Car not found"));

            val actual = testNotFound(HttpMethod.GET, "/api/cars/1");
            assertEquals(404, actual.andReturn().getResponse().getStatus());
        }
    }

    @Nested
    class PostCar {
        @Test
        void shouldPostCarWhenCarExistById() {
            val carRequestDto = CarRequestDto.builder()
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .build();

            when(carService.createCar(carRequestDto)).thenReturn(
                    Car.builder()
                            .id(1)
                            .brand("Bentley")
                            .model("Continental")
                            .mileage(0)
                            .productionDate("01.2024")
                            .build());

            val actual = sendData(HttpMethod.POST, "/api/cars", carRequestDto, CarResponseDto.class);

            val expected = CarResponseDto.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .build();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class PutCar {
        @Test
        void shouldPutCarWhenCarExistById() {
            val carRequestDto = CarRequestDto.builder()
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .build();
            when(carService.updateCar(1, carRequestDto)).thenReturn(Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .build());

            val actual = sendData(HttpMethod.PUT, "/api/cars/1", carRequestDto, CarResponseDto.class);

            val expected = CarResponseDto.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .build();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnExceptionWhenIdIsNotFound() {
            val carRequestDto = CarRequestDto.builder()
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .build();
            when(carService.updateCar(1, carRequestDto))
                    .thenThrow(new NotFoundException("Car not found"));

            val actual = testNotFound(HttpMethod.PUT, "/api/cars/1", carRequestDto);

            assertEquals(404, actual.andReturn().getResponse().getStatus());
        }

    }

    @Nested
    class DeleteClient {
        @Test
        void shouldDeleteCarWhenCarExistById() {
            when(carService.deleteCar(1)).thenReturn(Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .build());

            val actual = sendData(HttpMethod.DELETE, "/api/cars/1", null, CarResponseDto.class);

            val expected = CarResponseDto.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .build();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnExceptionWhenIdIsNotFound() {
            when(carService.deleteCar(1)).thenThrow(new NotFoundException("Car not found"));

            testNotFound(HttpMethod.DELETE, "/api/cars/1");
        }
    }

}


