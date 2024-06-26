package com.car_service.vehicle.service.vehicle;

import com.car_service.customer.model.customer.domain.Customer;
import com.car_service.customer.service.customer.CustomerService;
import com.car_service.customer.service.customer.dto.CustomerDto;
import com.car_service.customer.service.customer.mapper.CustomerMapper;
import com.car_service.exceptions.RegistrationNumberAlreadyExistsException;
import com.car_service.exceptions.ResourceNotFoundException;
import com.car_service.exceptions.VINAlreadyExistsException;
import com.car_service.vehicle.model.vehicle.domain.Vehicle;
import com.car_service.vehicle.model.vehicle.repository.VehicleRepository;
import com.car_service.vehicle.service.vehicle.dto.VehicleDto;
import com.car_service.vehicle.service.vehicle.mapper.VehicleMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

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
    public List<VehicleDto> findVehicleByCustomerId(Long id) {
        CustomerDto customerDto = customerService.findCustomerById(id);
        Customer customer = customerMapper.mapToCustomer(customerDto);
        log.info("====>>>> findVehicleByCustomerId(" + id + ") execution.");
        return vehicleRepository.findVehicleByCustomer(customer).stream()
                .map(vehicleMapper::mapToVehicleDto)
                .collect(Collectors.toList());
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
                    veh.setCustomer(vehicleMapper.getCustomerByVehicle(vehicleDto));
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

    @Override
    public Page<VehicleDto> findVehiclesPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return vehicleRepository.findAll(pageable)
                .map(vehicleMapper::mapToVehicleDto);
    }

}
