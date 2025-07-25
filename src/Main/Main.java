package Main;

import Entity.User;
import Service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Scanner sc=new Scanner(System.in);
    static Main main=new Main();
    static UserService userService=new UserService();
    public static void main(String[] args) {
        while(true) {
            System.out.println("Enter your username: ");
            String username = sc.next();
            System.out.println("Enter your password: ");
            String password = sc.next();
            User user = userService.login(username, password);
            if (user != null && user.getRole().equals("admin")) {
                System.out.println("You are Logged in successfully");
                main.initAdmin();
            } else if (user != null && user.getRole().equals("user")) {
                System.out.println("You are Logged in successfully");
                main.initCustomer(user);
            } else {
                System.out.println("Login Failed");
            }
        }
    }
    private void initAdmin()
    {
        boolean flag=true;
        String userId="";
        while (flag) {
            System.out.println("1. Exit/Logout");
            System.out.println("2. Create a customer account");
            System.out.println("3. See all transactions");
            System.out.println("4. Check Account Balance");
            System.out.println("5. Approve check book request");
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1:
                    flag=false;
                    System.out.println("You have successfully logged out...");
                    break;
                case 2:
                    main.addNewCustomer();
                    break;
                case 3:
                     System.out.println("Enter User Id: ");
                     userId=sc.next();
                     main.printTransaction(userId);
                     break;
                case 4:
                     System.out.println("Enter User Id: ");
                     userId=sc.next();
                     Double accBalance= main.checkAccountBalance(userId);
                     System.out.println("Your account balance is "+accBalance);
                     break;
                case 5:
                    List<String> userIds=main.getUserIdforCheckBookRequest();
                    System.out.println("Please select user id from below..");
                    System.out.println(userIds);
                    System.out.println("Enter the userId that you going to approve: ");
                    userId=sc.next();
                    main.approveCheckBookRequest(userId);
                    System.out.println("CheckBook request is approved..");
                    break;
                default:
                    System.out.println("Wrong choice");

            }
        }
    }
    private void approveCheckBookRequest(String userId)
    {
        userService.approveCheckBookRequest(userId);
    }
    private List<String> getUserIdforCheckBookRequest()
    {
        return userService.getUserIdforCheckBookRequest();
    }
    private void addNewCustomer()
    {
        System.out.println("Enter Username: ");
        String username=sc.next();
        System.out.println("Enter password: ");
        String password=sc.next();
        System.out.println("Enter contact number: ");
        String contactNumber=sc.next();
        boolean result=userService.addNewCustomer(username,password,contactNumber);
        if(result)
        {
            System.out.println("Customer account is created...");
        }
        else
        {
            System.out.println("Customer account creation failed...");
        }
    }
    private void initCustomer(User user)
    {
        boolean flag=true;
        while(flag)
        {
            System.out.println("1. Exit/Logout");
            System.out.println("2. Check account balance");
            System.out.println("3. Fund transfer");
            System.out.println("4. See all transactions");
            System.out.println("5. Raise cheque book request");
            int choice=sc.nextInt();
            switch (choice)
            {
                case 1:
                    flag=false;
                    System.out.println("You have successfully logged out...");
                    break;
                case 2:
                    Double accountBalance=main.checkAccountBalance(user.getUsername());
                    if(accountBalance!=null)
                        System.out.println("Your account balance is "+accountBalance);
                    else
                        System.out.println("Check your username");
                    break;
                case 3:
                    main.fundTransfer(user);
                    break;
                case 4:
                    main.printTransaction(user.getUsername());
                    break;
                case 5:
                    String userId=user.getUsername();
                    Map<String,Boolean> result=getAllChequeBookRequest();
                    if(result.containsKey(userId) && result.get(userId))
                        System.out.println("You have already raised the request and it is already approved");
                    else if(result.containsKey(userId) && !result.get(userId))
                        System.out.println("You have already raised a request and it is pending for approval");
                    else {
                        main.raiseChequeBookRequest(userId);
                        System.out.println("Request raised successfully");
                    }
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
    private Map<String,Boolean> getAllChequeBookRequest()
    {
        return userService.getAllChequeBookRequest();
    }
    private void raiseChequeBookRequest(String userId)
    {
        userService.raiseChequeBookRequest(userId);
    }
    private void printTransaction(String userId)
    {
        userService.printTransaction(userId);
    }
    private Double checkAccountBalance(String username)
    {
        return userService.checkAccountBalance(username);
    }
    private void fundTransfer(User user)
    {
        System.out.println("Enter payee account username");
        String payeeusername=sc.next();
        User payeeuser=getUser(payeeusername);
        if(payeeuser!=null)
        {
            System.out.println("Enter the amount to be transfer");
            Double amount=sc.nextDouble();
            Double accbalance=checkAccountBalance(user.getUsername());
            if(amount<=accbalance)
            {
                boolean result=userService.transferAmount(user.getUsername(),payeeusername,amount);
                if(result)
                    System.out.println("Amount transferred successfully...");
                else
                    System.out.println("Transaction failed...");
            }
            else
            {
                System.out.println("Your balance is insufficient: "+accbalance);
            }
        }
        else
        {
            System.out.println("Please enter valid username...");
        }
    }
    private User getUser(String accId)
    {
        return userService.getUser(accId);
    }
}