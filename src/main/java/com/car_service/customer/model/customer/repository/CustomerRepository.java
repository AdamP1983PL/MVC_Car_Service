package com.car_service.customer.model.customer.repository;


import com.car_service.customer.model.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findCustomerByCustomerNameContaining(String name);

    Optional<Customer> findCustomerByTaxNumber(String taxNumber);

}
