package com.car_service.vehicle.service.vehicle;


import com.car_service.vehicle.service.vehicle.dto.VehicleDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VehicleService {

    List<VehicleDto> findAllVehicles();

    VehicleDto findVehicleById(Long id);

    List<VehicleDto> findVehicleByCustomerId(Long id);

    VehicleDto findVehicleByVIN(String vin);

    VehicleDto findVehicleByRegistrationNumber(String registrationNumber);

    VehicleDto createVehicle(VehicleDto vehicleDto);

    VehicleDto updateVehicle(VehicleDto vehicleDto, String registrationNumber);

    void mvcUpdateVehicle(VehicleDto vehicleDto);

    void deleteVehicleByRegistrationNumber(String registrationNumber);

    Page<VehicleDto> findVehiclesPaginated(int pageNo, int pageSize);

}


