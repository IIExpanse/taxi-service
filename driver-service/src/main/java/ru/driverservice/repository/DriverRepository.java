package ru.driverservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.driverservice.entity.DriverEntity;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> { }
