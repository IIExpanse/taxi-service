package ru.driverservice.service;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.driverservice.dto.DriverDto;
import ru.driverservice.entity.DriverEntity;
import ru.driverservice.exception.NotFoundException;
import ru.driverservice.mapper.DriverMapper;
import ru.driverservice.repository.DriverRepository;

import java.time.LocalDate;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class DriverService {

    DriverRepository driverRepository;
    DriverMapper driverMapper;

    public DriverEntity getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Driver not found"));
    }

    public DriverEntity createDriver(@Valid DriverDto driverDto) {
        DriverEntity driverEntity = driverMapper.driverDtoToEntity(driverDto);

        int currentYear = LocalDate.now().getYear();
        int birthYear = driverDto.getDateOfBirth().getYear();
        int age = currentYear - birthYear;

        driverEntity.setAge(age);

        return driverRepository.save(driverEntity);
    }

    public DriverEntity updateDriver(@Valid DriverDto driverDto) {
        DriverEntity driverEntity = driverRepository.findById(driverDto.getId())
                .orElseThrow(() -> new NotFoundException("Driver not found"));

        driverMapper.updateDriverFromDto(driverDto, driverEntity);

        int currentYear = LocalDate.now().getYear();
        int birthYear = driverDto.getDateOfBirth().getYear();
        int age = currentYear - birthYear;

        driverEntity.setAge(age);

        return driverRepository.save(driverEntity);
    }

    public DriverEntity deleteDriver(Long id) {
        DriverEntity driverEntity = driverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Driver not found"));

        driverRepository.deleteById(id);

        return driverEntity;
    }
}
