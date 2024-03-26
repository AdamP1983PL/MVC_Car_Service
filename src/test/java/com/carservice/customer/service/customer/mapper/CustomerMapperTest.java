package com.carservice.customer.service.customer.mapper;

import com.carservice.customer.model.customer.domain.Customer;
import com.carservice.customer.model.enums.PaymentMethod;
import com.carservice.customer.model.enums.TaxValue;
import com.carservice.customer.service.customer.dto.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerMapperTest {

    private Customer customer;
    private CustomerDto customerDto;

    @Autowired
    private CustomerMapper customerMapper;

    @BeforeEach()
    void initialize() {
        customer = Customer.builder()
                .id(1L)
                .customerName("test name")
                .taxNumber("111-111-11-11")
                .country("test country")
                .city("test city")
                .postalCode("11-111")
                .street("test street")
                .customerEmail("test@test.com")
                .customerPhoneNumber("111-111-111")
                .customerWebsite("www.test.com")
                .isActive(true)
                .paymentIsBlocked(false)
                .paymentMethod(PaymentMethod.BY_CARD)
                .taxValue(TaxValue.TWENTY_THREE)
                .contactPersonName("test name")
                .contactPersonEmail("test email")
                .contactPersonPhone("000000000")
                .build();

        customerDto = CustomerDto.builder()
                .id(1L)
                .customerName("test name")
                .taxNumber("111-111-11-11")
                .country("test country")
                .city("test city")
                .postalCode("11-111")
                .street("test street")
                .customerEmail("test@test.com")
                .customerPhoneNumber("111-111-111")
                .customerWebsite("www.test.com")
                .isActive(true)
                .paymentIsBlocked(false)
                .paymentMethod(PaymentMethod.BY_CARD)
                .taxValue(TaxValue.TWENTY_THREE)
                .contactPersonName("test name")
                .contactPersonEmail("test email")
                .contactPersonPhone("000000000")
                .build();
    }

    @Test
    @DisplayName("Testing mapToCustomer() method.")
    public void givenCustomerDtoObject_whenMapToCustomer_thenReturnCustomerObject() {
        // given, when
        Customer mappedCustomer = customerMapper.mapToCustomer(customerDto);

        // then
        assertAll(
                () -> assertNotNull(mappedCustomer),
                () -> assertEquals(1L, mappedCustomer.getId()),
                () -> assertEquals("test name", mappedCustomer.getCustomerName()),
                () -> assertEquals("111-111-11-11", mappedCustomer.getTaxNumber()),
                () -> assertEquals("test country", mappedCustomer.getCountry()),
                () -> assertEquals("test city", mappedCustomer.getCity()),
                () -> assertEquals("11-111", mappedCustomer.getPostalCode()),
                () -> assertEquals("test street", mappedCustomer.getStreet()),
                () -> assertEquals("test@test.com", mappedCustomer.getCustomerEmail()),
                () -> assertEquals("111-111-111", mappedCustomer.getCustomerPhoneNumber()),
                () -> assertEquals("www.test.com", mappedCustomer.getCustomerWebsite()),
                () -> assertTrue(mappedCustomer.isActive()),
                () -> assertFalse(mappedCustomer.isPaymentIsBlocked()),
                () -> assertEquals(PaymentMethod.BY_CARD, mappedCustomer.getPaymentMethod()),
                () -> assertEquals("23", mappedCustomer.getTaxValue().getDisplayText()),
                () -> assertEquals("test name", mappedCustomer.getContactPersonName()),
                () -> assertEquals("test email", mappedCustomer.getContactPersonEmail()),
                () -> assertEquals("000000000", mappedCustomer.getContactPersonPhone())
        );
    }

    @Test
    @DisplayName("Testing mapToCustomerDto() method.")
    public void givenCustomerObject_whenMapToCustomerDto_thenReturnCustomerDtoObject() {
        // given, when
        CustomerDto mappedCustomerDto = customerMapper.mapToCustomerDto(customer);

        // then
        assertAll(
                () -> assertNotNull(mappedCustomerDto),
                () -> assertEquals(1L, mappedCustomerDto.getId()),
                () -> assertEquals("test name", mappedCustomerDto.getCustomerName()),
                () -> assertEquals("111-111-11-11", mappedCustomerDto.getTaxNumber()),
                () -> assertEquals("test country", mappedCustomerDto.getCountry()),
                () -> assertEquals("test city", mappedCustomerDto.getCity()),
                () -> assertEquals("11-111", mappedCustomerDto.getPostalCode()),
                () -> assertEquals("test street", mappedCustomerDto.getStreet()),
                () -> assertEquals("test@test.com", mappedCustomerDto.getCustomerEmail()),
                () -> assertEquals("111-111-111", mappedCustomerDto.getCustomerPhoneNumber()),
                () -> assertEquals("www.test.com", mappedCustomerDto.getCustomerWebsite()),
                () -> assertTrue(mappedCustomerDto.isActive()),
                () -> assertFalse(mappedCustomerDto.isPaymentIsBlocked()),
                () -> assertEquals(PaymentMethod.BY_CARD, mappedCustomerDto.getPaymentMethod()),
                () -> assertEquals("23", mappedCustomerDto.getTaxValue().getDisplayText()),
                () -> assertEquals("test name", mappedCustomerDto.getContactPersonName()),
                () -> assertEquals("test email", mappedCustomerDto.getContactPersonEmail()),
                () -> assertEquals("000000000", mappedCustomerDto.getContactPersonPhone())
        );
    }

}

