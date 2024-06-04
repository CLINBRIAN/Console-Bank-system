/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bank.Entity;

import java.time.LocalDate;

/**
 *
 * @author kamau
 */
public class Transaction {
    private LocalDate transactionDate;
    private String transactionUserId;
    private Double tranactionAmount;
    private String transactionType;
    private Double initialBalance;
    private Double finalBalance;
    private String transactionPerformedBy;

    public Transaction(LocalDate transactionDate, String transactionUserId, Double tranactionAmount, String transactionType, Double initialBalance, Double finalBalance, String transactionPerformedBy) {
        this.transactionDate = transactionDate;
        this.transactionUserId = transactionUserId;
        this.tranactionAmount = tranactionAmount;
        this.transactionType = transactionType;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
        this.transactionPerformedBy = transactionPerformedBy;
    }

    public String getTransactionPerformedBy() {
        return transactionPerformedBy;
    }

    public void setTransactionPerformedBy(String transactionPerformedBy) {
        this.transactionPerformedBy = transactionPerformedBy;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionUserId() {
        return transactionUserId;
    }

    public void setTransactionUserId(String transactionUserId) {
        this.transactionUserId = transactionUserId;
    }

    public Double getTranactionAmount() {
        return tranactionAmount;
    }

    public void setTranactionAmount(Double tranactionAmount) {
        this.tranactionAmount = tranactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Double getFinalBalance() {
        return finalBalance;
    }

    public void setFinalBalance(Double finalBalance) {
        this.finalBalance = finalBalance;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transactionDate=" + transactionDate + ", transactionUserId=" + transactionUserId + ", tranactionAmount=" + tranactionAmount + ", transactionType=" + transactionType + ", initialBalance=" + initialBalance + ", finalBalance=" + finalBalance + ", transactionPerformedBy=" + transactionPerformedBy + '}';
    }
    
    
    
    
}
