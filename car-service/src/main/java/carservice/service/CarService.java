package carservice.service;

import carservice.dto.CarRequestDto;
import carservice.entity.Car;
import carservice.exception.NotFoundException;
import carservice.repository.CarRepository;
import carservice.time.DateTimeProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;


@Service
@Validated
public class CarService {
    private final CarRepository carRepository;
    private final DateTimeProvider dateTimeProvider;

    @Autowired
    public CarService(CarRepository carRepository, DateTimeProvider dateTimeProvider) {
        this.carRepository = carRepository;
        this.dateTimeProvider = dateTimeProvider;
    }

    public Car createCar(@Valid final CarRequestDto carRequestDto) {
        Car car = Car.builder()
                .brand(carRequestDto.getBrand())
                .model(carRequestDto.getModel())
                .mileage(carRequestDto.getMileage())
                .productionDate(carRequestDto.getProductionDate())
                .createdAt(dateTimeProvider.nowAsInstant())
                .build();
        return carRepository.save(car);
    }

    public Car getCarById(final long id) {
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found"));
    }

    public Car updateCar(@Valid CarRequestDto carRequestDto) {
        Car car = carRepository.findById(carRequestDto.getId()).orElseThrow(() -> new NotFoundException("Car not found"));
        car.setBrand(carRequestDto.getBrand());
        car.setModel(carRequestDto.getModel());
        car.setMileage(carRequestDto.getMileage());
        car.setProductionDate(carRequestDto.getProductionDate());
        return carRepository.save(car);
    }

    public Car deleteCar(final long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not found"));
        carRepository.deleteById(id);
        return car;
    }
}
