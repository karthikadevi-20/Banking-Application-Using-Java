package Repository;

import Entity.Transaction;
import Entity.User;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class UserRepository {
    private static  Set<User> users=new HashSet<>();
    private static List<Transaction> transactions=new ArrayList<>();
    private static Map<String,Boolean> chequeBookRequest=new HashMap<>();
    static
    {
        User user1=new User("admin","admin","705667842","admin",0.0);
        User user2=new User("user2","user2","756689234","user",1000.0);
        User user3=new User("user3","user3","743255234","user",2000.0);
        User user4=new User("user4","user4","743259864","user",2000.0);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }
    public void printUsers()
    {
        System.out.println(users);
    }
    public User login(String username,String password)
    {
         List<User> finalList=users.stream().filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).collect(Collectors.toList());
         if(!finalList.isEmpty())
             return finalList.get(0);
         return null;
    }
    public boolean addNewCustomer(String username,String password,String contactNumber)
    {
        User user=new User(username,password,contactNumber,"user",500.0);
        return users.add(user);// this method return a boolean value
    }

    public Double checkAccountBalance(String username) {
        List<User> result= users.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        if(!result.isEmpty())
            return result.get(0).getAccountBalance();
        return null;
    }
    public User getuser(String accId)
    {
        List<User> result=users.stream().filter(user->user.getUsername().equals(accId)).collect(Collectors.toList());
        if(!result.isEmpty())
        {
            return result.get(0);
        }
        return null;
    }
    public boolean transferAmount(String userId,String payeeUserId,Double amount)
    {
        boolean isDebit=debit(userId,amount,payeeUserId);
        boolean isCredit=credit(payeeUserId,amount,userId);
        return isCredit && isDebit;
    }

    private boolean debit(String userId,Double amount,String payeeUserId)
    {
        User user=getuser(userId);

        Double accountBalance= user.getAccountBalance();
        Double finalBalance=accountBalance-amount;
        users.remove(user);
        user.setAccountBalance(finalBalance);
        Transaction transaction=new Transaction(payeeUserId,"Debit",LocalDate.now(),amount,accountBalance,finalBalance,userId);
//        System.out.println(transaction);
        transactions.add(transaction);
        return users.add(user);
    }
    private boolean credit(String  payeeUserId,Double amount,String userId)
    {
        User user=getuser( payeeUserId);
        Double accountBalance= user.getAccountBalance();
        Double finalBalance=accountBalance+amount;
        users.remove(user);
        user.setAccountBalance(finalBalance);
        Transaction transaction=new Transaction(userId,"Credit",LocalDate.now(),amount,accountBalance,finalBalance,payeeUserId);
//        System.out.println(transaction);
        transactions.add(transaction);
        return users.add(user);
    }
    public void printTransaction(String userId)
    {
        List<Transaction> result=transactions.stream().filter(transaction -> transaction.getTransactionPerformedBy().equals(userId)).collect(Collectors.toList());
        System.out.println("Date \t User Id \t Amount \t Type \t Initial Balance \t Final Balance");
        System.out.println("-----------------------------------------------------------------------------------");
        for(Transaction i:result)
        {
            System.out.println(i.getTransactionDate()+"\t"+i.getTransactionUserId()+"\t"+i.getTransactionAmount()+"\t\t"+i.getTransactionType()+"\t\t"+i.getInitialBalance()+"\t\t"+i.getFinalBalance());
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }
    public void raiseChequeBookRequest(String userId)
    {
        chequeBookRequest.put(userId,false);
    }
    public Map<String,Boolean> getAllChequeBookRequest()
    {
        return chequeBookRequest;
    }
    public List<String> getUserIdforCheckBookRequest()
    {
        List<String> userId=new ArrayList<>();
        for(Map.Entry<String,Boolean> entry:chequeBookRequest.entrySet())
        {
            if(!entry.getValue())
            {
                userId.add(entry.getKey());
            }
        }
        return userId;
    }
    public void approveCheckBookRequest(String userId)
    {
        if(chequeBookRequest.containsKey(userId))
            chequeBookRequest.put(userId,true);
    }
}
