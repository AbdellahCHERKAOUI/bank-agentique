package com.example.ebankservice;

import com.example.ebankservice.entities.BankAccount;
import com.example.ebankservice.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class EbankServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
		return args -> {
			for (int i = 1; i <= 3; i++) {
				for (int j = 1; j <5 ; j++) {
					bankAccountService.saveNewBankAccount(BankAccount.builder()
							.type(Math.random()>0.5?"CURRENT_ACCOUNT":"SAVING_ACCOUNT")
							.balance(100+Math.random()*500).customerId(i).build());

				}

			}
		};
	}

}
