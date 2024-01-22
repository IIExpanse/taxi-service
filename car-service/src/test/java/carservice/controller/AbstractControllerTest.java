package carservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class AbstractControllerTest {
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mvc;

    @SneakyThrows
    protected ResultActions sendData(final HttpMethod method,
                                     final String url,
                                     final Object object) {
        final var request = MockMvcRequestBuilders.request(method, url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(object));
        return mvc.perform(request).andExpect(status().isOk());
    }

    @SneakyThrows
    protected <T> T sendData(final HttpMethod method,
                             final String url,
                             final Object object,
                             final Class<T> clazz) {
        final var actualString = sendData(method, url, object)
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        return objectMapper.readValue(actualString, clazz);
    }

    @SneakyThrows
    protected ResultActions testNotFound(final HttpMethod method,
                                         final String url) {
        return mvc.perform(MockMvcRequestBuilders.request(method, url))
                .andExpect(status().isNotFound());
    }

    @SneakyThrows
    protected ResultActions testNotFound(final HttpMethod method,
                                         final String url, final Object object) {
        final var request = MockMvcRequestBuilders.request(method, url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(object));
        return mvc.perform(request).andExpect(status().isNotFound());
    }

    @SneakyThrows
    protected <T> T getData(final String url,
                            final Class<T> clazz) {
        final String actualString = mvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        return objectMapper.readValue(actualString, clazz);
    }

}