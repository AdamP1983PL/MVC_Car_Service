package com.car_service.vehicle.model.vehicle.repository;


import com.car_service.vehicle.model.vehicle.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findVehicleByVehicleIdentificationNumber(String vin);

    Optional<Vehicle> findVehicleByRegistrationNumber(String registrationNumber);

}
