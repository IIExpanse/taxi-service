package ru.driverservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "driver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false, length = 20)
    @Pattern(regexp = "^[^0-9]+$")
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 20)
    @Pattern(regexp = "^[^0-9]+$")
    private String lastName;

    @Column(name = "fatherName", length = 20)
    @Pattern(regexp = "^[^0-9]+$")
    private String fatherName;

    @Column(name = "dateOfBirth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "address", nullable = false, unique = true, length = 100)
    @Pattern(regexp = "[А-Я]\\w+, [А-Я]\\w+, \\d{6}")
    private String address;

    @Column(name = "salary", nullable = false)
    private Integer salary;

}
