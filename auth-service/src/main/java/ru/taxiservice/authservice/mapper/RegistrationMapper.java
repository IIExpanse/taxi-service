package ru.taxiservice.authservice.mapper;

import org.mapstruct.Mapper;
import ru.taxiservice.authservice.dto.RegistrationRequest;
import ru.taxiservice.authservice.entity.User;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {
    User toEntity(RegistrationRequest request);
}
