package com.carservice.customer.model.customer.repository;


import com.carservice.customer.model.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findCustomerByCustomerNameContaining(String name);

    Optional<Customer> findCustomerByTaxNumber(String taxNumber);

}
