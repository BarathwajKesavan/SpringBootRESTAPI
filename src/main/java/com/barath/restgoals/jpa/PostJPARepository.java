package com.barath.restgoals.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barath.restgoals.user.Post;

public interface PostJPARepository extends JpaRepository<Post, Integer>{

}
