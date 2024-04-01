package com.car_service.vehicle.service.vehicle.mapper;

import com.car_service.customer.model.customer.domain.Customer;
import com.car_service.customer.service.customer.CustomerService;
import com.car_service.customer.service.customer.dto.CustomerDto;
import com.car_service.customer.service.customer.mapper.CustomerMapper;
import com.car_service.vehicle.model.vehicle.domain.Vehicle;
import com.car_service.vehicle.service.vehicle.dto.VehicleDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VehicleMapper {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public Vehicle mapToVehicle(VehicleDto vehicleDto) {
        return Vehicle.builder()
                .id(vehicleDto.getId())
                .registrationNumber(vehicleDto.getRegistrationNumber())
                .vehicleIdentificationNumber(vehicleDto.getVehicleIdentificationNumber())
                .manufacturer(vehicleDto.getManufacturer())
                .model(vehicleDto.getModel())
                .productionYear(vehicleDto.getProductionYear())
                .mileage(vehicleDto.getMileage())
                .engineType(vehicleDto.getEngineType())
                .gearboxType(vehicleDto.getGearboxType())
                .additionalInformation(vehicleDto.getAdditionalInformation())
                .customer(getCustomerByVehicle(vehicleDto))
                .build();
    }

    public VehicleDto mapToVehicleDto(Vehicle vehicle) {
        return VehicleDto.builder()
                .id(vehicle.getId())
                .registrationNumber(vehicle.getRegistrationNumber())
                .vehicleIdentificationNumber(vehicle.getVehicleIdentificationNumber())
                .manufacturer(vehicle.getManufacturer())
                .model(vehicle.getModel())
                .productionYear(vehicle.getProductionYear())
                .mileage(vehicle.getMileage())
                .engineType(vehicle.getEngineType())
                .gearboxType(vehicle.getGearboxType())
                .additionalInformation(vehicle.getAdditionalInformation())
                .customerId(vehicle.getCustomer().getId())
                .build();
    }

    public Customer getCustomerByVehicle(VehicleDto vehicleDto) {
        Long customerId = vehicleDto.getCustomerId();
        CustomerDto customerDto = customerService.findCustomerById(customerId);
        return customerMapper.mapToCustomer(customerDto);
    }

}
