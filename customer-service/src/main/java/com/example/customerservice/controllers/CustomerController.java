package com.example.customerservice.controllers;
import com.example.customerservice.entities.Customer;
import com.example.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CustomerController {
    public CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable long id){
        return customerService.getCustomerById(id);
    }
    @PostMapping("/customers")
    public Customer saveNewCustomer(@RequestParam String name,@RequestParam String email){
        return customerService.saveNewCustomer(name,email);
    }
}
