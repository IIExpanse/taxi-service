package ru.taxiservice.authservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private String username;

    private String firstName;

    private String lastName;

}
