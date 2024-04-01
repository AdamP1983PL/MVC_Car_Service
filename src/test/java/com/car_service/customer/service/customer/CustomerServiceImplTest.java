package com.car_service.customer.service.customer;

import com.car_service.customer.model.customer.domain.Customer;
import com.car_service.customer.model.customer.repository.CustomerRepository;
import com.car_service.customer.model.enums.PaymentMethod;
import com.car_service.customer.model.enums.TaxValue;
import com.car_service.customer.service.customer.dto.CustomerDto;
import com.car_service.customer.service.customer.mapper.CustomerMapper;
import com.car_service.exceptions.ResourceNotFoundException;
import com.car_service.exceptions.TaxNumberAlreadyExistsException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    private Customer customer;
    private CustomerDto customerDto;

    @BeforeEach()
    void initialize() {
        customer = Customer.builder()
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
                .build();

        customerDto = CustomerDto.builder()
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
                .build();
    }

//    @AfterEach()
//    void cleanUp() {
////        customerRepository.deleteById(customer.getId());
//    }

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    @DisplayName("Testing findAllCustomers() method - negative scenario (empty List).")
    public void givenEmptyCustomersList_whenFindAllCustomers_thenReturnEmptyList() {
        // given
        given(customerRepository.findAll()).willReturn(Collections.emptyList());

        // when
        List<CustomerDto> emptyList = customerServiceImpl.findAllCustomers();

        // then
        assertTrue(emptyList.isEmpty());
    }

    @Test
    @DisplayName("Testing findAllCustomers() method - positive scenario (valid input).")
    public void givenCustomersList_whenFindAllCustomers_thenReturnCustomerList() {
        // given
        given(customerRepository.findAll()).willReturn(List.of(customer));
        given(customerMapper.mapToCustomerDto(customer)).willReturn(customerDto);

        // when
        List<CustomerDto> customersList = customerServiceImpl.findAllCustomers();

        // then
        assertFalse(customersList.isEmpty());
        assertAll(
                () -> assertEquals(1, customersList.size()),
                () -> assertEquals("111-111-111", customersList.get(0).getCustomerPhoneNumber()),
                () -> assertTrue(customersList.get(0).isActive()),
                () -> assertFalse(customersList.get(0).isPaymentIsBlocked()),
                () -> assertEquals(TaxValue.TWENTY_THREE, customersList.get(0).getTaxValue())
        );
    }

    @Test
    @DisplayName("Testing findCustomerById() method - positive scenario (valid input).")
    public void givenCustomerId_whenFindCustomerById_thenReturnCustomerObject() {
        // given
        Long id = customer.getId();
        given(customerRepository.findById(id)).willReturn(Optional.ofNullable(customer));
        given(customerMapper.mapToCustomerDto(customer)).willReturn(customerDto);

        // when
        CustomerDto testedCustomer = customerServiceImpl.findCustomerById(id);

        // then
        assertAll(
                () -> assertNotNull(testedCustomer),
                () -> assertEquals("test customer name", testedCustomer.getCustomerName()),
                () -> assertTrue(testedCustomer.isActive()),
                () -> assertFalse(testedCustomer.isPaymentIsBlocked()),
                () -> assertEquals(TaxValue.TWENTY_THREE, testedCustomer.getTaxValue())
        );
    }

    @Test
    @DisplayName("Testing findCustomerById() method that throws ResourceNotFoundException.")
    public void givenCustomerId_whenFindCustomerById_thenThrowsResourceNotFountException() {
        // given
        Long id = 222L;
        given(customerRepository.findById(id)).willReturn(Optional.empty());

        // when, then
        assertThrows(ResourceNotFoundException.class, () -> {
            customerServiceImpl.findCustomerById(id);
        });
        verify(customerRepository, times(1)).findById(id);
        verify(customerMapper, never()).mapToCustomerDto(any(Customer.class));
    }

    @Test
    @DisplayName("Testing findCustomerByName() method - positive scenario (valid input).")
    public void givenCustomerName_whenFindCustomersByName_thenReturnCustomersList() {
        // given
        String nameSnippet = "cust";
        given(customerRepository.findCustomerByCustomerNameContaining(nameSnippet)).willReturn(List.of(customer));
        given(customerMapper.mapToCustomerDto(customer)).willReturn(customerDto);

        // when
        List<CustomerDto> customersList = customerServiceImpl.findCustomersByName(nameSnippet);

        // then
        assertAll(
                () -> assertNotNull(customersList),
                () -> assertEquals(1, customersList.size()),
                () -> assertEquals("test@test.com", customersList.get(0).getCustomerEmail()),
                () -> assertTrue(customersList.get(0).isActive()),
                () -> assertFalse(customersList.get(0).isPaymentIsBlocked())
        );
    }

    @Test
    @DisplayName("Testing creteCustomer() method - positive scenario (valid input).")
    public void givenCustomerWithValidTaxNumber_whenCreateCustomer_thenSaveNewCustomerObject() {
        // given
        given(customerRepository.findCustomerByTaxNumber(customer.getTaxNumber()))
                .willReturn(Optional.empty());
        given(customerMapper.mapToCustomer(customerDto)).willReturn(customer);
        given(customerMapper.mapToCustomerDto(customer)).willReturn(customerDto);
        given(customerRepository.save(customer)).willReturn(customer);

        // when
        CustomerDto createdCustomer = customerServiceImpl.createCustomer(customerDto);

        // then
        assertAll(
                () -> assertNotNull(createdCustomer),
                () -> assertEquals(1L, createdCustomer.getId()),
                () -> assertEquals("country", createdCustomer.getCountry()),
                () -> assertEquals("city", createdCustomer.getCity())
        );
    }

    @Test
    @DisplayName("Testing creteCustomer() method that throws TaxNumberAlreadyExistsException.")
    public void givenCustomerTaxNumber_whenCreateCustomer_thenThrowNumberAlreadyExistsException() {
        // given
        given(customerRepository.findCustomerByTaxNumber(customerDto.getTaxNumber()))
                .willReturn(Optional.ofNullable(customer));

        // when, then
        assertThrows(TaxNumberAlreadyExistsException.class, () -> {
            customerServiceImpl.createCustomer(customerDto);
        });
        verify(customerRepository, times(1)).findCustomerByTaxNumber(customerDto.getTaxNumber());
        verify(customerMapper, never()).mapToCustomerDto(customer);
    }

    @Test
    @DisplayName("Testing updateCustomer() method.")
    public void givenCustomerObject_whenUpdateCustomer_thenReturnUpdatedCustomer() {
        // given
        Customer updatedCustomer = Customer.builder()
                .id(1L)
                .customerName("updated_test customer name")
                .taxNumber("updated_1112223344")
                .country("updated_country")
                .city("updated_city")
                .postalCode("updated_postalCode")
                .street("updated_street")
                .customerEmail("updated_test@test.com")
                .customerPhoneNumber("updated_111-111-111")
                .customerWebsite("updated_www@www.com")
                .isActive(true)
                .paymentIsBlocked(true)
                .paymentMethod(PaymentMethod.BY_CARD)
                .taxValue(TaxValue.ZERO)
                .build();

        CustomerDto updatedCustomerDto = CustomerDto.builder()
                .id(1L)
                .customerName("updated_test customer name")
                .taxNumber("updated_1112223344")
                .country("updated_country")
                .city("updated_city")
                .postalCode("updated_postalCode")
                .street("updated_street")
                .customerEmail("updated_test@test.com")
                .customerPhoneNumber("updated_111-111-111")
                .customerWebsite("updated_www@www.com")
                .isActive(true)
                .paymentIsBlocked(true)
                .paymentMethod(PaymentMethod.BY_CARD)
                .taxValue(TaxValue.ZERO)
                .build();

        given(customerRepository.findById(customerDto.getId())).willReturn(Optional.ofNullable(customer));
        given(customerMapper.mapToCustomerDto(updatedCustomer)).willReturn(updatedCustomerDto);
        given(customerRepository.save(customer)).willReturn(updatedCustomer);

        // when
        CustomerDto testedUpdatedCustomerDto = customerServiceImpl.updateCustomer(customerDto, customerDto.getId());

        // then
        assertAll(
                () -> assertNotNull(testedUpdatedCustomerDto),
                () -> assertEquals("updated_test customer name", testedUpdatedCustomerDto.getCustomerName()),
                () -> assertEquals("updated_www@www.com", testedUpdatedCustomerDto.getCustomerWebsite()),
                () -> assertEquals(TaxValue.ZERO, testedUpdatedCustomerDto.getTaxValue()),
                () -> assertEquals(PaymentMethod.BY_CARD, testedUpdatedCustomerDto.getPaymentMethod()),
                () -> assertTrue(testedUpdatedCustomerDto.isActive()),
                () -> assertTrue(testedUpdatedCustomerDto.isPaymentIsBlocked())
        );
    }

    @Test
    @DisplayName("Testing deleteCustomerById() method - positive scenario (valid input).")
    public void givenCustomerId_whenDeleteCustomerById_thenCustomerObjectDeleted() {
        // given
        Long id = customer.getId();
        given(customerRepository.findById(id)).willReturn(Optional.ofNullable(customer));

        // when
        customerServiceImpl.deleteCustomerById(id);

        // then
        verify(customerRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Testing deleteCustomerById() method that throws ResourceNotFoundException.")
    public void givenCustomerId_whenDeleteCustomerById_thenThrowsResourceNotFountException() {
        // given
        Long id = 111111L;
        given(customerRepository.findById(id)).willReturn(Optional.empty());

        // when, then
        assertThrows(ResourceNotFoundException.class, () -> {
            customerServiceImpl.findCustomerById(id);
        });
        verify(customerRepository, times(1)).findById(id);
    }

}
