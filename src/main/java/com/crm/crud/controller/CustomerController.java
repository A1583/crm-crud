package com.crm.crud.controller;

import com.crm.crud.model.request.CreateCustomerRequest;
import com.crm.crud.model.request.CustomerRequest;
import com.crm.crud.model.request.SearchCustomerRequest;
import com.crm.crud.repository.entity.Customer;
import com.crm.crud.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerController {
    CustomerService customerService;

    @PostMapping()
    public List<Customer> createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        return customerService.create(request);
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@RequestBody @Valid CustomerRequest request, @PathVariable Integer customerId){
        return customerService.update(request, customerId);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Integer customerId) {
        customerService.delete(customerId);
    }

    @PostMapping("/search")
    public List<Customer> searchCustomer(@RequestBody SearchCustomerRequest request){
        return customerService.search(request);
    }
}
