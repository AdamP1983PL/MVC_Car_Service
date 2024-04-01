package com.car_service.vehicle.model.vehicle.repository;


import com.car_service.customer.model.customer.domain.Customer;
import com.car_service.customer.model.customer.repository.CustomerRepository;
import com.car_service.customer.model.enums.PaymentMethod;
import com.car_service.customer.model.enums.TaxValue;
import com.car_service.vehicle.model.enums.EngineType;
import com.car_service.vehicle.model.enums.GearboxType;
import com.car_service.vehicle.model.vehicle.domain.Vehicle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehicleRepositoryTest {
    private Vehicle vehicle1;
    private Customer customer;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach()
    void initialise() {
        customer = Customer.builder()
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
                .contactPersonName("test name")
                .contactPersonEmail("test email")
                .contactPersonPhone("000000000")
                .build();

        vehicle1 = Vehicle.builder()
                .registrationNumber("registration 1")
                .vehicleIdentificationNumber("vin 1")
                .manufacturer("manufacturer")
                .model("model")
                .productionYear("1111")
                .mileage(11.11)
                .engineType(EngineType.DIESEL)
                .gearboxType(GearboxType.MANUAL)
                .additionalInformation("none1")
                .customer(customer)
                .build();
    }

    @AfterEach()
    void cleanUp() {
        vehicleRepository.deleteById(vehicle1.getId());
        customerRepository.deleteById(customer.getId());
    }

    @Test
    @DisplayName("Testing findVehicleByVehicleIdentificationNumber(String vin) query.")
    public void givenVinNumber_whenFindVehicleByVehicleIdentificationNumber_thenReturnVehicleObject() {
        // given
        String vin = vehicle1.getVehicleIdentificationNumber();
        vehicleRepository.save(vehicle1);

        // when
        Optional<Vehicle> testVehicle = vehicleRepository.findVehicleByVehicleIdentificationNumber(vin);

        // then
        assertAll(
                () -> assertNotNull(testVehicle),
                () -> assertEquals("registration 1", testVehicle.get().getRegistrationNumber())
        );
    }

    @Test
    @DisplayName("Testing findVehicleByRegistrationNumber(String registrationNumber) query.")
    public void givenRegistrationNumber_whenFindVehicleByRegistrationNumber_thenReturnVehicleObject() {
        // given
        String registrationNumber = "registration 1";
        vehicleRepository.save(vehicle1);

        // when
        Optional<Vehicle> testVehicle = vehicleRepository.findVehicleByRegistrationNumber(registrationNumber);

        // then
        assertAll(
                () -> assertNotNull(testVehicle),
                () -> assertEquals("registration 1", testVehicle.get().getRegistrationNumber())
        );
    }

}
