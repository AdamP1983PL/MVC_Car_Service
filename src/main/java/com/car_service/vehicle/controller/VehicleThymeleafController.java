package com.car_service.vehicle.controller;


import com.car_service.customer.service.customer.CustomerService;
import com.car_service.customer.service.customer.dto.CustomerDto;
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
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleThymeleafController {

    private final VehicleServiceImpl vehicleServiceImpl;
    private final CustomerService customerService;

    @GetMapping("/page")
    public String showVehiclePage() {

        log.info("====>>>> showVehiclePage() execution.");
        return "vehicle/vehicles";
    }

    @GetMapping("/all-vehicles")
    public String listAllVehicles(Model model) {
        List<VehicleDto> vehicles = vehicleServiceImpl.findAllVehicles();
        model.addAttribute("vehicles", vehicles);
        log.info("====>>>> listAllVehicles() execution.");
        return "vehicle/vehicle-list";
    }

    @GetMapping("/add-vehicle")
    public String addVehicle(Model model) {
        VehicleDto vehicleDto = new VehicleDto();
        model.addAttribute("vehicleDto", vehicleDto);

        List<CustomerDto> customers = customerService.findAllCustomers();
        model.addAttribute("customers", customers);

        log.info("====>>>> addVehicle() execution");
        return "vehicle/add-new-vehicle";
    }

    @PostMapping("/save-new-vehicle")
    public String saveNewVehicle(@Valid @ModelAttribute("vehicleDto") VehicleDto vehicleDto,
                                 BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("vehicleDto", vehicleDto);
            return "vehicle/add-new-vehicle";
        }
        vehicleServiceImpl.createVehicle(vehicleDto);
        log.info("====>>>> saveNewVehicle() execution");
        return "redirect:/vehicle/all-vehicles";
    }

    @GetMapping("/details/{registration}")
    public String listVehicleDetails(@PathVariable("registration") String registration, Model model) {
        VehicleDto vehicleDetails = vehicleServiceImpl.findVehicleByRegistrationNumber(registration);
        model.addAttribute("vehicleDetails", vehicleDetails);
        log.info("====>>>> listVehicleDetails(registration: " + registration + ") execution");
        return "vehicle/vehicle-details";
    }

    @GetMapping("/edit/{registration}")
    public String editVehicle(@PathVariable("registration") String registration, Model model) {
        VehicleDto vehicleDto = vehicleServiceImpl.findVehicleByRegistrationNumber(registration);
        model.addAttribute("vehicleDto", vehicleDto);

        log.info("====>>>> editVehicle() execution");
        return "vehicle/edit-vehicle";
    }

    @PostMapping("/update/{registration}")
    public String updateVehicle(@PathVariable("registration") String registration,
                                @Valid @ModelAttribute("vehicleDto") VehicleDto vehicleDto,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("vehicleDto", vehicleDto);
            return "vehicle/edit-vehicle";
        }
        vehicleDto.setRegistrationNumber(registration);
        vehicleServiceImpl.mvcUpdateVehicle(vehicleDto);
        log.info("====>>>> updateVehicle(registration: " + registration + ") execution");
        return "redirect:/vehicle/all-vehicles";
    }

    @GetMapping("/delete/{registration}")
    public String deleteVehicle(@PathVariable("registration") String registration) {
        vehicleServiceImpl.deleteVehicleByRegistrationNumber(registration);
        log.info("====>>>> deleteVehicle(" + registration + ") execution");
        return "redirect:/vehicle/all-vehicles";
    }

}
