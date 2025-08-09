package com.barath.restgoals.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barath.restgoals.user.User;

public interface UserJPARepository extends JpaRepository<User, Integer> {

}
