package ru.driverservice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDto {
    private Long id;

    @NotNull(message = "First Name cannot be empty")
    @Pattern(regexp = "^[^0-9]+$")
    private String firstName;

    @NotNull(message = "Last name cannot be empty")
    @Pattern(regexp = "^[^0-9]+$")
    private String lastName;

    @Pattern(regexp = "^[^0-9]+$")
    private String fatherName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    private Integer age;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Address cannot be empty")
    @NotBlank(message = "The address cannot consist only of spaces")
    @Pattern(regexp = "[А-Я]\\w+, [А-Я]\\w+, \\d{6}")
    private String address;

    @NotNull(message = "Salary cannot be empty")
    @Positive
    @DecimalMin("19242")
    private Integer salary;
}
