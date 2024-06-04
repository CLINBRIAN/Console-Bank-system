/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bank.Service;

import com.bank.Entity.User;
import com.bank.Repository.UserRepository;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kamau
 */
public class UserService {

    private UserRepository userRepository = new UserRepository();

    public void printUsers() {
        userRepository.printUsers();
    }

    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

    public boolean addCustomer(String username, String password, String contactNumber) {
        return userRepository.addCustomer(username, password, contactNumber);
    }

    public Double checkBankBalance(String userId) {
        return userRepository.checkBankBalance(userId);
    }

    public User getUser(String userId) {
        return userRepository.getUser(userId);

    }

    public boolean transferAmount(String userId, String payeeUserId, Double amount) {
        return userRepository.transferAmount(userId, payeeUserId, amount);
    }

    public void printTransactions(String userId) {
        userRepository.printTransactions(userId);

    }

    public void raiseChequebookRequest(String userId) {
        userRepository.raiseChequebookRequest(userId);
    }

    public Map<String, Boolean> getAllChequebookRequest() {
        return userRepository.getAllChequebookRequest();
    }

    public List<String> getUserIdforChequeBookRequest() {
        return userRepository.getUserIdforChequeBookRequest();
    }

    public void approveChequeBookRequest(String userId) {
        userRepository.approveChequeBookRequest(userId);
    }
}
