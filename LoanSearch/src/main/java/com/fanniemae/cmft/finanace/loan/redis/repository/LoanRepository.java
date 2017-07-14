package com.fanniemae.cmft.finanace.loan.redis.repository;

import com.fanniemae.cmft.finanace.loan.model.Loan;

import java.util.List;
import java.util.Map;

public interface LoanRepository {

    public Loan acquireLoan(Loan loan);

    public Loan searchByLoanId(String id);

    public Map<Object, Object> getLoans();

    public Loan modifyLoan(Loan loan);
}
