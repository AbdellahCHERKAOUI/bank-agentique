package com.example.ebankservice.entities;

import com.example.ebankservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.UUID;

@Entity @Builder @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private double balance;
    private String type;
    private long customerId;
    @Transient
    private Customer customer;
}
