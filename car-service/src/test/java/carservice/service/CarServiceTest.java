package carservice.service;

import carservice.dto.CarRequestDto;
import carservice.entity.Car;
import carservice.exception.NotFoundException;
import carservice.repository.CarRepository;
import carservice.time.DateTimeProvider;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class CarServiceTest {
    @Mock
    private CarRepository carRepository;
    @Mock
    private DateTimeProvider dateTimeProvider;
    @InjectMocks
    private CarService carService;

    @Nested
    class CreateCar {
        @Test
        void shouldCreateCar() {
            when(dateTimeProvider.nowAsInstant()).thenReturn(Instant.parse("2021-03-01T12:15:30Z"));
            val returnCar = Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            when(carRepository.save(any())).thenReturn(returnCar);

            val carRequestDto = CarRequestDto.builder()
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .build();
            val actual = carService.createCar(carRequestDto);

            val expected = Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class GetCarById {
        @Test
        void shouldGetCarById() {
            val returnCar = Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            when(carRepository.findById(1)).thenReturn(Optional.of(returnCar));

            val actual = carService.getCarById(1);

            val expected = Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            assertEquals(expected, actual);
        }

        @Test
        void shouldThrowExceptionWhenClientNotExistById() {
            when(carRepository.findById(1)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> carService.getCarById(1));
        }
    }

    @Nested
    class UpdateCar {
        @Test
        void shouldUpdateCar() {
            val returnCar = Car.builder()
                    .id(1)
                    .brand("Opel")
                    .model("Corsa")
                    .mileage(300000)
                    .productionDate("01.2010")
                    .build();
            when(carRepository.findById(1)).thenReturn(Optional.of(returnCar));
            when(carRepository.save(any())).thenReturn(returnCar);

            val carRequestDto = CarRequestDto.builder()
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .build();
            val actual = carService.updateCar(1, carRequestDto);

            val expected = Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .build();
            assertEquals(expected, actual);
        }

        @Test
        void shouldThrowExceptionWhenClientNotExistById() {
            when(carRepository.findById(1)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> carService.updateCar(1, null));
        }
    }

    @Nested
    class DeleteCar {
        @Test
        void shouldDeleteCar() {
            val returnCar = Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .build();
            when(carRepository.findById(1)).thenReturn(Optional.of(returnCar));

            val actual = carService.deleteCar(1);

            val expected = Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .build();
            assertEquals(expected, actual);
        }

        @Test
        void shouldThrowExceptionWhenClientNotExistById() {
            when(carRepository.findById(1)).thenReturn(Optional.empty());

            assertThrows(NotFoundException.class, () -> carService.deleteCar(1));
        }
    }

}
