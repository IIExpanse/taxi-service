package ru.taxiservice.authservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
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
import ru.taxiservice.authservice.dto.AuthenticationRequest;
import ru.taxiservice.authservice.dto.RegistrationRequest;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AllArgsConstructor(onConstructor_ = @Autowired)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    private MockMvc mvc;
    private ObjectMapper mapper;

    @Test
    void login() throws Exception {
        RegistrationRequest regRequest = getDefaultRequest();
        register(regRequest);
        AuthenticationRequest authRequest = new AuthenticationRequest(regRequest.username(), regRequest.password());

        MockHttpServletResponse response = mvc.perform(
                post("/auth", mapper.writeValueAsString(regRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(authRequest))
        ).andReturn().getResponse();

        Map<String, String> res = mapper.readValue(
                response.getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });

        assertEquals(authRequest.username(), res.get("username"));
        assertNotNull(res.get("token"));
    }

    private void register(RegistrationRequest request) throws Exception {
        mvc.perform(
                post("/reg", mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
        ).andReturn().getResponse();
    }

    private RegistrationRequest getDefaultRequest() {
        return new RegistrationRequest(
                "user",
                "password",
                "John",
                "Dow",
                "ROLE_USER"
        );
    }
}