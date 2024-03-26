package com.carservice.customer.service.customer.mapper;


import com.carservice.customer.model.customer.domain.Customer;
import com.carservice.customer.service.customer.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapToCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .customerName(customerDto.getCustomerName())
                .taxNumber(customerDto.getTaxNumber())
                .country(customerDto.getCountry())
                .city(customerDto.getCity())
                .postalCode(customerDto.getPostalCode())
                .street(customerDto.getStreet())
                .customerEmail(customerDto.getCustomerEmail())
                .customerPhoneNumber(customerDto.getCustomerPhoneNumber())
                .customerWebsite(customerDto.getCustomerWebsite())
                .isActive(customerDto.isActive())
                .paymentIsBlocked(customerDto.isPaymentIsBlocked())
                .paymentMethod(customerDto.getPaymentMethod())
                .taxValue(customerDto.getTaxValue())
                .contactPersonName(customerDto.getContactPersonName())
                .contactPersonEmail(customerDto.getContactPersonEmail())
                .contactPersonPhone(customerDto.getContactPersonPhone())
                .build();
    }

    public CustomerDto mapToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .taxNumber(customer.getTaxNumber())
                .country(customer.getCountry())
                .city(customer.getCity())
                .postalCode(customer.getPostalCode())
                .street(customer.getStreet())
                .customerEmail(customer.getCustomerEmail())
                .customerPhoneNumber(customer.getCustomerPhoneNumber())
                .customerWebsite(customer.getCustomerWebsite())
                .isActive(customer.isActive())
                .paymentIsBlocked(customer.isPaymentIsBlocked())
                .paymentMethod(customer.getPaymentMethod())
                .taxValue(customer.getTaxValue())
                .contactPersonName(customer.getContactPersonName())
                .contactPersonEmail(customer.getContactPersonEmail())
                .contactPersonPhone(customer.getContactPersonPhone())
                .build();
    }

}
