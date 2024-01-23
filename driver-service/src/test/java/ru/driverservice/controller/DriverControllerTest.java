package ru.driverservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.driverservice.dto.DriverDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Test
    void testCreateDriver() throws Exception {
        mockMvc.perform(post("/drivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getDefaultDriver())))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetDriverById() throws Exception {
        DriverDto driverDto = getDefaultDriver();
        createDriver(driverDto);

        mockMvc.perform(get("/drivers/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(driverDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(driverDto.getLastName()))
                .andExpect(jsonPath("$.dateOfBirth").value(driverDto.getDateOfBirth().format(formatter)))
                .andExpect(jsonPath("$.email").value(driverDto.getEmail()))
                .andExpect(jsonPath("$.address").value(driverDto.getAddress()))
                .andExpect(jsonPath("$.salary").value(driverDto.getSalary()));
    }

    @Test
    void testUpdateDriver() throws Exception {
        DriverDto driverDto = getDefaultDriver();
        createDriver(driverDto);

        driverDto.setId(1L);
        driverDto.setFirstName("UpdatedJohn");
        driverDto.setLastName("UpdatedDoe");

        mockMvc.perform(put("/drivers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(driverDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(driverDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(driverDto.getLastName()));
    }

    @Test
    public void testDeleteDriver() throws Exception {
        createDriver(getDefaultDriver());
        mockMvc.perform(delete("/drivers/{id}", 1))
                .andExpect(status().isOk());
        mockMvc.perform(get("/drivers/{id}", 1))
                .andExpect(status().isNotFound());
    }

    private DriverDto getDefaultDriver() {
        return DriverDto.builder()
                .firstName("John")
                .lastName("Doe")
                .fatherName("Smith")
                .dateOfBirth(LocalDate.of(1997, 10, 1))
                .email("johnDoe@example.com")
                .address("Россия, Москва, 224001")
                .salary(35000)
                .build();
    }

    private void createDriver(DriverDto driverDto) throws Exception {
        mockMvc.perform(post("/drivers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(driverDto)));
    }
}
