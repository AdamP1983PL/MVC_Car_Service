package com.carservice.vehicle.service.vehicle;


import com.carservice.vehicle.service.vehicle.dto.VehicleDto;

import java.util.List;

public interface VehicleService {

    List<VehicleDto> findAllVehicles();

    VehicleDto findVehicleById(Long id);

    VehicleDto findVehicleByVIN(String vin);

    VehicleDto findVehicleByRegistrationNumber(String registrationNumber);

    VehicleDto createVehicle(VehicleDto vehicleDto);

    VehicleDto updateVehicle(VehicleDto vehicleDto, String registrationNumber);

    void mvcUpdateVehicle(VehicleDto vehicleDto);

    void deleteVehicleByRegistrationNumber(String registrationNumber);

}


