package com.niit.athometicDemo.repository;

import com.niit.athometicDemo.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
 public User findByUserNameAndUserPassword(String username,String password);
}
