package com.example.ebankservice.controllers;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class BankAccountController {
    public BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
    @GetMapping("/accounts")
    public List<BankAccount> getAllAccount(){
        return bankAccountService.getAllAccount();
    }
    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccountById(@PathVariable String id){
        return bankAccountService.getBankAccountById(id);
    }
    @PostMapping("/accounts")
    public BankAccount saveNewBankAccount(@RequestBody BankAccount bankAccount){
        return bankAccountService.saveNewBankAccount(bankAccount);
    }
}
