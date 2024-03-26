package com.car_service.service_order.service.dto;


import com.car_service.service_order.model.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceOrderDto {

    private Long id;
    private LocalDateTime dateTimeCreated;
    private LocalDateTime dateTimeUpdated;
    private LocalDateTime dateTimeDeadline;
    private Long customerId;
    private String customerName;
    private Long vehicleId;
    private String vehicleRegistrationNumber;
    private OrderStatus orderStatus;
    private String description1;
    private String description2;
    private String description3;

}
