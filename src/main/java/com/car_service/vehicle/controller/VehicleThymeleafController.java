package com.car_service.vehicle.controller;


import com.car_service.customer.service.customer.CustomerService;
import com.car_service.customer.service.customer.dto.CustomerDto;
import com.car_service.vehicle.service.vehicle.VehicleServiceImpl;
import com.car_service.vehicle.service.vehicle.dto.VehicleDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

        return findAllPaginated(1, model);
    }

    @GetMapping("/all-vehicles-one-page")
    public String listAllVehiclesOnePage(Model model) {
        List<VehicleDto> vehicles = vehicleServiceImpl.findAllVehicles();
        model.addAttribute("vehicles", vehicles);

        log.info("====>>>> listAllVehicles() execution.");
        return "vehicle/vehicle-list-one-page";
    }

    @GetMapping("/by-customer")
    public String showSelectionPage(Model model) {
        List<CustomerDto> customers = customerService.findAllCustomers();
        model.addAttribute("customers", customers);

        log.info("====>>>> showSelectionPage() execution");
        return "/vehicle/select-customer";
    }

//    @GetMapping("/by-customer/select/{id}")
//    public String listVehiclesByCustomer(@PathVariable("id") Long id, Model model) {
//        List<VehicleDto> vehiclesByCustomer = vehicleServiceImpl.findVehicleByCustomerId(id);
//        model.addAttribute("vehiclesByCustomer", vehiclesByCustomer);
//
//        log.info("====>>>> listVehiclesByCustomer(" + id + ") execution.");
//        return "/vehicle/vehicle-by-customer";
//    }

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
        if (result.hasErrors()) {
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
        List<CustomerDto> customers = customerService.findAllCustomers();
        model.addAttribute("customers", customers);

        log.info("====>>>> editVehicle(" + registration + ") execution");
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

    @GetMapping("/all-vehicles-paginated/{pageNo}")
    public String findAllPaginated(@PathVariable("pageNo") Integer pageNo, Model model) {
        int pageSize = 10;

        Page<VehicleDto> page = vehicleServiceImpl.findVehiclesPaginated(pageNo, pageSize);
        List<VehicleDto> vehicles = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("vehicles", vehicles);

        log.info("====>>>>  findAllPaginated() execution");
        return "vehicle/vehicle-list";
    }

}
