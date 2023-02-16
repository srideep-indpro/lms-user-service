package com.vlabs.lmsuser.repository;

import com.vlabs.lmsuser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer > {
    public User findByUserName(String name);
}
