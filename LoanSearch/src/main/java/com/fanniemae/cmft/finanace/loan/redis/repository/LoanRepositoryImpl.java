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

    private static final String KEY = "FNMALoan";

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
        hashOperations.put(KEY, loan.getLoanId(), loan);
        return (Loan)hashOperations.get(KEY, loan.getLoanId());
    }

    public Loan searchByLoanId(final String id){
        return (Loan) hashOperations.get(KEY, id);
    }

    public Map<Object, Object> getLoans(){
        return hashOperations.entries(KEY);
    }

    public Loan modifyLoan(final Loan loan){
        hashOperations.put(KEY, loan.getLoanId(), loan);
        return (Loan)hashOperations.get(KEY, loan.getLoanId());
    }
}
