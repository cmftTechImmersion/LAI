package com.fanniemae.cmft.finanace.loan.bootstrap;

import com.fanniemae.cmft.finanace.loan.model.Loan;
import com.fanniemae.cmft.finanace.loan.redis.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class LoanLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Loan ln1 = new Loan("11", new BigDecimal(12343.23), new Date(),Loan.LoanSaleType.CASH, "2");
        loanRepository.acquireLoan(ln1);
        System.out.println("Loan saved");
    }
}
