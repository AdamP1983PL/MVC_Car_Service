package com.car_service.service_order.service;

import com.car_service.service_order.model.enums.OrderStatus;
import com.car_service.service_order.service.dto.ServiceOrderDto;

import java.util.List;

public interface ServiceOrderService {

    List<ServiceOrderDto> findAllServiceOrders();

    ServiceOrderDto findServiceOrderById(Long id);

    List<ServiceOrderDto> findServiceOrdersByStatus(OrderStatus orderStatus);

    List<ServiceOrderDto> findServiceOrdersByCustomerId(Long id);

    List<ServiceOrderDto> findServiceOrdersByVehicleId(Long id);

    ServiceOrderDto createServiceOrder(ServiceOrderDto serviceOrderDto);

    ServiceOrderDto updateServiceOrder(ServiceOrderDto serviceOrderDto);

    ServiceOrderDto updateServiceOrderStatus(ServiceOrderDto serviceOrderDto);

    void deleteServiceOrder(Long id);
}
