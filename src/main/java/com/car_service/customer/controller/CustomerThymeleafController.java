package com.car_service.customer.controller;


import com.car_service.customer.service.customer.CustomerServiceImpl;
import com.car_service.customer.service.customer.dto.CustomerDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerThymeleafController {

    private final CustomerServiceImpl customerServiceImpl;

    @GetMapping("/page")
    public String showCustomerPage() {
        log.info("====>>>> showCustomerPage() execution.");
        return "customer/customers";
    }

    @GetMapping("/")
    public String listAllCustomers(Model model) {
        List<CustomerDto> customers = customerServiceImpl.findAllCustomers();
        model.addAttribute("customers", customers);
        log.info("====>>>> listAllCustomers() execution.");
        return "customer/customers-list";
    }

    @GetMapping("/add-customer")
    public String addCustomer(Model model) {
        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customerDto", customerDto);
        log.info("====>>>> addCustomer() execution");
        return "customer/add-new-customer";
    }

    @PostMapping("/save-new-customer")
    public String saveNewCustomer(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customerDto", customerDto);
            return "customer/add-new-customer";
        }
        customerServiceImpl.createCustomer(customerDto);
        log.info("====>>>> saveNewCustomer() execution");
        return "redirect:/customer/";
    }

    @GetMapping("/details/{id}")
    public String listCustomerDetails(@PathVariable("id") Long id, Model model) {
        CustomerDto customerDetailsDto = customerServiceImpl.findCustomerById(id);
        model.addAttribute("customerDetailsDto", customerDetailsDto);
        log.info("====>>>> listCustomerDetails(id: " + id + ") execution");
        return "customer/customer-details";
    }

    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable("id") Long id, Model model) {
        CustomerDto customerDto = customerServiceImpl.findCustomerById(id);
        model.addAttribute("customerDto", customerDto);
        return "customer/edit-customer";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") Long id,
                                 @Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customerDto", customerDto);
            return "customer/edit-customer";
        }
        customerDto.setId(id);
        customerServiceImpl.mvcUpdateCustomer(customerDto);
        log.info("====>>>> updateCustomer(" + customerDto + ") execution");
        return "redirect:/customer/";
    }

    @GetMapping("/delete/{id}")
    public String mvcDeleteCustomer(@PathVariable("id") Long id) {
        customerServiceImpl.deleteCustomerById(id);
        log.info("====>>>> deleteCustomer(id: " + id + ") execution.");
        return "redirect:/customer/";
    }

}
