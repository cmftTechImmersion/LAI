package com.fanniemae.cmft.finanace.loan.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Loan implements Serializable {

    public Loan() {
    }

    long loanId;
    BigDecimal fiannceUPB;
    Date acquisitionDate;
    LoanSaleType loneSaleType;
    String poolId;
    String borrowerId;

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public static enum LoanSaleType{
        CASH, SWAP, PFP, LTSC
    }

    public Loan(long loanId, BigDecimal fiannceUPB, Date acquisitionDate, LoanSaleType loneSaleType, String poolId, String borrowerId) {
        this.loanId = loanId;
        this.fiannceUPB = fiannceUPB;
        this.acquisitionDate = acquisitionDate;
        this.loneSaleType = loneSaleType;
        this.poolId = poolId;
        this.borrowerId = borrowerId;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getFiannceUPB() {
        return fiannceUPB;
    }

    public void setFiannceUPB(BigDecimal fiannceUPB) {
        this.fiannceUPB = fiannceUPB;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public LoanSaleType getLoneSaleType() {
        return loneSaleType;
    }

    public void setLoneSaleType(LoanSaleType loneSaleType) {
        this.loneSaleType = loneSaleType;
    }

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

}
