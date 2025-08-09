package com.barath.restgoals.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	private static ArrayList<User> usersList = new ArrayList<User>();
	private static int usersCount = 1;
	
	static {
		usersList.add(new User(usersCount++,"Barathwaj Kesavan", LocalDate.now().minusYears(30)));
		usersList.add(new User(usersCount++,"Prasad Cinthamani", LocalDate.now().minusYears(31)));
	}
	
	public ArrayList<User> findAllUsers(){
		return usersList;
	}
	public User findUserByID(int id){
		
		//return usersList.stream().filter(user -> user.getId()==id).collect(Collectors.toList()).get(0);
		return usersList.stream().filter(user -> user.getId()==id).findFirst().orElse(null);
	}
	public User addUser(User user) {
		user.setId(usersCount++);
		usersList.add(user);
		return user;
	}
	
	public void deleteUser(int id) {
		Predicate<User> perdicate = user -> user.getId()==id;
		usersList.removeIf(perdicate);
	}

}
