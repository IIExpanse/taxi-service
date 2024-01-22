package ru.aston;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.aston.domain.dto.OrderDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ControllerTest {
    private final ObjectMapper objectMapper;
    private final MockMvc mvc;

    @Test
    void createOrder() throws Exception {
        OrderDto dto = getDefaultDto();
        dto.setId(1L);

        mvc.perform(post("/orders")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isCreated(),
                        content().json(objectMapper.writeValueAsString(dto))
                );
    }



    @Test
    void getByID() throws Exception {
        OrderDto dto = getDefaultDto();
        addOrder(dto);
        dto.setId(1L);

        mvc.perform(get("/orders/1"))
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(dto))
                );
    }


    @Test
    void update() throws Exception {
        addOrder(getDefaultDto());
        OrderDto dto2 = getDefaultDto();
        dto2.setId(1L);
        dto2.setCarId(2L);

        mvc.perform(put("/orders")
                        .content(objectMapper.writeValueAsString(dto2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(dto2))
                );
    }

    @Test
    void deleteOrder() throws Exception {
        addOrder(getDefaultDto());

        mvc.perform(delete("/orders/1"))
                .andExpectAll(
                        status().isOk()
                );
        mvc.perform(get("/orders/1"))
                .andExpect(status().isNotFound());
    }

    private void addOrder(OrderDto dto) throws Exception {
        mvc.perform(post("/orders")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON));
    }

    private OrderDto getDefaultDto() {
        return new OrderDto(1L, 1L, 1L, new OrderDto.Route("aaa", "bbb"));
    }
}