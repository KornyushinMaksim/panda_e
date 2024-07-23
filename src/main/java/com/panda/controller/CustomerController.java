package com.panda.controller;

import com.panda.dto.CustomerDto;
import com.panda.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/add-customer")
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.addCustomer(customerDto);
    }

    @GetMapping("/get-all-customers")
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/delete-customer")
    public void deleteCustomer(@RequestParam UUID customerId) {
        customerService.deleteCustomer(customerId);
    }

    @GetMapping("/get-customer-by-id/{id}")
    public CustomerDto getCustomerById(@PathVariable UUID id) {
        return customerService.getCustomerById(id);
    }
}
