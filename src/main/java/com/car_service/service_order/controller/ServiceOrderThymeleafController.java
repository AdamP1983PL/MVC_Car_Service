package com.car_service.service_order.controller;


import com.car_service.service_order.service.ServiceOrderServiceImpl;
import com.car_service.service_order.service.dto.ServiceOrderDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
@AllArgsConstructor
public class ServiceOrderThymeleafController {

    private final ServiceOrderServiceImpl serviceOrderServiceImpl;

    @GetMapping("/service-order/find-all")
    public String showAllServiceOrders(Model model) {
//        List<ServiceOrderDto> serviceOrderDtoList = serviceOrderServiceImpl.findAllServiceOrders();
//        model.addAttribute("serviceOrderDtoList", serviceOrderDtoList);
//        log.info("====>>>> showAllServiceOrders() execution.");
        return "all-service-orders";
    }

    @GetMapping("/service-order/create-new")
    public String showAddServiceOrderPage(Model model) {
//        ServiceOrderDto serviceOrderDto = new ServiceOrderDto();
//        model.addAttribute("serviceOrderDto", serviceOrderDto);
//
//        List<VehicleDto> vehicles = vehicleApiServiceImpl.findAllVehicles();
//        model.addAttribute("vehicles", vehicles);
//
//        List<CustomerDto> customers = customerApiServiceImpl.findAllCustomers();
//        model.addAttribute("customers", customers);
//
//        log.info("====>>>> showAddServiceOrderPage() execution");
        return "add-new-service-order";
    }

    @PostMapping("/service-order/save")
    public String saveNewServiceOrder(@ModelAttribute("serviceOrderDto") ServiceOrderDto serviceOrderDto,
                                      BindingResult result, Model model) {
//        log.info("saveNewServiceOrder(" + serviceOrderDto + ")");
//        if (result.hasErrors()) {
//            log.info("====>>>> saveNewServiceOrder() result.hasError() execution.");
//            model.addAttribute("serviceOrderDto", serviceOrderDto);
//            return "add-new-service-order";
//        }
//        serviceOrderServiceImpl.createServiceOrder(serviceOrderDto);
//        log.info("====>>>> saveNewServiceOrder() execution.");
        return "redirect:/service-order/find-all";
    }


    @GetMapping("/service-order/details/{id}")
    public String listServiceOrderDetails(@PathVariable("id") Long id, Model model) {
//        ServiceOrderDto serviceOrderDto = serviceOrderServiceImpl.findServiceOrderById(id);
//        model.addAttribute("serviceOrderDto", serviceOrderDto);
//        log.info("====>>>> listServiceOrderDetails(" + id + ") execution.");
        return "service-order-details";
    }

    @GetMapping("/service-order/edit/{id}")
    public String editServiceOrder(@PathVariable("id") Long id, Model model) {
//        ServiceOrderDto serviceOrderDto = serviceOrderServiceImpl.findServiceOrderById(id);
//        model.addAttribute("serviceOrderDto", serviceOrderDto);
//
//        List<CustomerDto> customers = customerApiServiceImpl.findAllCustomers();
//        model.addAttribute("customers", customers);
//
//        List<VehicleDto> vehicles = vehicleApiServiceImpl.findAllVehicles();
//        model.addAttribute("vehicles", vehicles);
//
//        log.info("====>>>> editServiceOrder(" + id + ") execution.");
        return "edit-service-order";
    }

    @PostMapping("/service-order/update/{id}")
    public String updateServiceOrder(@PathVariable("id") Long id,
                                     @Valid @ModelAttribute("serviceOrderDto") ServiceOrderDto serviceOrderDto,
                                     BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("serviceOrderDto", serviceOrderDto);
//            return "edit-service-order";
//        }
//        serviceOrderDto.setId(id);
//        serviceOrderServiceImpl.mvcUpdateServiceOrder(serviceOrderDto);
//        log.info("====>>>> updateServiceOrder(" + id + ") execution.");
        return "redirect:/service-order/find-all";
    }

    @GetMapping("/service-order/delete/{id}")
    public String deleteServiceOrder(@PathVariable("id") Long id) {
//        serviceOrderServiceImpl.deleteServiceOrder(id);
//        log.info("====>>>> deleteServiceOrder(" + id + ") execution");
        return "redirect:/service-order/find-all";
    }

}















