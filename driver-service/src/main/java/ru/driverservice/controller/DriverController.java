package ru.driverservice.controller;


import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.driverservice.dto.DriverDto;
import ru.driverservice.mapper.DriverMapper;
import ru.driverservice.service.DriverService;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
@RequestMapping("/drivers")
public class DriverController {
    DriverService driverService;
    DriverMapper driverMapper;

    @GetMapping("/{id}")
    public DriverDto getDriverById(@PathVariable Long id) {
        return driverMapper.entityToDriverDto(driverService.getDriverById(id));
    }

    @PostMapping
    public DriverDto createDriver(@Valid @RequestBody DriverDto driverDto) {
        return driverMapper.entityToDriverDto(driverService.createDriver(driverDto));
    }

    @PutMapping
    public DriverDto updateDriver(@Valid @RequestBody DriverDto driverDto) {
        return driverMapper.entityToDriverDto(driverService.updateDriver(driverDto));
    }

    @DeleteMapping("/{id}")
    public DriverDto deleteDriver(@PathVariable Long id) {
        return driverMapper.entityToDriverDto(driverService.deleteDriver(id));
    }
}
