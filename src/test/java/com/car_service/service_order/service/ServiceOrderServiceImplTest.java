package com.car_service.service_order.service;

import com.car_service.customer.model.customer.domain.Customer;
import com.car_service.customer.model.customer.repository.CustomerRepository;
import com.car_service.customer.model.enums.PaymentMethod;
import com.car_service.customer.model.enums.TaxValue;
import com.car_service.customer.service.customer.CustomerServiceImpl;
import com.car_service.customer.service.customer.mapper.CustomerMapper;
import com.car_service.exceptions.ResourceNotFoundException;
import com.car_service.service_order.model.enums.OrderStatus;
import com.car_service.service_order.model.service_order.domain.ServiceOrder;
import com.car_service.service_order.model.service_order.repository.ServiceOrderRepository;
import com.car_service.service_order.service.dto.ServiceOrderDto;
import com.car_service.service_order.service.mapper.ServiceOrderMapper;
import com.car_service.vehicle.model.enums.EngineType;
import com.car_service.vehicle.model.enums.GearboxType;
import com.car_service.vehicle.model.vehicle.domain.Vehicle;
import com.car_service.vehicle.model.vehicle.repository.VehicleRepository;
import com.car_service.vehicle.service.vehicle.VehicleServiceImpl;
import com.car_service.vehicle.service.vehicle.mapper.VehicleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ServiceOrderServiceImplTest {
    private Customer customer;
    private Vehicle vehicle1;
    private ServiceOrder serviceOrder;
    private ServiceOrderDto serviceOrderDto;

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

        vehicle1 = Vehicle.builder()
                .id(1L)
                .registrationNumber("registration 1")
                .vehicleIdentificationNumber("vin 1")
                .manufacturer("manufacturer1")
                .model("model1")
                .productionYear("1111")
                .mileage(11.11)
                .engineType(EngineType.DIESEL)
                .gearboxType(GearboxType.MANUAL)
                .additionalInformation("none1")
                .build();

        serviceOrder = ServiceOrder.builder()
                .id(111L)
                .dateTimeCreated(LocalDateTime.of(2024, 4, 1, 12, 0, 0))
                .dateTimeUpdated(LocalDateTime.of(2024, 4, 1, 12, 1, 1))
                .dateTimeDeadline(LocalDateTime.of(2024, 4, 4, 6, 0, 0))
                .customerId(customer.getId())
                .customerName(customer.getCustomerName())
                .vehicleId(vehicle1.getId())
                .vehicleRegistrationNumber(vehicle1.getRegistrationNumber())
                .orderStatus(OrderStatus.OPEN)
                .description1("test desc 1")
                .description2("test desc 2")
                .description3("test desc 3")
                .build();

        serviceOrderDto = ServiceOrderDto.builder()
                .id(111L)
                .dateTimeCreated(LocalDateTime.of(2024, 4, 1, 12, 0, 0))
                .dateTimeUpdated(LocalDateTime.of(2024, 4, 1, 12, 1, 1))
                .dateTimeDeadline(LocalDateTime.of(2024, 4, 4, 6, 0, 0))
                .customerId(customer.getId())
                .customerName(customer.getCustomerName())
                .vehicleId(vehicle1.getId())
                .vehicleRegistrationNumber(vehicle1.getRegistrationNumber())
                .orderStatus(OrderStatus.OPEN)
                .description1("test desc 1")
                .description2("test desc 2")
                .description3("test desc 3")
                .build();
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

    @Mock
    private ServiceOrderRepository serviceOrderRepository;
    @Mock
    private ServiceOrderMapper serviceOrderMapper;
    @InjectMocks
    private ServiceOrderServiceImpl serviceOrderServiceImpl;

    @Test
    @DisplayName("Testing findAllServiceOrders() - negative scenario (empty list)")
    public void givenEmptyServiceOrdersList_whenFindAllServiceOrders_thenReturnEmptyList() {
        // given
        given(serviceOrderRepository.findAll()).willReturn(Collections.emptyList());

        // when
        List<ServiceOrderDto> emptyList = serviceOrderServiceImpl.findAllServiceOrders();

        // then
        assertTrue(emptyList.isEmpty());
    }

    @Test
    @DisplayName("Testing findAllServiceOrders() - positive scenario (valid input)")
    public void givenServiceOrdersList_whenFindAllServiceOrders_thenReturnServiceOrderDtoList() {
        // given
        given(serviceOrderRepository.findAll()).willReturn(List.of(serviceOrder));
        given(serviceOrderMapper.mapToServiceOrderDto(serviceOrder)).willReturn(serviceOrderDto);

        // when
        List<ServiceOrderDto> testList = serviceOrderServiceImpl.findAllServiceOrders();

        // then
        assertAll(
                () -> assertNotNull(testList),
                () -> assertFalse(testList.isEmpty()),
                () -> assertEquals(LocalDateTime.of(2024, 4, 1, 12, 0, 0),
                        testList.get(0).getDateTimeCreated()),
                () -> assertEquals("test customer name", testList.get(0).getCustomerName()),
                () -> assertEquals(OrderStatus.OPEN, testList.get(0).getOrderStatus())
        );
    }

    @Test
    @DisplayName("Testing findServiceOrderById(Long id) method - positive scenario (valid input).")
    public void givenServiceOrderId_whenFindServiceOrderById_thenReturnServiceOrderDto() {
        // given
        given(serviceOrderRepository.findById(serviceOrder.getId())).willReturn(Optional.ofNullable(serviceOrder));
        given(serviceOrderMapper.mapToServiceOrderDto(serviceOrder)).willReturn(serviceOrderDto);

        // when
        ServiceOrderDto testServiceOrderDto = serviceOrderServiceImpl.findServiceOrderById(serviceOrder.getId());

        // then
        assertAll(
                () -> assertNotNull(testServiceOrderDto),
                () -> assertEquals(LocalDateTime.of(2024, 4, 1, 12, 0, 0),
                        testServiceOrderDto.getDateTimeCreated()),
                () -> assertEquals(LocalDateTime.of(2024, 4, 1, 12, 1, 1),
                        testServiceOrderDto.getDateTimeUpdated()),
                () -> assertEquals(LocalDateTime.of(2024, 4, 4, 6, 0, 0),
                        testServiceOrderDto.getDateTimeDeadline()),
                () -> assertEquals("test customer name", testServiceOrderDto.getCustomerName()),
                () -> assertEquals(OrderStatus.OPEN, testServiceOrderDto.getOrderStatus()),
                () -> assertEquals("registration 1", testServiceOrderDto.getVehicleRegistrationNumber())
        );
    }

    @Test
    @DisplayName("Testing findServiceOrderById(Long id) method that throws ResourceNotFoundException.")
    public void givenServiceOrderId_whenFindServiceOrderById_thenThrowsResourceNotFountException() {
        // given
        Long id = 3333L;
        given(serviceOrderRepository.findById(id)).willReturn(Optional.empty());

        // when, then
        assertThrows(ResourceNotFoundException.class, () -> {
            serviceOrderServiceImpl.findServiceOrderById(id);
        });
        verify(serviceOrderRepository, times(1)).findById(id);
        verify(serviceOrderMapper, never()).mapToServiceOrderDto(any());
    }

    @Test
    @DisplayName("Testing findServiceOrdersByStatus() method - negative scenario (empty list).")
    public void givenServiceOrderList_whenFindServiceOrderByStatus_thenReturnEmptyList() {
        // given
        given(serviceOrderRepository.findServiceOrderByOrderStatus(OrderStatus.OPEN))
                .willReturn(Collections.emptyList());

        // when
        List<ServiceOrderDto> testList = serviceOrderServiceImpl.findServiceOrdersByStatus(OrderStatus.OPEN);

        // then
        assertAll(
                () -> assertTrue(testList.isEmpty()),
                () -> assertEquals(0, testList.size())
        );
    }

    @Test
    @DisplayName("Testing findServiceOrdersByStatus() method - positive scenario (valid input).")
    public void givenServiceOrderList_whenFindServiceOrderByStatus_thenReturnServiceOrderDtoList() {
        // given
        given(serviceOrderRepository.findServiceOrderByOrderStatus(OrderStatus.OPEN))
                .willReturn(List.of(serviceOrder));
        given(serviceOrderMapper.mapToServiceOrderDto(serviceOrder)).willReturn(serviceOrderDto);

        // when
        List<ServiceOrderDto> testList = serviceOrderServiceImpl.findServiceOrdersByStatus(OrderStatus.OPEN);

        // then
        assertAll(
                () -> assertFalse(testList.isEmpty()),
                () -> assertEquals(1, testList.size()),
                () -> assertEquals(OrderStatus.OPEN, testList.get(0).getOrderStatus()),
                () -> assertEquals("test desc 3", testList.get(0).getDescription3()),
                () -> assertEquals("test desc 2", testList.get(0).getDescription2()),
                () -> assertEquals("test desc 1", testList.get(0).getDescription1())
        );
    }

    @Test
    @DisplayName("Testing findServiceOrdersByCustomerId()  method - negative scenario (empty list).")
    public void givenServiceOrderList_whenFindServiceOrderByCustomerId_thenReturnEmptyList() {
        // given
        given(serviceOrderRepository.findServiceOrderByCustomerId(customer.getId())).willReturn(Collections.emptyList());

        // when
        List<ServiceOrderDto> testList = serviceOrderServiceImpl.findServiceOrdersByCustomerId(customer.getId());

        // then
        assertAll(
                () -> assertTrue(testList.isEmpty()),
                () -> assertEquals(0, testList.size())
        );
    }

    @Test
    @DisplayName("Testing findServiceOrdersByCustomerId() method - positive scenario (valid input).")
    public void givenServiceOrderList_whenFindServiceOrderByCustomerId_thenServiceOrderDtoList() {
        // given
        given(serviceOrderRepository.findServiceOrderByCustomerId(customer.getId())).willReturn(List.of(serviceOrder));
        given(serviceOrderMapper.mapToServiceOrderDto(serviceOrder)).willReturn(serviceOrderDto);

        // when
        List<ServiceOrderDto> testList = serviceOrderServiceImpl.findServiceOrdersByCustomerId(customer.getId());

        // then
        assertAll(
                () -> assertFalse(testList.isEmpty()),
                () -> assertEquals(1, testList.size()),
                () -> assertEquals(OrderStatus.OPEN, testList.get(0).getOrderStatus()),
                () -> assertEquals("test desc 3", testList.get(0).getDescription3()),
                () -> assertEquals("test desc 2", testList.get(0).getDescription2()),
                () -> assertEquals("test desc 1", testList.get(0).getDescription1())
        );
    }

    @Test
    @DisplayName("Testing findServiceOrdersByVehicleId()  method - negative scenario (empty list).")
    public void givenServiceOrderList_whenFindServiceOrderByVehicleId_thenReturnEmptyList() {
        // given
        given(serviceOrderRepository.findServiceOrderByVehicleId(vehicle1.getId())).willReturn(Collections.emptyList());

        // when
        List<ServiceOrderDto> testList = serviceOrderServiceImpl.findServiceOrdersByVehicleId(vehicle1.getId());

        // then
        assertAll(
                () -> assertTrue(testList.isEmpty()),
                () -> assertEquals(0, testList.size())
        );
    }

    @Test
    @DisplayName("Testing findServiceOrdersByVehicleId()  method - positive scenario (valid input).")
    public void givenServiceOrderList_whenFindServiceOrderByVehicleId_thenReturnServiceOrderDtoList() {
        // given
        given(serviceOrderRepository.findServiceOrderByVehicleId(vehicle1.getId())).willReturn(List.of(serviceOrder));
        given(serviceOrderMapper.mapToServiceOrderDto(serviceOrder)).willReturn(serviceOrderDto);

        // when
        List<ServiceOrderDto> testList = serviceOrderServiceImpl.findServiceOrdersByVehicleId(vehicle1.getId());

        // then
        assertAll(
                () -> assertFalse(testList.isEmpty()),
                () -> assertEquals(1, testList.size()),
                () -> assertEquals(OrderStatus.OPEN, testList.get(0).getOrderStatus()),
                () -> assertEquals("test desc 3", testList.get(0).getDescription3()),
                () -> assertEquals("test desc 2", testList.get(0).getDescription2()),
                () -> assertEquals("test desc 1", testList.get(0).getDescription1())
        );
    }

//    @Test
//    @DisplayName("Testing createServiceOrder() method.")
//    public void givenServiceOrder_whenCreateServiceOrder_thenServiceOrderSaved() {
//        // given
//        given(serviceOrderMapper.mapToServiceOrderDto(serviceOrder)).willReturn(serviceOrderDto);
//        given(serviceOrderMapper.mapToServiceOrder(serviceOrderDto)).willReturn(serviceOrder);
//        given(serviceOrderRepository.save(serviceOrder)).willReturn(serviceOrder);
//        given(customerRepository.findById(customer.getId())).willReturn(Optional.ofNullable(customer));
//        given(customerServiceImpl.findCustomerById(customer.getId())).willReturn(customerDto);
//        given(vehicleRepository.findById(vehicle1.getId())).willReturn(Optional.ofNullable(vehicle1));
//        given(vehicleServiceImpl.findVehicleById(vehicle1.getId())).willReturn(vehicleDto1);
//
//        // when
//        ServiceOrderDto createdServiceOrder = serviceOrderServiceImpl.createServiceOrder(serviceOrderDto);
//
//        // then
//        assertAll(
//                () -> assertNotNull(createdServiceOrder)
//        );
//    }

    @Test
    @DisplayName("Testing updateServiceOrder() method - positive scenario (valid input).")
    public void givenServiceOrder_whenUpdateServiceOrder_thenReturnUpdatedServiceOrder() {
        // given
        ServiceOrder updatedServiceOrder = ServiceOrder.builder()
                .id(serviceOrder.getId())
                .dateTimeCreated(LocalDateTime.of(2024, 4, 1, 12, 0, 0))
                .dateTimeUpdated(LocalDateTime.of(2024, 4, 1, 12, 1, 1))
                .dateTimeDeadline(LocalDateTime.of(2024, 4, 4, 6, 0, 0))
                .customerId(customer.getId())
                .customerName(customer.getCustomerName())
                .vehicleId(vehicle1.getId())
                .vehicleRegistrationNumber(vehicle1.getRegistrationNumber())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .description1("updated test desc 1")
                .description2("updated test desc 2")
                .description3("updated test desc 3")
                .build();

        ServiceOrderDto updatedServiceOrderDto = ServiceOrderDto.builder()
                .id(serviceOrder.getId())
                .dateTimeCreated(LocalDateTime.of(2024, 4, 1, 12, 0, 0))
                .dateTimeUpdated(LocalDateTime.of(2024, 4, 1, 12, 1, 1))
                .dateTimeDeadline(LocalDateTime.of(2024, 4, 4, 6, 0, 0))
                .customerId(customer.getId())
                .customerName(customer.getCustomerName())
                .vehicleId(vehicle1.getId())
                .vehicleRegistrationNumber(vehicle1.getRegistrationNumber())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .description1("updated test desc 1")
                .description2("updated test desc 2")
                .description3("updated test desc 3")
                .build();

        given(serviceOrderRepository.findById(serviceOrderDto.getId())).willReturn(Optional.ofNullable(serviceOrder));
        given(serviceOrderRepository.save(serviceOrder)).willReturn(updatedServiceOrder);
        given(serviceOrderMapper.mapToServiceOrderDto(updatedServiceOrder)).willReturn(updatedServiceOrderDto);

        // when
        ServiceOrderDto testServiceOrderDto = serviceOrderServiceImpl.updateServiceOrder(serviceOrderDto);

        // then
        assertAll(
                () -> assertNotNull(testServiceOrderDto),
                () -> assertEquals("updated test desc 1", testServiceOrderDto.getDescription1()),
                () -> assertEquals(OrderStatus.IN_PROGRESS, testServiceOrderDto.getOrderStatus())
        );
    }

    @Test
    @DisplayName("Testing updateServiceOrderStatus() method - positive scenario (valid input).")
    public void givenServiceOrder_whenUpdateServiceOrderStatus_thenStatusUpdated() {
        // given
        ServiceOrder updatedServiceOrder = ServiceOrder.builder()
                .id(serviceOrder.getId())
                .dateTimeCreated(LocalDateTime.of(2024, 4, 1, 12, 0, 0))
                .dateTimeUpdated(LocalDateTime.of(2024, 4, 1, 12, 1, 1))
                .dateTimeDeadline(LocalDateTime.of(2024, 4, 4, 6, 0, 0))
                .customerId(customer.getId())
                .customerName(customer.getCustomerName())
                .vehicleId(vehicle1.getId())
                .vehicleRegistrationNumber(vehicle1.getRegistrationNumber())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .description1("test desc 1")
                .description2("test desc 2")
                .description3("test desc 3")
                .build();

        ServiceOrderDto updatedServiceOrderDto = ServiceOrderDto.builder()
                .id(serviceOrder.getId())
                .dateTimeCreated(LocalDateTime.of(2024, 4, 1, 12, 0, 0))
                .dateTimeUpdated(LocalDateTime.of(2024, 4, 1, 12, 1, 1))
                .dateTimeDeadline(LocalDateTime.of(2024, 4, 4, 6, 0, 0))
                .customerId(customer.getId())
                .customerName(customer.getCustomerName())
                .vehicleId(vehicle1.getId())
                .vehicleRegistrationNumber(vehicle1.getRegistrationNumber())
                .orderStatus(OrderStatus.IN_PROGRESS)
                .description1("test desc 1")
                .description2("test desc 2")
                .description3("test desc 3")
                .build();

        given(serviceOrderRepository.findById(serviceOrderDto.getId())).willReturn(Optional.ofNullable(serviceOrder));
        given(serviceOrderRepository.save(serviceOrder)).willReturn(updatedServiceOrder);
        given(serviceOrderMapper.mapToServiceOrderDto(updatedServiceOrder)).willReturn(updatedServiceOrderDto);

        // when
        ServiceOrderDto testServiceOrderDto = serviceOrderServiceImpl.updateServiceOrder(serviceOrderDto);

        // then
        assertAll(
                () -> assertNotNull(testServiceOrderDto),
                () -> assertEquals("test desc 1", testServiceOrderDto.getDescription1()),
                () -> assertEquals(OrderStatus.IN_PROGRESS, testServiceOrderDto.getOrderStatus()),
                () -> assertEquals(customer.getCustomerName(), testServiceOrderDto.getCustomerName())
        );
    }

    @Test
    @DisplayName("Testing deleteServiceOrder() that throws ResourceNotFoundException.")
    public void givenServiceOrderId_whenDeleteServiceOrder_thenThrowResourceNotFoundException() {
        // given
        Long id = 333L;
        given(serviceOrderRepository.findById(id)).willReturn(Optional.empty());

        // when, then
        assertThrows(ResourceNotFoundException.class, () -> {
            serviceOrderServiceImpl.deleteServiceOrder(id);
        });
        verify(serviceOrderRepository, times(1)).findById(id);
        verify(serviceOrderRepository, never()).delete(any(ServiceOrder.class));
    }

    @Test
    @DisplayName("Testing deleteServiceOrder() method - positive scenario (valid input).")
    public void givenServiceOrderId_whenDeleteServiceOrder_thenServiceOrderDeleted() {
        // given
        given(serviceOrderRepository.findById(serviceOrderDto.getId())).willReturn(Optional.ofNullable(serviceOrder));

        // when
        serviceOrderServiceImpl.deleteServiceOrder(serviceOrderDto.getId());

        //  then
        verify(serviceOrderRepository, times(1)).findById(serviceOrderDto.getId());
    }

}
