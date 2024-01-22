package ru.aston.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.aston.entity.Route;

@Repository
public interface RoutesRepository extends CrudRepository<Route, Long> {
    int countRoutesByFromAndTo(String from, String to);
    int countRoutesByFrom(String from);
    int countRoutesByTo(String to);
}
