/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bank.Repository;

import com.bank.Entity.Transaction;
import com.bank.Entity.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author kamau
 */
public class UserRepository {

    private static Set<User> users = new HashSet<>();                                //hashset does not take insertion order as linkedHashset and does not allow duplication

    private static List<Transaction> transactions = new ArrayList<>();               //can allow duplication

    Map<String, Boolean> chequebookRequest = new HashMap<>();

    static {
        User user1 = new User("admin", "admin", "123445", "admin", 0.0);
        User user2 = new User("user2", "user2", "123400", "user", 1000.0);
        User user3 = new User("user3", "user3", "100445", "user", 2000.0);
        User user4 = new User("user4", "user3", "100443", "user", 2000.0);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    public void printUsers() {
        System.out.println(users);
    }

    public void approveChequeBookRequest(String userId) {
        if(chequebookRequest.containsKey(userId)){
        chequebookRequest.put(userId, true);
        }

    }

    public List<String> getUserIdforChequeBookRequest() {
        List<String> userIds = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : chequebookRequest.entrySet()) {
            if (!entry.getValue()) {
                userIds.add(entry.getKey());
            }
        }

        return userIds;
    }

    public void raiseChequebookRequest(String userId) {
        chequebookRequest.put(userId, false);
    }

    public Map<String, Boolean> getAllChequebookRequest() {
        return chequebookRequest;
    }

    public boolean transferAmount(String userId, String payeeUserId, Double amount) {
        boolean isDebit = debit(userId, amount, payeeUserId);
        boolean isCredit = credit(payeeUserId, amount, userId);

        return isDebit && isCredit;

    }

    //for the debit account
    private boolean debit(String userId, Double amount, String payeeUserId) {
        User user = getUser(userId);
        Double accountBalance = user.getAccountBalance();
        users.remove(user);

        Double finalBalance = accountBalance - amount;
        user.setAccountBalance(finalBalance);

        Transaction transaction = new Transaction(
                LocalDate.now(),
                payeeUserId,
                amount,
                "Debit",
                accountBalance,
                finalBalance,
                userId
        );

        System.out.println(transaction);   //reason to use toString() method in transaction
        transactions.add(transaction);

        return users.add(user);

    }

    //for the credit account
    private boolean credit(String payeeUserId, Double amount, String userId) {
        User user = getUser(payeeUserId);
        Double accountBalance = user.getAccountBalance();
        users.remove(user);

        Double finalBalance = accountBalance + amount;
        user.setAccountBalance(finalBalance);

        Transaction transaction = new Transaction(
                LocalDate.now(),
                userId,
                amount,
                "Credit",
                accountBalance,
                finalBalance,
                payeeUserId
        );

        System.out.println(transaction);
        transactions.add(transaction);

        return users.add(user);

    }

    public void printTransactions(String userId) {
        List<Transaction> filteredTransaction = transactions.stream().filter(transaction -> transaction.getTransactionPerformedBy().equals(userId)).collect(Collectors.toList());
        System.out.println("Date \t\t" + "user Id \t" + "Amount \t" + "Type \t" + "Initial amount \t" + "final amount \t");
        System.out.println("..................................................................................");
        for (Transaction t : filteredTransaction) {
            System.out.println(t.getTransactionDate() + "\t" + t.getTransactionUserId()
                    + "\t\t" + t.getTranactionAmount() + "\t" + t.getTransactionType() + "\t"
                    + t.getInitialBalance() + "\t\t" + t.getFinalBalance());
        }
        System.out.println("..................................................................................");
    }

    public User login(String username, String password) {

        List<User> finalList = users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).collect(Collectors.toList());

        if (!finalList.isEmpty()) {
            return finalList.get(0);   //0 is to show if there is any duplication, it should be omitted.
        } else {
            return null;
        }
    }

    public boolean addCustomer(String username, String password, String contactNumber) {
        User user = new User(username, password, contactNumber, "user", 500.0);
        return users.add(user);
    }

    public Double checkBankBalance(String userId) {
        List<User> result = users.stream().filter(user -> user.getUsername().equals(userId)).collect(Collectors.toList());
        if (!result.isEmpty()) {
            return result.get(0).getAccountBalance();
        } else {
            return null;
        }

    }

    public User getUser(String userId) {
        List<User> result = users.stream().filter(user -> user.getUsername().equals(userId)).collect(Collectors.toList());
        if (!result.isEmpty()) {
            return result.get(0);
        } else {
            return null;
        }

    }

}
