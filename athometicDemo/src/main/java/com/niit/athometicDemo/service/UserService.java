package com.niit.athometicDemo.service;

import com.niit.athometicDemo.Domain.User;
import com.niit.athometicDemo.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public User findByUsernameAndPassword(String username,String password)throws UserNotFoundException;
    List<User> getAllUsers();
    boolean deleteCustomer(int customerId)throws UserNotFoundException;

}
