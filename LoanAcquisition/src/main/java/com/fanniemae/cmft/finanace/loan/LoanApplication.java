package com.fanniemae.cmft.finanace.loan;

import com.fanniemae.cmft.finanace.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker
@RestController
@SpringBootApplication
public class LoanApplication {

	@Autowired
	private LoanService loanService;

	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		return builder.build();
	}
	@RequestMapping("/searchByBorrowerId")
	public String searchByBorrowerId(@RequestParam String borrowerId) {
		return loanService.searchByBorrowerId(borrowerId);
	}

	public static void main(String[] args) {
		SpringApplication.run(LoanApplication.class, args);
	}
}
