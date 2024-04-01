package com.car_service.service_order.service;

import com.car_service.customer.service.customer.CustomerService;
import com.car_service.customer.service.customer.dto.CustomerDto;
import com.car_service.exceptions.ResourceNotFoundException;
import com.car_service.service_order.model.enums.OrderStatus;
import com.car_service.service_order.model.service_order.domain.ServiceOrder;
import com.car_service.service_order.model.service_order.repository.ServiceOrderRepository;
import com.car_service.service_order.service.dto.ServiceOrderDto;
import com.car_service.service_order.service.mapper.ServiceOrderMapper;
import com.car_service.vehicle.service.vehicle.VehicleService;
import com.car_service.vehicle.service.vehicle.dto.VehicleDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceOrderServiceImpl implements ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceOrderMapper serviceOrderMapper;
    private final CustomerService customerService;
    private final VehicleService vehicleService;

    @Override
    public List<ServiceOrderDto> findAllServiceOrders() {
        log.info("====>>>> findAllServiceOrders() execution.");
        return serviceOrderRepository.findAll().stream()
                .map(serviceOrderMapper::mapToServiceOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceOrderDto findServiceOrderById(Long id) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service order", "id: ", Long.toString(id)));

        log.info("====>>>> findServiceOrderById(" + id + ") execution.");
        return serviceOrderMapper.mapToServiceOrderDto(serviceOrder);
    }

    @Override
    public List<ServiceOrderDto> findServiceOrdersByStatus(OrderStatus orderStatus) {

        log.info("====>>>> findServiceOrdersByStatus(" + orderStatus + ") execution.");
        return serviceOrderRepository.findServiceOrderByOrderStatus(orderStatus).stream()
                .map(serviceOrderMapper::mapToServiceOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceOrderDto> findServiceOrdersByCustomerId(Long id) {

        log.info("====>>>> findServiceOrdersByCustomerId(" + id + ") execution.");
        return serviceOrderRepository.findServiceOrderByCustomerId(id).stream()
                .map(serviceOrderMapper::mapToServiceOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceOrderDto> findServiceOrdersByVehicleId(Long id) {

        log.info("====>>>> findServiceOrdersByVehicleId(" + id + ") execution.");
        return serviceOrderRepository.findServiceOrderByVehicleId(id).stream()
                .map(serviceOrderMapper::mapToServiceOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceOrderDto createServiceOrder(ServiceOrderDto serviceOrderDto) {

        if (serviceOrderDto == null) {
            throw new IllegalArgumentException("serviceOrderDto can not be null");
        }

        ServiceOrder serviceOrder = serviceOrderMapper.mapToServiceOrder(serviceOrderDto);

        CustomerDto customerDto = customerService.findCustomerById(serviceOrderDto.getCustomerId());
        String customerName = customerDto.getCustomerName();
        serviceOrder.setCustomerName(customerName);

        VehicleDto vehicleDto = vehicleService.findVehicleById(serviceOrder.getVehicleId());
        String vehicleRegistration = vehicleDto.getRegistrationNumber();
        serviceOrder.setVehicleRegistrationNumber(vehicleRegistration);

        ServiceOrder savedServiceOrder = serviceOrderRepository.save(serviceOrder);

        log.info("====>>>> createServiceOrder(" + serviceOrderDto.getId() + ") execution.");
        return serviceOrderMapper.mapToServiceOrderDto(savedServiceOrder);
    }

    @Override
    public ServiceOrderDto updateServiceOrder(ServiceOrderDto serviceOrderDto) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Service order", "id: ",
                        Long.toString(serviceOrderDto.getId())));

        serviceOrder.setDateTimeDeadline(serviceOrderDto.getDateTimeDeadline());
        serviceOrder.setCustomerId(serviceOrderDto.getCustomerId());
        serviceOrder.setVehicleId(serviceOrder.getVehicleId());
        serviceOrder.setOrderStatus(serviceOrderDto.getOrderStatus());
        serviceOrder.setDescription1(serviceOrderDto.getDescription1());
        serviceOrder.setDescription2(serviceOrderDto.getDescription2());
        serviceOrder.setDescription3(serviceOrderDto.getDescription3());
        ServiceOrder updatedServiceOrder = serviceOrderRepository.save(serviceOrder);

        log.info("====>>>> updateServiceOrder(id: " + serviceOrder.getId() + ")execution.");
        return serviceOrderMapper.mapToServiceOrderDto(updatedServiceOrder);
    }

    @Override
    public ServiceOrderDto updateServiceOrderStatus(ServiceOrderDto serviceOrderDto) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Service order", "id: ",
                        Long.toString(serviceOrderDto.getId())));

        serviceOrder.setOrderStatus(serviceOrderDto.getOrderStatus());
        ServiceOrder updatedServiceOrder = serviceOrderRepository.save(serviceOrder);

        log.info("====>>>> updateServiceOrderStatus(id: " + serviceOrder.getId() + ")execution.");
        return serviceOrderMapper.mapToServiceOrderDto(updatedServiceOrder);
    }

    @Override
    public void deleteServiceOrder(Long id) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service order", "id: ", Long.toString(id)));

        log.info("====>>>> deleteServiceOrder(" + id + ") execution.");
        serviceOrderRepository.delete(serviceOrder);
    }

}
