package com.car_service.service_order.model.service_order.domain;


import com.car_service.service_order.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "SERVICE_ORDER")
public class ServiceOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "DATE_TIME_CREATED")
    private LocalDateTime dateTimeCreated;

    @UpdateTimestamp
    @Column(name = "DATE_TIME_UPDATED")
    private LocalDateTime dateTimeUpdated;

    @Column(name = "DATE_TIME_DEADLINE")
//            , nullable = false)
    private LocalDateTime dateTimeDeadline;

    @Column(name = "CUSTOMER_ID")
//    , nullable = false)
    private Long customerId;

    @Column(name = "CUSTOMER_NAME")
//    , nullable = false)
    private String customerName;

    @Column(name = "VEHICLE_ID")
//    , nullable = false)
    private Long vehicleId;

    @Column(name = "VEHICLE_REGISTRATION")
//    , nullable = false)
    private String vehicleRegistrationNumber;

    @Column(name = "ORDER_STATUS")
//    , nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "DESC_1")
//    , nullable = false)
    private String description1;

    @Column(name = "DESC_2")
    private String description2;

    @Column(name = "DESC_3")
    private String description3;

}

// todo add hibernate mappings
