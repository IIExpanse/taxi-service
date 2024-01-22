package ru.aston.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "driver_id")
    private Long driverId;
    @Column(name = "car_id")
    private Long carId;
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "\"from\"")
    private String from;
    @Column(name = "\"to\"")
    private String to;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(driverId, that.driverId) && Objects.equals(carId, that.carId)
                && Objects.equals(clientId, that.clientId) && Objects.equals(from, that.from)
                && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId, carId, clientId, from, to);
    }
}
