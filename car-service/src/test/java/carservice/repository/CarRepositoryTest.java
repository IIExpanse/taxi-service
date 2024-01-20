package carservice.repository;

import carservice.entity.Car;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("/db/RESET_AUTO_INCREMENT.sql")
class CarRepositoryTest {
    @Autowired
    private CarRepository carRepository;

    @Nested
    class SaveCar {
        @Test
        void shouldSaveCar() {
            val car = Car.builder()
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();

            val actual = carRepository.save(car);

            val expected = Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            assertEquals(expected, actual);
            assertEquals(1, carRepository.count());
        }
    }

    @Nested
    class FindCarById {
        @Test
        void shouldFindCarById() {
            val car = Car.builder()
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            carRepository.save(car);

            val actual = carRepository.findById(1);

            val expected = Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            assertTrue(actual.isPresent());
            assertEquals(expected, actual.get());
        }
    }

    @Nested
    class UpdateCar {
        @Test
        void shouldUpdateCar() {
            val car = Car.builder()
                    .brand("Opel")
                    .model("Corsa")
                    .mileage(300000)
                    .productionDate("01.2010")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            carRepository.save(car);

            val carUpdate = Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            val actual = carRepository.save(carUpdate);

            val expected = Car.builder()
                    .id(1)
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            assertEquals(expected, actual);
            assertEquals(expected, carRepository.findById(1).get());
        }
    }

    @Nested
    class DeleteCar {
        @Test
        void shouldDeleteCar() {
            val car = Car.builder()
                    .brand("Bentley")
                    .model("Continental")
                    .mileage(0)
                    .productionDate("01.2024")
                    .dateCreation(Instant.parse("2021-03-01T12:15:30Z"))
                    .build();
            carRepository.save(car);

            carRepository.deleteById(1);

            assertEquals(0, carRepository.count());
        }
    }

}
