package com.example.ebankservice.feign;

import com.example.ebankservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Service
@FeignClient(name = "customer-service")
public interface CustomerRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customer-service",fallbackMethod = "getDefaultCustomer")
    Customer getCustomerById(@PathVariable long id);

    default Customer getDefaultCustomer(long id,Exception e){
        return new Customer(id,"noName","noEmail");
    }
}
