package com.car_service.vehicle.model.vehicle.repository;


import com.car_service.customer.model.customer.domain.Customer;
import com.car_service.vehicle.model.vehicle.domain.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findVehicleByVehicleIdentificationNumber(String vin);

    Optional<Vehicle> findVehicleByRegistrationNumber(String registrationNumber);

    List<Vehicle> findVehicleByCustomer(Customer customer);

    Page<Vehicle> findAll(Pageable pageable);

}
