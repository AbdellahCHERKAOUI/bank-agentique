package com.example.customerservice.services;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repositories.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    public CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @McpTool(description = "get all cutomers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
    @McpTool(description = "get customer by id")
    public Customer getCustomerById(@McpToolParam(description = "the customer id") long id){
        Customer customer=customerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("customer not found"));
        return customer;
    }
    @McpTool(description = "Save a new customer. The id is generated automatically.")
    public Customer saveNewCustomer(
            @McpToolParam(description = "the customer name") String name,
            @McpToolParam(description = "the customer email") String email) {

        Customer customer = Customer.builder()
                .name(name)
                .email(email)
                .build();               // id = null → INSERT
        return customerRepository.save(customer);
    }
}
