package com.niit.athometicDemo.service;

import com.niit.athometicDemo.Domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String>generateToken(User user);

}
