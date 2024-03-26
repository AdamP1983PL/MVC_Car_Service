package com.carservice.customer.service.customer;


import com.carservice.customer.model.customer.domain.Customer;
import com.carservice.customer.model.customer.repository.CustomerRepository;
import com.carservice.customer.service.customer.dto.CustomerDto;
import com.carservice.customer.service.customer.mapper.CustomerMapper;
import com.carservice.exceptions.ResourceNotFoundException;
import com.carservice.exceptions.TaxNumberAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public List<CustomerDto> findAllCustomers() {
        log.info("====>>>> findAllCustomers() execution.");
        return customerRepository.findAll().stream()
                .map(customerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        log.info("====>>>> findCustomerById(\"" + id + "\") execution.");
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> findCustomersByName(String name) {
        List<Customer> customers = customerRepository.findCustomerByCustomerNameContaining(name);

        log.info("====>>>> findCustomersByName(\"" + name + "\") execution.");
        return customers.stream()
                .map(customerMapper::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = customerRepository.findCustomerByTaxNumber(customerDto.getTaxNumber());
        if (customer.isPresent()) {
            throw new TaxNumberAlreadyExistsException("Tax number", customerDto.getTaxNumber());
        }

        Customer savedCustomer = customerRepository.save(customerMapper.mapToCustomer(customerDto));
        log.info("====>>>> createCustomer(" + customerDto.getCustomerName() + ") execution.");
        return customerMapper.mapToCustomerDto(savedCustomer);
    }

// todo Postman tests: savedCustomer "isActive" is always false!!!

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long id) {
        Customer customer = customerRepository.findById(id)
                .map(cust -> {
                    prepareUpdate(customerDto, cust);
                    return customerRepository.save(cust);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        log.info("====>>>> updateCustomer() execution.");
        return customerMapper.mapToCustomerDto(customer);
    }

    @Override
    public CustomerDto mvcUpdateCustomer(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerDto.getId()));
        prepareUpdate(customerDto, customer);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("====>>>> mvcUpdateCustomer(" + customerDto + ") execution.");
        return customerMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customerRepository.deleteById(id);
        log.info("====>>>> Customer with id " + id + " deleted successfully.");
    }

    private void prepareUpdate(CustomerDto customerDto, Customer customer) {
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setTaxNumber(customerDto.getTaxNumber());
        customer.setCountry(customerDto.getCountry());
        customer.setCity(customerDto.getCity());
        customer.setPostalCode(customerDto.getPostalCode());
        customer.setStreet(customerDto.getStreet());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        customer.setCustomerPhoneNumber(customerDto.getCustomerPhoneNumber());
        customer.setCustomerWebsite(customerDto.getCustomerWebsite());
        customer.setActive(customerDto.isActive());
        customer.setPaymentIsBlocked(customerDto.isPaymentIsBlocked());
        customer.setTaxValue(customerDto.getTaxValue());
        customer.setContactPersonName(customerDto.getContactPersonName());
        customer.setContactPersonEmail(customerDto.getContactPersonEmail());
        customer.setContactPersonPhone(customerDto.getContactPersonPhone());
    }

}
