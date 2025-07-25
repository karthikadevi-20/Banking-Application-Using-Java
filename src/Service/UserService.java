package Service;

import Entity.User;
import Repository.UserRepository;

import java.util.List;
import java.util.Map;

public class UserService {
    UserRepository userRepository=new UserRepository();
    public void printUsers()
    {
        userRepository.printUsers();
    }
    public User login(String username, String password)
    {
        return userRepository.login(username,password);
    }
    public boolean addNewCustomer(String username,String password,String contactNumber)
    {
        return userRepository.addNewCustomer(username,password,contactNumber);
    }

    public Double checkAccountBalance(String username) {
        return userRepository.checkAccountBalance(username);
    }
    public User getUser(String accId)
    {
        return userRepository.getuser(accId);
    }
    public boolean transferAmount(String userId,String payeeUserId,Double amount)
    {
        return userRepository.transferAmount(userId,payeeUserId,amount);
    }

    public void printTransaction(String userId) {
        userRepository.printTransaction(userId);
    }
    public void raiseChequeBookRequest(String userId)
    {
        userRepository.raiseChequeBookRequest(userId);
    }
    public Map<String,Boolean> getAllChequeBookRequest()
    {
        return userRepository.getAllChequeBookRequest();
    }
    public List<String> getUserIdforCheckBookRequest()
    {
        return userRepository.getUserIdforCheckBookRequest();
    }
    public void approveCheckBookRequest(String userId)
    {
        userRepository.approveCheckBookRequest(userId);
    }
}
