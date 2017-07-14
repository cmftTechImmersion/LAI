package com.fanniemae.cmft.finanace.loan.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Loan implements Serializable {

    public Loan() {
    }

    String loanId;
    BigDecimal fiannceUPB;
    Date acquisationDate;
    LoanSaleType loneSaleType;
    String poolId;


   public static enum LoanSaleType{
        CASH, SWAP, PFP, LTSC
    }

    public Loan(String loanId, BigDecimal fiannceUPB, Date acquisationDate, LoanSaleType loneSaleType, String poolId) {
        this.loanId = loanId;
        this.fiannceUPB = fiannceUPB;
        this.acquisationDate = acquisationDate;
        this.loneSaleType = loneSaleType;
        this.poolId = poolId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getFiannceUPB() {
        return fiannceUPB;
    }

    public void setFiannceUPB(BigDecimal fiannceUPB) {
        this.fiannceUPB = fiannceUPB;
    }

    public Date getAcquisationDate() {
        return acquisationDate;
    }

    public void setAcquisationDate(Date acquisationDate) {
        this.acquisationDate = acquisationDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        if (loanId != null ? !loanId.equals(loan.loanId) : loan.loanId != null) return false;
        if (fiannceUPB != null ? !fiannceUPB.equals(loan.fiannceUPB) : loan.fiannceUPB != null) return false;
        if (acquisationDate != null ? !acquisationDate.equals(loan.acquisationDate) : loan.acquisationDate != null)
            return false;
        if (loneSaleType != loan.loneSaleType) return false;
        return poolId.equals(loan.poolId);
    }

    @Override
    public int hashCode() {
        int result = loanId != null ? loanId.hashCode() : 0;
        result = 31 * result + (fiannceUPB != null ? fiannceUPB.hashCode() : 0);
        result = 31 * result + (acquisationDate != null ? acquisationDate.hashCode() : 0);
        result = 31 * result + (loneSaleType != null ? loneSaleType.hashCode() : 0);
        result = 31 * result + poolId.hashCode();
        return result;
    }
}
