package com.fanniemae.cmft.finanace.loan.service;

import com.fanniemae.cmft.finanace.loan.model.Loan;
import com.fanniemae.cmft.finanace.loan.redis.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
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

    public void acquireLoan(Loan loan) {
        System.out.println("loan.getBorrowerId()"+loan.getBorrowerId());
        System.out.println(searchLoanByBorrowerId(loan.getBorrowerId()));
        if(searchLoanByBorrowerId(loan.getBorrowerId()) == null)
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

    @RequestMapping(method = RequestMethod.GET, path = "/searchLoanByBorrowerId")
    public String searchLoanByBorrowerId(String borrowerId) {
        System.out.println("borrowerId:"+borrowerId);
        return loanRepository.searchLoanByBorrowerId(borrowerId);
    }
}
