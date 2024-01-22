package ru.aston.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDto {
    private Long id;
    @NotNull
    private Long driverId;
    @NotNull
    private Long carId;
    @NotNull
    private Long clientId;
    @NotNull
    private Route route;

    public OrderDto(Long driverId, Long carId, Long clientId, Route route) {
        this.driverId = driverId;
        this.carId = carId;
        this.clientId = clientId;
        this.route = route;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class Route{
        private String from;
        private String to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(driverId, orderDto.driverId)
                && Objects.equals(carId, orderDto.carId)
                && Objects.equals(clientId, orderDto.clientId)
                && Objects.equals(route.to, orderDto.route.to)
                && Objects.equals(route.from, orderDto.route.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId, carId, clientId, route.from, route.to);
    }
}
