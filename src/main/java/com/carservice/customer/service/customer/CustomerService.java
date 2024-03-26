package com.carservice.customer.service.customer;


import com.carservice.customer.service.customer.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAllCustomers();

    CustomerDto findCustomerById(Long id);

    List<CustomerDto> findCustomersByName(String name);

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(CustomerDto customerDto, Long id);

    CustomerDto mvcUpdateCustomer(CustomerDto customerDto);

    void deleteCustomerById(Long id);

}
