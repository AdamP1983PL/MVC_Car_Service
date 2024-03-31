package com.car_service.service_order.controller;


import com.car_service.customer.service.customer.CustomerService;
import com.car_service.customer.service.customer.dto.CustomerDto;
import com.car_service.service_order.service.ServiceOrderService;
import com.car_service.service_order.service.ServiceOrderServiceImpl;
import com.car_service.service_order.service.dto.ServiceOrderDto;
import com.car_service.vehicle.service.vehicle.VehicleService;
import com.car_service.vehicle.service.vehicle.VehicleServiceImpl;
import com.car_service.vehicle.service.vehicle.dto.VehicleDto;
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
@RequestMapping("/service-order")
public class ServiceOrderThymeleafController {

    private final ServiceOrderService serviceOrderService;
    private final VehicleService vehicleService;
    private final CustomerService customerService;

    @GetMapping("/page")
    public String showServiceOrderPage() {
        log.info("====>>>> showServiceOrderPage() execution.");
        return "service_order/service-orders";
    }

    @GetMapping("/")
    public String listAllServiceOrders(Model model) {
        List<ServiceOrderDto> serviceOrders = serviceOrderService.findAllServiceOrders();
        model.addAttribute("serviceOrders", serviceOrders);
        log.info("====>>>> showAllServiceOrders() execution.");
        return "/service_order/all-service-orders";
    }

    @GetMapping("/add-service-order")
    public String showAddServiceOrderPage(Model model) {
        ServiceOrderDto serviceOrderDto = new ServiceOrderDto();
        model.addAttribute("serviceOrderDto", serviceOrderDto);

        List<VehicleDto> vehicles = vehicleService.findAllVehicles();
        model.addAttribute("vehicles", vehicles);

        List<CustomerDto> customers = customerService.findAllCustomers();
        model.addAttribute("customers", customers);

        log.info("====>>>> showAddServiceOrderPage() execution");
        return "/service_order/add-new-service-order";
    }

    @PostMapping("/save")
    public String saveNewServiceOrder(@ModelAttribute("serviceOrderDto") ServiceOrderDto serviceOrderDto,
                                      BindingResult result, Model model) {
        log.info("saveNewServiceOrder(" + serviceOrderDto + ")");
        if (result.hasErrors()) {
            log.info("====>>>> saveNewServiceOrder() result.hasError() execution.");
            model.addAttribute("serviceOrderDto", serviceOrderDto);
            return "/service_order/add-new-service-order";
        }
        serviceOrderService.createServiceOrder(serviceOrderDto);
        log.info("====>>>> saveNewServiceOrder() execution.");
        return "redirect:/service-order/";
    }


    @GetMapping("/details/{id}")
    public String listServiceOrderDetails(@PathVariable("id") Long id, Model model) {
        ServiceOrderDto serviceOrderDetails = serviceOrderService.findServiceOrderById(id);
        model.addAttribute("serviceOrderDetails", serviceOrderDetails);

        CustomerDto customerDto = customerService.findCustomerById(serviceOrderDetails.getCustomerId());
        model.addAttribute("customerDto", customerDto);

        VehicleDto vehicleDto = vehicleService.findVehicleById(serviceOrderDetails.getVehicleId());
        model.addAttribute("vehicleDto", vehicleDto);

        log.info("====>>>> listServiceOrderDetails(" + id + ") execution.");
        return "/service_order/service-order-details";
    }

    @GetMapping("/edit/{id}")
    public String editServiceOrder(@PathVariable("id") Long id, Model model) {
        ServiceOrderDto serviceOrderDto = serviceOrderService.findServiceOrderById(id);
        model.addAttribute("serviceOrderDto", serviceOrderDto);

        List<CustomerDto> customers = customerService.findAllCustomers();
        model.addAttribute("customers", customers);

        List<VehicleDto> vehicles = vehicleService.findAllVehicles();
        model.addAttribute("vehicles", vehicles);

        log.info("====>>>> editServiceOrder(" + id + ") execution.");
        return "/service_order/edit-service-order";
    }

    @PostMapping("/update/{id}")
    public String updateServiceOrder(@PathVariable("id") Long id,
                                     @Valid @ModelAttribute("serviceOrderDto") ServiceOrderDto serviceOrderDto,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("serviceOrderDto", serviceOrderDto);
            return "/service_order/edit-service-order";
        }
        serviceOrderDto.setId(id);
        serviceOrderService.updateServiceOrder(serviceOrderDto);
        log.info("====>>>> updateServiceOrder(" + id + ") execution.");
        return "redirect:/service-order/";
    }
    // todo update dateTimeDeadline doesn't work

    @GetMapping("/delete/{id}")
    public String deleteServiceOrder(@PathVariable("id") Long id) {
        serviceOrderService.deleteServiceOrder(id);
        log.info("====>>>> deleteServiceOrder(" + id + ") execution");
        return "redirect:/service-order/";
    }

}















