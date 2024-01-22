package carservice.controller;

import carservice.converter.CarToCarResponseDto;
import carservice.dto.CarRequestDto;
import carservice.dto.CarResponseDto;
import carservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;
    private final CarToCarResponseDto converter;

    @GetMapping("/{id}")
    public CarResponseDto getCar(@PathVariable final int id) {
        return converter.convert(carService.getCarById(id));
    }

    @PostMapping
    public CarResponseDto postCar(@RequestBody final CarRequestDto carRequestDto) {
        return converter.convert(carService.createCar(carRequestDto));
    }

    @PutMapping("/{id}")
    public CarResponseDto putClient(@PathVariable final int id, @RequestBody final CarRequestDto carRequestDto) {
        return converter.convert(carService.updateCar(id, carRequestDto));
    }

    @DeleteMapping("/{id}")
    public CarResponseDto deleteCar(@PathVariable final int id) {
        return converter.convert(carService.deleteCar(id));
    }
}
