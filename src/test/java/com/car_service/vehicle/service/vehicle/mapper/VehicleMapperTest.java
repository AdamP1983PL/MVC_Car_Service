package com.car_service.vehicle.service.vehicle.mapper;

import com.car_service.customer.model.customer.domain.Customer;
import com.car_service.customer.model.customer.repository.CustomerRepository;
import com.car_service.customer.model.enums.PaymentMethod;
import com.car_service.customer.model.enums.TaxValue;
import com.car_service.customer.service.customer.dto.CustomerDto;
import com.car_service.vehicle.model.enums.EngineType;
import com.car_service.vehicle.model.enums.GearboxType;
import com.car_service.vehicle.model.vehicle.domain.Vehicle;
import com.car_service.vehicle.model.vehicle.repository.VehicleRepository;
import com.car_service.vehicle.service.vehicle.dto.VehicleDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehicleMapperTest {
//    private Vehicle vehicle;
//    private VehicleDto vehicleDto;
//    private Customer customer;
//
//    @Autowired
//    private VehicleMapper vehicleMapper;
//
//    @Autowired
//    private VehicleRepository vehicleRepository;
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @BeforeEach()
//    void initialize() {
//        customer = Customer.builder()
//                .id(111L)
//                .customerName("test name")
//                .taxNumber("test tax number")
//                .country("test country")
//                .city("test city")
//                .postalCode("test postal code")
//                .street("test street")
//                .customerEmail("test@test.com")
//                .customerPhoneNumber("111222333")
//                .customerWebsite("www.test.com")
//                .isActive(true)
//                .paymentIsBlocked(false)
//                .paymentMethod(PaymentMethod.CASH)
//                .taxValue(TaxValue.TWENTY_THREE)
//                .contactPersonName("test name")
//                .contactPersonEmail("test@test.com")
//                .contactPersonPhone("222333444")
//                .vehicles(List.of())
//                .build();
//
//        customer = customerRepository.save(customer);
//
//        vehicle = Vehicle.builder()
////                .id(1L)
//                .registrationNumber("registration 1")
//                .vehicleIdentificationNumber("vin 1")
//                .manufacturer("manufacturer")
//                .model("model")
//                .productionYear("1111")
//                .mileage(11.11)
//                .engineType(EngineType.DIESEL)
//                .gearboxType(GearboxType.MANUAL)
//                .additionalInformation("none1")
//                .customer(customer)
//                .build();
//
//        vehicleDto = VehicleDto.builder()
//                .id(1L)
//                .registrationNumber("registration 1")
//                .vehicleIdentificationNumber("vin 1")
//                .manufacturer("manufacturer")
//                .model("model")
//                .productionYear("1111")
//                .mileage(11.11)
//                .engineType(EngineType.DIESEL)
//                .gearboxType(GearboxType.MANUAL)
//                .additionalInformation("none1")
//                .customerId(customer.getId())
//                .build();
//
//        customer.setVehicles(List.of(vehicle));
//
//        customerRepository.save(customer);
//        vehicleRepository.save(vehicle);
//    }
//
//    @AfterEach
//    void cleanUp() {
////        customerRepository.delete(customer);
////        vehicleRepository.delete(vehicle);
//
//        vehicle.setCustomer(null);
//        vehicleRepository.save(vehicle);
//        customerRepository.delete(customer);
//    }
//
//    @Test
//    @DisplayName("Testing mapToVehicle(VehicleDto vehicleDto) method.")
//    public void givenVehicleDtoObject_whenMapToVehicle_thenReturnVehicleObject() {
//        // given
//        // when
//        Vehicle testVehicle = vehicleMapper.mapToVehicle(vehicleDto);
//
//        // then
//        assertAll(
//                () -> assertNotNull(testVehicle),
//                () -> assertEquals(vehicle.getId(), testVehicle.getId()),
//                () -> assertEquals("registration 1", testVehicle.getRegistrationNumber()),
//                () -> assertEquals("vin 1", testVehicle.getVehicleIdentificationNumber()),
//                () -> assertEquals("manufacturer", testVehicle.getManufacturer()),
//                () -> assertEquals("model", testVehicle.getModel()),
//                () -> assertEquals("1111", testVehicle.getProductionYear()),
//                () -> assertEquals(11.11, testVehicle.getMileage()),
//                () -> assertEquals(EngineType.DIESEL, testVehicle.getEngineType()),
//                () -> assertEquals(GearboxType.MANUAL, testVehicle.getGearboxType()),
//                () -> assertEquals("none1", testVehicle.getAdditionalInformation())
////                () -> assertEquals("test tax number", testVehicle.getCustomer().getTaxNumber()),
////                () -> assertEquals("test name",testVehicle.getCustomer().getCustomerName())
//        );
//    }
//
//    @Test
//    @DisplayName("Testing mapToVehicleDto(Vehicle vehicle) method.")
//    public void givenVehicleObject_whenMapToVehicleDto_thenReturnVehicleDtoObject() {
//        // given
//        // when
//        VehicleDto testVehicle = vehicleMapper.mapToVehicleDto(vehicle);
//
//        // then
//        assertAll(
//                () -> assertNotNull(testVehicle),
//                () -> assertEquals(1L, testVehicle.getId()),
//                () -> assertEquals("registration 1", testVehicle.getRegistrationNumber()),
//                () -> assertEquals("vin 1", testVehicle.getVehicleIdentificationNumber()),
//                () -> assertEquals("manufacturer", testVehicle.getManufacturer()),
//                () -> assertEquals("model", testVehicle.getModel()),
//                () -> assertEquals("1111", testVehicle.getProductionYear()),
//                () -> assertEquals(11.11, testVehicle.getMileage()),
//                () -> assertEquals(EngineType.DIESEL, testVehicle.getEngineType()),
//                () -> assertEquals(GearboxType.MANUAL, testVehicle.getGearboxType()),
//                () -> assertEquals("none1", testVehicle.getAdditionalInformation())
//        );
//    }
//
//    @Test
//    @DisplayName("Testing getCustomerByVehicle() method.")
//    public void givenVehicleDto_whenGetCustomerByVehicle_thenReturnCustomerDto() {
//        // given
//        // when
//        Customer testCustomer = vehicleMapper.getCustomerByVehicle(vehicleDto);
//
//        // then
//        assertAll(
//                () -> assertNotNull(testCustomer)
//        );
//
//
//    }

}
