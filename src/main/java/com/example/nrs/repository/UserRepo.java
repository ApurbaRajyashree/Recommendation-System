package com.example.nrs.repository;

import com.example.nrs.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserEmail(String email);

    User findByUserFullName(String name);

}
