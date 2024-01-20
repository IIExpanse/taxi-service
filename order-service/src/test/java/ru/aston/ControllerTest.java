package ru.aston;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.aston.controller.OrderController;
import ru.aston.domain.dto.OrderDto;
import ru.aston.service.serviceApi.OrderService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class ControllerTest {
    private final ObjectMapper objectMapper;
    @MockBean
    private OrderService service;
    private final MockMvc mvc;
    private static OrderDto dto;
    private static OrderDto dto2;

    @BeforeEach
    public void setUp() {
        dto = new OrderDto(1L, 1L, 1L, new OrderDto.Route("aaa", "bbb"));
        dto2 = new OrderDto(2L, 2L, 2L, new OrderDto.Route("222", "333"));
    }

    @Test
    void createOrder() throws Exception {
        when(service.create(any(OrderDto.class))).thenReturn(dto);
        mvc.perform(post("/orders")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        status().isCreated(),
                        content().json(objectMapper.writeValueAsString(dto))
                );
    }



    @Test
    void getByID() throws Exception {
        when(service.read(anyLong())).thenReturn(dto);
        mvc.perform(get("/orders/1"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(dto))
                );
    }


    @Test
    void update() throws Exception {
        when(service.update(any(OrderDto.class))).thenReturn(dto);
        mvc.perform(put("/orders")
                        .content(objectMapper.writeValueAsString(dto2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().json(objectMapper.writeValueAsString(dto))
                );
    }

    @Test
    void deleteUser() throws Exception {
        mvc.perform(delete("/orders/1"))
                .andDo(print())
                .andExpectAll(
                        status().isOk()
                );
        verify(service, times(1)).delete(1L);
    }

}