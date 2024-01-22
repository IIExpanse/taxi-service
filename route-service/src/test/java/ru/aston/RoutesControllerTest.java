package ru.aston;

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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.aston.dto.RouteDto;
import ru.aston.dto.RouteStatDto;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AllArgsConstructor(onConstructor_ = @Autowired)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Sql(scripts = "classpath:schema-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class RoutesControllerTest {
    private MockMvc mvc;
    private ObjectMapper mapper;

    @Test
    public void addRouteTest() throws Exception {
        RouteDto routeDto = getDefaultRoute();
        String json = mapper.writeValueAsString(routeDto);
        mvc.perform(put("/routes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        MockHttpServletResponse response = mvc.perform(get("/routes")
                .param("from", routeDto.from())
                .param("to", routeDto.to()))
                .andReturn().getResponse();
        RouteStatDto statDto = mapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), RouteStatDto.class);

        assertEquals(routeDto.from(), statDto.from());
        assertEquals(routeDto.to(), statDto.to());
        assertEquals(1, statDto.routeStat());
    }

    @Test
    public void getRouteStatNull() throws Exception {
        mvc.perform(get("/routes"))
                .andExpect(status().isBadRequest());
    }

    private RouteDto getDefaultRoute() {
        return new RouteDto("адрес 1", "адрес 2");
    }
}
