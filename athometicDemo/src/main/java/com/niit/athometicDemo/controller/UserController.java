package com.niit.athometicDemo.controller;

import com.niit.athometicDemo.Domain.User;
import com.niit.athometicDemo.exception.UserNotFoundException;
import com.niit.athometicDemo.service.SecurityTokenGenerator;
import com.niit.athometicDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
public class UserController {
    private ResponseEntity responseEntity;
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;

    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody User user) throws UserNotFoundException {
        Map<String, String> map = null;
        try {
            User user1 = userService.findByUsernameAndPassword(user.getUserName(), user.getUserPassword());
            if (user1.getUserName().equals(user.getUserName())) {
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity<>(map, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return responseEntity;
    }

    @PostMapping("/register")
    public ResponseEntity saveUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return responseEntity = new ResponseEntity<>("User Created", HttpStatus.CREATED);

    }

    @GetMapping("/api/v1/userservice/users")
    public ResponseEntity getAllUsers() throws UserNotFoundException {
        List<User> list = userService.getAllUsers();
        responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
        return responseEntity;
    }
    @DeleteMapping("/api/v1/userservice/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") int userId) throws UserNotFoundException {
    ResponseEntity responseEntity =null;
    try {
          userService.deleteCustomer(userId);
        responseEntity = new ResponseEntity("Successfully deleted the 1 record",HttpStatus.OK);
    }
    catch (UserNotFoundException cnfe){
        throw  new UserNotFoundException();
    }catch (Exception exception){
        responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
}
}
