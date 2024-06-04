/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bank.Main;

import com.bank.Entity.User;
import com.bank.Service.UserService;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author kamau
 */
public class Main {

    static Scanner scanner = new Scanner(System.in);
    static UserService userService = new UserService();

    public static void main(String[] args) {

        while (true) {
            System.out.println("Enter the userName");
            String userName = scanner.next();

            System.out.println("Enter the password");
            String password = scanner.next();

            User user = userService.login(userName, password);
            if (user != null && user.getRole().equals("admin")) {
                initAdmin();
            } else if (user != null && user.getRole().equals("user")) {
                initCustomer(user);
            } else {
                System.out.println("log in failed");
            }
        }

    }

    private static void initAdmin() {
        boolean flag = true;
        String userId = "";
        while (flag) {
            System.out.println("You are an admin");
            System.out.println("1. exit/logout");
            System.out.println("2. add a new customer");
            System.out.println("3. View Transactions");
            System.out.println("4. Check balance to specific user Id");
            System.out.println("5. Approve cheque Book");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    flag = false;
                    System.out.println("logout success");
                    break;
                case 2:
                    addNewCustomer();
                    break;
                case 3:
                    System.out.println("Enter userId to view");
                    userId = scanner.next();

                    printTransactions(userId);
                    break;
                case 4:
                    System.out.println("Enter userId to check balance");
                    userId = scanner.next();

                    Double balance = checkBankBalance(userId);
                    System.out.println("the balance: " + balance);
                    break;
                case 5:
                    List<String> userIds = getUserIdforChequeBookRequest();
                    System.out.println("Enter the value to approve the cheque Book request below..");
                    System.out.println(userIds);

                    userId = scanner.next();

                    approveChequeBookRequest(userId);
                    System.out.println("Cheque Book request approved..");
                    break;
                default:
                    System.out.println("wrong choice");
            }

        }

    }

    private static void approveChequeBookRequest(String userId) {
        userService.approveChequeBookRequest(userId);

    }

    private static List<String> getUserIdforChequeBookRequest() {
        return userService.getUserIdforChequeBookRequest();

    }

    private static void addNewCustomer() {
        System.out.println("Enter new userName");
        String userName = scanner.next();

        System.out.println(" Enter password");
        String password = scanner.next();

        System.out.println("Enter contact number");
        String contactNumber = scanner.next();

        boolean result = userService.addCustomer(userName, password, contactNumber);

        if (result) {
            System.out.println("Customer account created");
        } else {
            System.out.println("account creation failed");
        }

    }

    private static void initCustomer(User user) {
        System.out.println("You are a customer");
        boolean flag = true;
        while (flag) {
            System.out.println("1. exit/logout");
            System.out.println("2. Check Bank balance");
            System.out.println("3. Transfer funds");
            System.out.println("4. Print all transactions");
            System.out.println("5. Raise Cheque book request");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    flag = false;
                    System.out.println("logout success");
                    break;
                case 2:
                    Double balance = checkBankBalance(user.getUsername());
                    if (balance != null) {
                        System.out.println("your bank balance is " + balance);
                    } else {
                        System.out.println("check the username");
                    }
                    break;
                case 3:
                    fundTransfer(user);
                    break;
                case 4:
                    printTransactions(user.getUsername());
                    break;
                case 5:
                    String userId = user.getUsername();
                    Map<String, Boolean> map = getAllChequebookRequest();

                    if (map.containsKey(userId) && map.get(userId)) {
                        System.out.println("You have raised a request and is already approved");
                    } else if (map.containsKey(userId) && !map.get(userId)) {
                        System.out.println("You have raised a request and is pending for approval");
                    } else {
                        raiseChequebookRequest(userId);
                        System.out.println("Request raised successfully..");
                    }

                    break;
                default:
                    System.out.println("wrong choice");
            }
        }
    }

    private static void raiseChequebookRequest(String userId) {
        userService.raiseChequebookRequest(userId);

    }

    private static Map<String, Boolean> getAllChequebookRequest() {
        return userService.getAllChequebookRequest();
    }

    private static void printTransactions(String userId) {
        userService.printTransactions(userId);
    }

    private static void fundTransfer(User userDetails) {
        System.out.println("Enter payee account user ID");
        String payeeAccountId = scanner.next();

        User user = getUser(payeeAccountId);
        if (user != null) {
            System.out.println("Enter amount to transfer");
            Double amount = scanner.nextDouble();

            Double userAccountBalance = checkBankBalance(userDetails.getUsername());
            if (userAccountBalance >= amount) {
                boolean result = userService.transferAmount(userDetails.getUsername(), payeeAccountId, amount);
                if (result) {
                    System.out.println("Transfer success...1");
                } else {
                    System.out.println("Transfer failed");
                }

            } else {
                System.out.println("Insufficient account balance: " + userAccountBalance);
            }
        } else {
            System.out.println("Please enter a valid username...");
        }

    }

    private static Double checkBankBalance(String userId) {
        return userService.checkBankBalance(userId);
    }

    private static User getUser(String userId) {
        return userService.getUser(userId);

    }

}
