package ru.taxiservice.authservice.mapper;

import org.mapstruct.Mapper;
import ru.taxiservice.authservice.dto.UserDto;
import ru.taxiservice.authservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
