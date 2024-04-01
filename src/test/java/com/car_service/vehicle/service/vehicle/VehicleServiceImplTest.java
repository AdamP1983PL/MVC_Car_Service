package com.car_service.vehicle.service.vehicle;

import com.car_service.customer.model.customer.domain.Customer;
import com.car_service.customer.model.customer.repository.CustomerRepository;
import com.car_service.customer.model.enums.PaymentMethod;
import com.car_service.customer.model.enums.TaxValue;
import com.car_service.customer.service.customer.CustomerServiceImpl;
import com.car_service.customer.service.customer.dto.CustomerDto;
import com.car_service.customer.service.customer.mapper.CustomerMapper;
import com.car_service.exceptions.ResourceNotFoundException;
import com.car_service.vehicle.model.enums.EngineType;
import com.car_service.vehicle.model.enums.GearboxType;
import com.car_service.vehicle.model.vehicle.domain.Vehicle;
import com.car_service.vehicle.model.vehicle.repository.VehicleRepository;
import com.car_service.vehicle.service.vehicle.dto.VehicleDto;
import com.car_service.vehicle.service.vehicle.mapper.VehicleMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {
    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private Vehicle updatedVehicle1;
    private VehicleDto vehicleDto1;
    private VehicleDto vehicleDto2;
    private VehicleDto updatedVehicleDto1;
    private Customer customer;
    private CustomerDto customerDto;

    @BeforeEach()
    void initialize() {
        customer = Customer.builder()
                .id(1L)
                .customerName("test customer name")
                .taxNumber("1112223344")
                .country("country")
                .city("city")
                .postalCode("postalCode")
                .street("street")
                .customerEmail("test@test.com")
                .customerPhoneNumber("111-111-111")
                .customerWebsite("www@www.com")
                .isActive(true)
                .paymentIsBlocked(false)
                .paymentMethod(PaymentMethod.CASH)
                .taxValue(TaxValue.TWENTY_THREE)
                .build();

        customerDto = CustomerDto.builder()
                .id(1L)
                .customerName("test customer name")
                .taxNumber("1112223344")
                .country("country")
                .city("city")
                .postalCode("postalCode")
                .street("street")
                .customerEmail("test@test.com")
                .customerPhoneNumber("111-111-111")
                .customerWebsite("www@www.com")
                .isActive(true)
                .paymentIsBlocked(false)
                .paymentMethod(PaymentMethod.CASH)
                .taxValue(TaxValue.TWENTY_THREE)
                .build();

        vehicle1 = Vehicle.builder()
                .registrationNumber("registration 1")
                .vehicleIdentificationNumber("vin 1")
                .manufacturer("manufacturer1")
                .model("model1")
                .productionYear("1111")
                .mileage(11.11)
                .engineType(EngineType.DIESEL)
                .gearboxType(GearboxType.MANUAL)
                .additionalInformation("none1")
                .customer(customer)
                .build();

        vehicle2 = Vehicle.builder()
                .registrationNumber("registration 2")
                .vehicleIdentificationNumber("vin 2")
                .manufacturer("manufacturer2")
                .model("model2")
                .productionYear("2222")
                .mileage(22.22)
                .engineType(EngineType.PETROL)
                .gearboxType(GearboxType.AUTOMATIC)
                .additionalInformation("test information")
                .customer(customer)
                .build();

        vehicleDto1 = VehicleDto.builder()
                .registrationNumber("registration 1")
                .vehicleIdentificationNumber("vin 1")
                .manufacturer("manufacturer1")
                .model("model")
                .productionYear("1111")
                .mileage(11.11)
                .engineType(EngineType.DIESEL)
                .gearboxType(GearboxType.MANUAL)
                .additionalInformation("none1")
                .customerId(customer.getId())
                .build();

        vehicleDto2 = VehicleDto.builder()
                .registrationNumber("registration 2")
                .vehicleIdentificationNumber("vin 2")
                .manufacturer("manufacturer2")
                .model("model2")
                .productionYear("2222")
                .mileage(22.22)
                .engineType(EngineType.PETROL)
                .gearboxType(GearboxType.AUTOMATIC)
                .additionalInformation("test information")
                .customerId(customerDto.getId())
                .build();

        updatedVehicle1 = Vehicle.builder()
                .registrationNumber("registration")
                .vehicleIdentificationNumber("updated vin 1")
                .manufacturer("updated manufacturer1")
                .model("updated model1")
                .productionYear("updated 1111")
                .mileage(11.11)
                .engineType(EngineType.DIESEL)
                .gearboxType(GearboxType.MANUAL)
                .additionalInformation("updated none1")
                .customer(customer)
                .build();

        updatedVehicleDto1 = VehicleDto.builder()
                .registrationNumber("registration")
                .vehicleIdentificationNumber("updated vin 1")
                .manufacturer("updated manufacturer1")
                .model("updated model1")
                .productionYear("updated 1111")
                .mileage(11.11)
                .engineType(EngineType.DIESEL)
                .gearboxType(GearboxType.MANUAL)
                .additionalInformation("updated none1")
                .customerId(customerDto.getId())
                .build();


    }

    @AfterEach
    void cleanUp() {
        vehicleRepository.deleteById(vehicle1.getId());
        vehicleRepository.deleteById(vehicle2.getId());
    }

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMapper vehicleMapper;

    @InjectMocks
    private VehicleServiceImpl vehicleServiceImpl;

    @Test
    @DisplayName("Testing findAllVehicles() method - negative scenario (empty list).")
    public void givenEmptyVehiclesList_whenFindAllVehicles_thenReturnEmptyVehicleDtoList() {
        // given
        given(vehicleRepository.findAll()).willReturn(Collections.emptyList());

        // when
        List<VehicleDto> vehicles = vehicleServiceImpl.findAllVehicles();

        // then
        assertTrue(vehicles.isEmpty());
    }

    @Test
    @DisplayName("Testing findVehicleById() method - positive scenario (valid input)")
    public void givenVehicleId_whenFindVehicleById_thenReturnVehicleDto() {
        // given
        given(vehicleRepository.findById(vehicle1.getId())).willReturn(Optional.ofNullable(vehicle1));
        given(vehicleMapper.mapToVehicleDto(vehicle1)).willReturn(vehicleDto1);

        // when
        VehicleDto testVehicle = vehicleServiceImpl.findVehicleById(vehicleDto1.getId());

        // then
        assertAll(
                () -> assertNotNull(testVehicle),
                () -> assertEquals("registration 1", testVehicle.getRegistrationNumber()),
                () -> assertEquals("manufacturer1", testVehicle.getManufacturer()),
                () -> assertEquals(customer.getId(), testVehicle.getCustomerId())
        );
    }

    @Test
    @DisplayName("Testing findVehicleById() method that throws ResourceNotFoundException.")
    public void givenVehicleId_whenFindVehicleById_thenThrowResourceNotFoundException() {
        // given
        Long id = 222L;
        given(vehicleRepository.findById(id)).willReturn(Optional.empty());

        // when, then
        assertThrows(ResourceNotFoundException.class, () -> {
            vehicleServiceImpl.findVehicleById(id);
        });
        verify(vehicleRepository, times(1)).findById(id);
        verify(vehicleMapper, never()).mapToVehicleDto(any(Vehicle.class));
    }

    @Test
    @DisplayName("Testing findVehicleByCustomerId() method - positive scenario (valid input)")
    public void givenCustomerId_whenFindVehicleByCustomerId_thenReturnVehicleDtoList() {
        // given
        given(customerRepository.findById(customerDto.getId())).willReturn(Optional.ofNullable(customer));
        given(customerMapper.mapToCustomerDto(customer)).willReturn(customerDto);
        given(customerServiceImpl.findCustomerById(customer.getId())).willReturn(customerDto);
        given(customerMapper.mapToCustomer(customerDto)).willReturn(customer);
        given(vehicleMapper.mapToVehicleDto(vehicle1)).willReturn(vehicleDto1);
        given(vehicleMapper.mapToVehicleDto(vehicle2)).willReturn(vehicleDto2);
        given(vehicleRepository.findVehicleByCustomer(customer)).willReturn(List.of(vehicle1, vehicle2));

        // when
        List<VehicleDto> testList = vehicleServiceImpl.findVehicleByCustomerId(customer.getId());

        // then
        assertAll(
                () -> assertNotNull(testList),
                () -> assertEquals(2, testList.size()),
                () -> assertEquals("registration 2", testList.get(1).getRegistrationNumber()),
                () -> assertEquals("vin 2", testList.get(1).getVehicleIdentificationNumber()),
                () -> assertEquals("manufacturer2", testList.get(1).getManufacturer()),
                () -> assertEquals("model2", testList.get(1).getModel()),
                () -> assertEquals("2222", testList.get(1).getProductionYear()),
                () -> assertEquals("22.22", testList.get(1).getMileage()),
                () -> assertEquals(EngineType.PETROL, testList.get(1).getEngineType()),
                () -> assertEquals(GearboxType.AUTOMATIC, testList.get(1).getGearboxType()),
                () -> assertEquals("test information", testList.get(1).getAdditionalInformation()),
                () -> assertEquals(customer.getId(), testList.get(1).getCustomerId())
        );
    }

    @Test
    @DisplayName("Testing findVehicleByCustomerId() method - negative scenario (empty list).")
    public void givenCustomerId_whenFindVehicleByCustomerId_thenReturnEmptyList() {
        // given
        given(customerRepository.findById(customerDto.getId())).willReturn(Optional.ofNullable(customer));
        given(customerMapper.mapToCustomerDto(customer)).willReturn(customerDto);
        given(customerServiceImpl.findCustomerById(customer.getId())).willReturn(customerDto);
        given(customerMapper.mapToCustomer(customerDto)).willReturn(customer);
        given(vehicleMapper.mapToVehicleDto(vehicle1)).willReturn(vehicleDto1);
        given(vehicleMapper.mapToVehicleDto(vehicle2)).willReturn(vehicleDto2);
        given(vehicleRepository.findVehicleByCustomer(customer)).willReturn(Collections.emptyList());

        // when
        List<VehicleDto> testList = vehicleServiceImpl.findVehicleByCustomerId(customer.getId());

        // then
        assertEquals(0, testList.size());
    }

    @Test
    @DisplayName("Testing findAllVehicles() method - positive scenario (valid input).")
    public void givenVehiclesList_whenFindAllVehicles_thenReturnVehicleDtoList() {
        // given
        given(vehicleRepository.findAll()).willReturn(List.of(vehicle1, vehicle2));
        given(vehicleMapper.mapToVehicleDto(vehicle1)).willReturn(vehicleDto1);
        given(vehicleMapper.mapToVehicleDto(vehicle2)).willReturn(vehicleDto2);

        // when
        List<VehicleDto> vehicles = vehicleServiceImpl.findAllVehicles();

        // then
        assertAll(
                () -> assertNotNull(vehicles),
                () -> assertTrue(vehicles.size() == 2),
                () -> assertEquals(vehicleDto1, vehicles.get(0)),
                () -> assertEquals(vehicleDto2, vehicles.get(1)),
                () -> assertEquals("registration 1", vehicles.get(0).getRegistrationNumber())
        );
    }

    @Test
    @DisplayName("Testing findVehicleByVIN(String vin) method - positive scenario (valid input).")
    public void givenVehicleVINNumber_whenFindVehicleByVIN_thenReturnVehicleDtoObject() {
        // given
        String vin = "vin 1";
        given(vehicleRepository.findVehicleByVehicleIdentificationNumber(vin)).willReturn(Optional.ofNullable(vehicle1));
        given(vehicleMapper.mapToVehicleDto(vehicle1)).willReturn(vehicleDto1);

        // when
        VehicleDto testVehicle = vehicleServiceImpl.findVehicleByVIN(vin);

        // then
        assertAll(
                () -> assertNotNull(testVehicle),
                () -> assertEquals(vehicleDto1, testVehicle),
                () -> assertEquals("vin 1", testVehicle.getVehicleIdentificationNumber()),
                () -> assertEquals(EngineType.DIESEL, testVehicle.getEngineType())
        );
    }

    @Test
    @DisplayName("Testing findVehicleByVIN(String vin) method that throws ResourceNotFoundException.")
    public void givenVINNumber_whenFindVehicleByVIN_thenThrowsResourceNotFoundException() {
        // given
        String vin = "none";
        given(vehicleRepository.findVehicleByVehicleIdentificationNumber(vin)).willReturn(Optional.empty());

        // when, then
        assertThrows(ResourceNotFoundException.class, () -> {
            vehicleServiceImpl.findVehicleByVIN(vin);
        });
        verify(vehicleRepository, times(1)).findVehicleByVehicleIdentificationNumber(vin);
        verify(vehicleMapper, never()).mapToVehicleDto(any());
    }

    @Test
    @DisplayName("Testing findVehicleByRegistrationNumber(String regNum) method - positive scenario.")
    public void givenRegistrationNumber_whenFindVehicleByRegistrationNumber_thenReturnVehicleDtoObject() {
        // given
        String registrationNumber = "registration 1";
        given(vehicleRepository.findVehicleByRegistrationNumber(registrationNumber))
                .willReturn(Optional.ofNullable(vehicle1));
        given(vehicleMapper.mapToVehicleDto(vehicle1)).willReturn(vehicleDto1);

        // when
        VehicleDto testVehicle = vehicleServiceImpl.findVehicleByRegistrationNumber(registrationNumber);

        // then
        assertAll(
                () -> assertNotNull(testVehicle),
                () -> assertEquals(vehicleDto1, testVehicle),
                () -> assertEquals(vehicle1.getId(), testVehicle.getId())
        );
    }

    @Test
    @DisplayName("Testing findVehicleByRegistrationNumber(String regNum) method that throws RNFException ")
    public void givenRegistrationNumber_whenFindVehicleByRegistrationNumber_thenThrowsResourceNotFoundException() {
        // given
        String registrationNumber = "none";
        given(vehicleRepository.findVehicleByRegistrationNumber(registrationNumber))
                .willReturn(Optional.empty());

        // when, then
        assertThrows(ResourceNotFoundException.class, () -> {
            vehicleServiceImpl.findVehicleByRegistrationNumber(registrationNumber);
        });
        verify(vehicleRepository, times(1)).findVehicleByRegistrationNumber(registrationNumber);
        verify(vehicleMapper, never()).mapToVehicleDto(any());
    }

    @Test
    @DisplayName("Testing createVehicle(VehicleDto vehicleDto) method.")
    public void givenVehicleDtoObject_whenCreateVehicle_thenReturnCreatedVehicleDtoObject() {
        // given
        given(vehicleRepository.findVehicleByRegistrationNumber(vehicleDto1.getRegistrationNumber()))
                .willReturn(Optional.empty());
        given(vehicleRepository.findVehicleByVehicleIdentificationNumber(vehicleDto1.getVehicleIdentificationNumber()))
                .willReturn(Optional.empty());
        given(vehicleRepository.save(vehicle1)).willReturn(vehicle1);
        given(vehicleMapper.mapToVehicleDto(vehicle1)).willReturn(vehicleDto1);
        given(vehicleMapper.mapToVehicle(vehicleDto1)).willReturn(vehicle1);

        // when
        VehicleDto createdVehicle = vehicleServiceImpl.createVehicle(vehicleDto1);

        // then
        assertAll(
                () -> assertNotNull(createdVehicle),
                () -> assertEquals(vehicle1.getId(), createdVehicle.getId()),
                () -> assertEquals("1111", createdVehicle.getProductionYear())
        );
    }

    @Test
    @DisplayName("Testing updateVehicle(VehicleDto vehicleDto, String registrationNumber) method.")
    public void givenVehicleObject_whenUpdateVehicle_thenReturnUpdatedVehicleObject() {
        // given
        given(vehicleRepository.findVehicleByRegistrationNumber(vehicleDto1.getRegistrationNumber()))
                .willReturn(Optional.ofNullable(vehicle1));
        given(vehicleRepository.save(vehicle1)).willReturn(updatedVehicle1);
        given(vehicleMapper.mapToVehicleDto(updatedVehicle1)).willReturn(updatedVehicleDto1);

        // when
        VehicleDto updatedVehicle = vehicleServiceImpl.updateVehicle(vehicleDto1, vehicleDto1.getRegistrationNumber());

        // then
        assertAll(
                () -> assertNotNull(updatedVehicle),
                () -> assertEquals("updated vin 1", updatedVehicle.getVehicleIdentificationNumber()),
                () -> assertEquals("updated manufacturer1", updatedVehicle.getManufacturer()),
                () -> assertEquals("updated 1111", updatedVehicle.getProductionYear())
        );
    }

    @Test
    @DisplayName("Testing deleteVehicleByRegistrationNumber(String registrationNumber) method.")
    public void givenRegistrationNumber_whenDeleteVehicleByRegistrationNumber_thenVehicleDeleted() {
        // given
        given(vehicleRepository.findVehicleByRegistrationNumber(vehicleDto1.getRegistrationNumber()))
                .willReturn(Optional.ofNullable(vehicle1));

        // when
        vehicleServiceImpl.deleteVehicleByRegistrationNumber(vehicleDto1.getRegistrationNumber());

        // then
        verify(vehicleRepository, times(1))
                .findVehicleByRegistrationNumber(vehicleDto1.getRegistrationNumber());
        verify(vehicleRepository, times(1)).delete(any(Vehicle.class));
    }

}
