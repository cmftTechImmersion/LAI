package com.fanniemae.cmft.finanace.loan.service;

import com.fanniemae.cmft.finanace.loan.model.Loan;
import com.fanniemae.cmft.finanace.loan.redis.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/loan")
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @RequestMapping(method = RequestMethod.POST, path = "/acquireLoan")
    @ResponseStatus(HttpStatus.CREATED)
    public void acquireLoan(@RequestBody Loan Loan) {
        loanRepository.acquireLoan(Loan);
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
}
