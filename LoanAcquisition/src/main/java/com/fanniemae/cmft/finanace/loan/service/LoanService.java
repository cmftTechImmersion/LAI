package com.fanniemae.cmft.finanace.loan.service;

import com.fanniemae.cmft.finanace.loan.model.Loan;
import com.fanniemae.cmft.finanace.loan.redis.repository.LoanRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/loan")
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Value("${loan.modification.url}")
    String lookupURL;

    public String getLookupURL() {
        return lookupURL;
    }

    public void setLookupURL(String lookupURL) {
        this.lookupURL = lookupURL;
    }
    private final RestTemplate restTemplate;

    public LoanService(RestTemplate rest) {
        this.restTemplate = rest;
    }
    @HystrixCommand(fallbackMethod = "searchLoanByBorrowerId")
    public String searchByBorrowerId(String borrowerId) {
        URI uri = UriComponentsBuilder.fromUriString(lookupURL)  // Build the base link
                .path("/loan/searchLoanByBorrowerId")                            // Add path
                .queryParam("borrowerId", borrowerId)                                // Add one or more query params
                .build()                                                 // Build the URL
                .encode()                                                // Encode any URI items that need to be encoded
                .toUri();
        System.out.println("borrowerId:"+borrowerId);
        return restTemplate.getForObject(uri, String.class);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/acquireLoan")
    @ResponseStatus(HttpStatus.CREATED)
    public void acquireLoan(@RequestBody Loan loan) {
        System.out.println("loan.getBorrowerId()"+loan.getBorrowerId());
        System.out.println(searchLoanByBorrowerId(loan.getBorrowerId()));
        if(searchByBorrowerId(loan.getBorrowerId()) == null)
            loanRepository.acquireLoan(loan);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/searchByLoanId")
    public Loan searchByLoanId(@Param("id") String id) {
        System.out.print("Id:"+id);
        return loanRepository.searchByLoanId(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/allLoans")
    public Collection<Object> search() {
        return loanRepository.getLoans().values();
    }

    public String searchLoanByBorrowerId(String borrowerId) {
        System.out.println("From Local borrowerId:"+borrowerId);
        return loanRepository.searchLoanByBorrowerId(borrowerId);
    }
}
