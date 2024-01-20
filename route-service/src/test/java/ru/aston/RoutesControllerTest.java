package ru.aston;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import ru.aston.controller.RoutesController;
import ru.aston.dto.RouteDto;
import ru.aston.dto.RouteStatDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AllArgsConstructor(onConstructor_ = @Autowired)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Sql(scripts = "classpath:schema-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RoutesControllerTest {
    private RoutesController controller;

    public void fillRoutes() {
        controller.addRoute(new RouteDto("адрес 1", "адрес 2"));
        controller.addRoute(new RouteDto("адрес 1", "адрес 2"));
        controller.addRoute(new RouteDto("адрес 1", "адрес 3"));
        controller.addRoute(new RouteDto("адрес 2", "адрес 3"));
    }

    @Test
    public void addCategoryTest() {
        RouteDto requestDto = new RouteDto("адрес 1", "адрес 2");
        RouteDto responseDto = controller.addRoute(requestDto);

        assertEquals(requestDto, responseDto);
    }

    @Test
    public void getRouteStatFromTo() {
        fillRoutes();
        String from = "адрес 1";
        String to = "адрес 2";

        RouteStatDto routeStatDto = new RouteStatDto(from, to, 2);
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(routeStatDto, HttpStatus.OK);

        assertEquals(expectedResponse, controller.getRouteStat(from, to));
    }

    @Test
    public void getRouteStatFrom() {
        fillRoutes();
        String from = "адрес 1";
        String to = null;

        RouteStatDto routeStatDto = new RouteStatDto(from, to, 3);
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(routeStatDto, HttpStatus.OK);

        assertEquals(expectedResponse, controller.getRouteStat(from, to));
    }

    @Test
    public void getRouteStatTo() {
        fillRoutes();
        String from = null;
        String to = "адрес 3";

        RouteStatDto routeStatDto = new RouteStatDto(from, to, 2);
        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(routeStatDto, HttpStatus.OK);

        assertEquals(expectedResponse, controller.getRouteStat(from, to));
    }

    @Test
    public void getRouteStatNull() {
        fillRoutes();
        String from = null;
        String to = null;

        ResponseEntity<Object> expectedResponse = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        assertEquals(expectedResponse, controller.getRouteStat(from, to));
    }
}
