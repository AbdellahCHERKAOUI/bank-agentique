package com.example.ebankservice.services;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.feign.CustomerRestClient;
import com.example.ebankservice.model.Customer;
import com.example.ebankservice.repositories.BankAccountRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BankAccountService {
    public BankAccountRepository bankAccountRepository;
    public CustomerRestClient customerRestClient;
    public BankAccountService(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }
    @McpTool(description = "get all accounts")
    public List<BankAccount> getAllAccount(){
        return bankAccountRepository.findAll();
    }
    @McpTool(description = "get account by id")
    public BankAccount getBankAccountById(
            @McpToolParam(description = "the account id") String id){
        BankAccount bankAccount=bankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException("account not found"));
        bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
        return bankAccount;
    }
    @McpTool(description = "save new account")
    public BankAccount saveNewBankAccount(
            @McpToolParam(description ="the account (balance,type)" ) BankAccount bankAccount){
        try {
            Customer customer=customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        return bankAccountRepository.save(bankAccount);
    }
}
