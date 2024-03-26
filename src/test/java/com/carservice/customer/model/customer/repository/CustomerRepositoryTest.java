package com.carservice.customer.model.customer.repository;

import com.carservice.customer.model.customer.domain.Customer;
import com.carservice.customer.model.enums.PaymentMethod;
import com.carservice.customer.model.enums.TaxValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {
    private Customer customer;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach()
    void initialise() {
        customer = Customer.builder()
                .id(1L)
                .customerName("test customer name")
                .taxNumber("1112223344")
                .country("country")
                .city("city")
                .postalCode("postalCode")
                .street("street")
                .customerEmail("test@test.com")
                .customerPhoneNumber("111-111-111")
                .customerWebsite("www@www.com")
                .isActive(true)
                .paymentIsBlocked(false)
                .paymentMethod(PaymentMethod.CASH)
                .taxValue(TaxValue.TWENTY_THREE)
                .contactPersonName("test name")
                .contactPersonEmail("test email")
                .contactPersonPhone("000000000")
                .build();
    }

    @AfterEach()
    void cleanUp() {
        customerRepository.deleteAll();
    }

    @Test
    @DisplayName("Testing findCustomerByCustomerNameContaining() query.")
    public void givenCustomerName_whenFindCustomerByCustomerName_thenCustomerObject() {
        // given
        customerRepository.save(customer);

        // when
        List<Customer> testedCustomersList = customerRepository
                .findCustomerByCustomerNameContaining("custo");

        // todo doesnt work with multiple words

        // then
        assertNotNull(testedCustomersList.get(0));
        assertFalse(testedCustomersList.isEmpty());
        assertEquals("1112223344", testedCustomersList.get(0).getTaxNumber());
    }

    @Test
    @DisplayName("Testing findCustomerByTaxNumber() query.")
    public void givenTaxNumber_whenFindCustomerByTaxNumber_thenReturnCustomerObject() {
        // given
        customerRepository.save(customer);

        // when
        Customer testedCustomer = customerRepository.findCustomerByTaxNumber("1112223344").get();

        // then
        assertNotNull(testedCustomer);
        assertEquals("test customer name", testedCustomer.getCustomerName());
        assertEquals("country", testedCustomer.getCountry());
    }

}
