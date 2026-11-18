package com.example.customerservice;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerService customerService){
        return args -> {
            customerService.saveNewCustomer("abdellah","abdellah@gmail.com");
            customerService.saveNewCustomer("mehdi","mehdi@gmail.com");
            customerService.saveNewCustomer("ismail","ismail@gmail.com");
        };

    }

}
