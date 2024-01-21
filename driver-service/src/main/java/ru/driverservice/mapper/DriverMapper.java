package ru.driverservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.driverservice.dto.DriverDto;
import ru.driverservice.entity.DriverEntity;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    DriverDto entityToDriverDto(DriverEntity driver);

    @Mapping(target = "id", ignore = true)
    DriverEntity driverDtoToEntity(DriverDto driverDto);

    @Mapping(target = "id", ignore = true)
    void updateDriverFromDto(DriverDto driverDto, @MappingTarget DriverEntity driverEntity);
}


