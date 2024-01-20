package ru.driverservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.driverservice.dto.DriverDto;
import ru.driverservice.entity.DriverEntity;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    DriverMapper INSTANCE = Mappers.getMapper(DriverMapper.class);

    DriverDto driverToDriverDto(DriverEntity driver);

    DriverEntity driverDtoToDriver(DriverDto driverDto);

    void updateDriverFromDto(DriverDto driverDto, @MappingTarget DriverEntity driverEntity);
}


