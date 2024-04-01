package com.car_service.customer.service.customer.dto;


import com.car_service.customer.model.enums.PaymentMethod;
import com.car_service.customer.model.enums.TaxValue;
import com.car_service.vehicle.model.vehicle.domain.Vehicle;
import com.car_service.vehicle.service.vehicle.dto.VehicleDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

    private Long id;
    @Size(min = 3, max = 100)
    @NotEmpty(message = "Customer name should not be empty.")
    private String customerName;
    @Size(min = 6, max = 30)
    @NotEmpty(message = "Tax number should not be empty.")
    private String taxNumber;
    @Size(min = 3, max = 30)
    @NotEmpty(message = "Country name should not be empty.")
    private String country;
    @Size(min = 3, max = 30)
    @NotEmpty(message = "City name should not be empty.")
    private String city;
    @Size(min = 3, max = 20)
    @NotEmpty(message = "Postal code should not be empty.")
    private String postalCode;
    @Size(min = 3, max = 30)
    @NotEmpty(message = "Street should not be empty.")
    private String street;
    @Size(min = 3, max = 50)
    @Email
    @NotEmpty(message = "Customer email should not be empty.")
    private String customerEmail;
    @Size(min = 3, max = 30)
    @NotEmpty(message = "Customer phone number should not be empty.")
    private String customerPhoneNumber;
    private String customerWebsite;
    private boolean isActive;
    private boolean paymentIsBlocked;
    private PaymentMethod paymentMethod;
    private TaxValue taxValue;
    @Size(min = 3, max = 50)
    @NotEmpty(message = "Contact person full name should not be empty.")
    private String contactPersonName;
    @Size(min = 3, max = 50)
    @NotEmpty(message = "Customer person email should not be empty.")
    private String contactPersonEmail;
    @Size(min = 3, max = 50)
    @NotEmpty(message = "Customer person phone number should not be empty.")
    private String contactPersonPhone;

}
