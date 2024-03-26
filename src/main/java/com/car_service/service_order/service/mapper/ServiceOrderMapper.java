package com.car_service.service_order.service.mapper;


import com.car_service.service_order.model.service_order.domain.ServiceOrder;
import com.car_service.service_order.service.dto.ServiceOrderDto;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderMapper {

    public ServiceOrder mapToServiceOrder(ServiceOrderDto serviceOrderDto) {
        return ServiceOrder.builder()
                .id(serviceOrderDto.getId())
                .dateTimeCreated(serviceOrderDto.getDateTimeCreated())
                .dateTimeUpdated(serviceOrderDto.getDateTimeUpdated())
                .dateTimeDeadline(serviceOrderDto.getDateTimeDeadline())
                .customerId(serviceOrderDto.getCustomerId())
                .customerName(serviceOrderDto.getCustomerName())
                .vehicleId(serviceOrderDto.getVehicleId())
                .vehicleRegistrationNumber(serviceOrderDto.getVehicleRegistrationNumber())
                .orderStatus(serviceOrderDto.getOrderStatus())
                .description1(serviceOrderDto.getDescription1())
                .description2(serviceOrderDto.getDescription2())
                .description3(serviceOrderDto.getDescription3())
                .build();
    }

    public ServiceOrderDto mapToServiceOrderDto(ServiceOrder serviceOrder) {
        return ServiceOrderDto.builder()
                .id(serviceOrder.getId())
                .dateTimeCreated(serviceOrder.getDateTimeCreated())
                .dateTimeUpdated(serviceOrder.getDateTimeUpdated())
                .dateTimeDeadline(serviceOrder.getDateTimeDeadline())
                .customerId(serviceOrder.getCustomerId())
                .customerName(serviceOrder.getCustomerName())
                .vehicleId(serviceOrder.getVehicleId())
                .vehicleRegistrationNumber(serviceOrder.getVehicleRegistrationNumber())
                .orderStatus(serviceOrder.getOrderStatus())
                .description1(serviceOrder.getDescription1())
                .description2(serviceOrder.getDescription2())
                .description3(serviceOrder.getDescription3())
                .build();
    }

}
