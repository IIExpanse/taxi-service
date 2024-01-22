package ru.taxiservice.authservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.taxiservice.authservice.dto.RegistrationRequest;
import ru.taxiservice.authservice.dto.UserDto;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AllArgsConstructor(onConstructor_ = @Autowired)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class RegistrationControllerTest {
    private MockMvc mvc;
    private ObjectMapper mapper;

    @Test
    void register() throws Exception {
        RegistrationRequest request = new RegistrationRequest(
                "user",
                "password",
                "John",
                "Dow",
                "ROLE_USER"
        );
        UserDto userDto = new UserDto();
        userDto.setUsername(request.username());
        userDto.setFirstName(request.firstName());
        userDto.setLastName(request.lastName());

        MockHttpServletResponse response = mvc.perform(
                post("/reg", mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
        ).andReturn().getResponse();

        assertEquals(response.getContentAsString(StandardCharsets.UTF_8), mapper.writeValueAsString(userDto));
    }
}