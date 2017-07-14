package com.fanniemae.cmft.finanace.loan.redis.repository;

import com.fanniemae.cmft.finanace.loan.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class LoanRepositoryImpl implements LoanRepository{
    private static long id=1;
    private static final String LOAN_KEY = "FNMALoan";
    private static final String REF_KEY = "LoanRef";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public LoanRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public Loan acquireLoan(final Loan loan){
        loan.setLoanId(id);
        hashOperations.put(LOAN_KEY, loan.getLoanId(), loan);
        hashOperations.put(REF_KEY, loan.getBorrowerId(), id+"");
        System.out.println("id:"+id);
        id++;
        return (Loan)hashOperations.get(LOAN_KEY, loan.getLoanId());
    }

    public Loan searchByLoanId(final String id){
        return (Loan) hashOperations.get(LOAN_KEY, id);
    }

    public Map<Object, Object> getLoans(){
        //System.out.println(hashOperations.delete(LOAN_KEY,hashOperations.entries(LOAN_KEY).keySet().toArray()));
        //System.out.println(hashOperations.delete(REF_KEY,hashOperations.entries(REF_KEY).keySet().toArray()));
        return hashOperations.entries(LOAN_KEY);
    }

    public Loan modifyLoan(final Loan loan){
        hashOperations.put(LOAN_KEY, loan.getLoanId(), loan);
        return (Loan)hashOperations.get(LOAN_KEY, loan.getLoanId());
    }

    public String searchLoanByBorrowerId(String borrowerId) {
        return (String) hashOperations.get(REF_KEY, borrowerId);
    }
}
