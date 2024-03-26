package com.carservice.vehicle.service.vehicle;

import com.carservice.exceptions.RegistrationNumberAlreadyExistsException;
import com.carservice.exceptions.ResourceNotFoundException;
import com.carservice.exceptions.VINAlreadyExistsException;
import com.carservice.vehicle.model.vehicle.domain.Vehicle;
import com.carservice.vehicle.model.vehicle.repository.VehicleRepository;
import com.carservice.vehicle.service.vehicle.dto.VehicleDto;
import com.carservice.vehicle.service.vehicle.mapper.VehicleMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Slf4j
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public List<VehicleDto> findAllVehicles() {
        log.info("====>>>> findAllVehicles() execution.");
        return vehicleRepository.findAll().stream()
                .map(vehicleMapper::mapToVehicleDto)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDto findVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", Long.toString(id)));
        log.info("====>>>> findVehicleById(" + id + ") execution.");
        return vehicleMapper.mapToVehicleDto(vehicle);
    }

    @Override
    public VehicleDto findVehicleByVIN(String vin) {
        Vehicle vehicle = vehicleRepository.findVehicleByVehicleIdentificationNumber(vin)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle", "VIN number", vin));

        log.info("====>>>> findVehicleByVINNumber(" + vin + ") execution.");
        return vehicleMapper.mapToVehicleDto(vehicle);
    }

    @Override
    public VehicleDto findVehicleByRegistrationNumber(String registrationNumber) {
        Vehicle vehicle = vehicleRepository.findVehicleByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle", "Registration Number", registrationNumber));

        log.info("====>>>> findVehicleByRegistrationNumber(" + registrationNumber + ") execution.");
        return vehicleMapper.mapToVehicleDto(vehicle);
    }

    @Override
    public VehicleDto createVehicle(VehicleDto vehicleDto) {
        Optional<Vehicle> vehicle1 = vehicleRepository
                .findVehicleByRegistrationNumber(vehicleDto.getRegistrationNumber());
        if (vehicle1.isPresent()) {
            throw new RegistrationNumberAlreadyExistsException("Registration Number", vehicleDto.getRegistrationNumber());
        }

        Optional<Vehicle> vehicle2 = vehicleRepository
                .findVehicleByVehicleIdentificationNumber(vehicleDto.getVehicleIdentificationNumber());
        if (vehicle2.isPresent()) {
            throw new VINAlreadyExistsException("VIN", vehicleDto.getVehicleIdentificationNumber());
        }

        Vehicle savedVehicle = vehicleRepository.save(vehicleMapper.mapToVehicle(vehicleDto));
        log.info("====>>>> createVehicle() execution.");
        return vehicleMapper.mapToVehicleDto(savedVehicle);
    }

    @Override
    public VehicleDto updateVehicle(VehicleDto vehicleDto, String registrationNumber) {
        Vehicle vehicle = vehicleRepository.findVehicleByRegistrationNumber(registrationNumber)
                .map(veh -> {
                    veh.setVehicleIdentificationNumber(vehicleDto.getVehicleIdentificationNumber());
                    veh.setManufacturer(vehicleDto.getManufacturer());
                    veh.setModel(vehicleDto.getModel());
                    veh.setProductionYear(vehicleDto.getProductionYear());
                    veh.setEngineType(vehicleDto.getEngineType());
                    veh.setGearboxType(vehicleDto.getGearboxType());
                    veh.setAdditionalInformation(vehicleDto.getAdditionalInformation());
                    return vehicleRepository.save(veh);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle", "Registration Number", registrationNumber));

        log.info("====>>>> updateVehicle(" + registrationNumber + ") execution.");
        return vehicleMapper.mapToVehicleDto(vehicle);
    }

    @Override
    public void mvcUpdateVehicle(VehicleDto vehicleDto) {
        log.info("====>>>> mvcUpdateVehicle() execution.");
        vehicleRepository.save(vehicleMapper.mapToVehicle(vehicleDto));
    }

    @Override
    public void deleteVehicleByRegistrationNumber(String registrationNumber) {
        Vehicle vehicle = vehicleRepository.findVehicleByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle", "Registration Number", registrationNumber));

        vehicleRepository.delete(vehicle);
        log.info("====>>>> deleteVehicleByRegistrationNumber(" + registrationNumber + ") execution.");
    }

}
