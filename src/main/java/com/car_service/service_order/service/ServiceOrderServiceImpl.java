package com.car_service.service_order.service;

import com.car_service.service_order.model.enums.OrderStatus;
import com.car_service.service_order.model.service_order.repository.ServiceOrderRepository;
import com.car_service.service_order.service.dto.ServiceOrderDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceOrderServiceImpl implements ServiceOrderService {

    private final ServiceOrderRepository serviceOrderRepository;

    @Override
    public List<ServiceOrderDto> findAllServiceOrders() {
        return null;
    }

    @Override
    public ServiceOrderDto findServiceOrderById(Long id) {
        return null;
    }

    @Override
    public List<ServiceOrderDto> findServiceOrdersByStatus(OrderStatus orderStatus) {
        return null;
    }

    @Override
    public List<ServiceOrderDto> findServiceOrdersByCustomerId(Long id) {
        return null;
    }

    @Override
    public List<ServiceOrderDto> findServiceOrdersByVehicleId(Long id) {
        return null;
    }

    @Override
    public ServiceOrderDto createServiceOrder(ServiceOrderDto serviceOrderDto) {
        return null;
    }

    @Override
    public ServiceOrderDto updateServiceOrder(ServiceOrderDto serviceOrderDto, Long id) {
        return null;
    }

    @Override
    public ServiceOrderDto updateServiceOrderStatus(ServiceOrderDto serviceOrderDto, Long id) {
        return null;
    }

    @Override
    public ServiceOrderDto mvcUpdateServiceOrder(ServiceOrderDto serviceOrderDto) {
        return null;
    }

    @Override
    public void deleteServiceOrder(Long id) {

    }
}
