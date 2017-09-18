package com.openscom.application.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.openscom.application.models.AdminUser;
import com.openscom.application.repositories.AdminUserRepository;

@Service
public class AdminUserService {
	
	@Autowired
	private AdminUserRepository userRepository;
	//@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public AdminUser getUser(long userId){
		return userRepository.findOne(userId);
	}
	
	public ArrayList<AdminUser> getAllUsers(){
		ArrayList<AdminUser> usersList = (ArrayList<AdminUser>) userRepository.findAll();
		return usersList;
	}
	
	public AdminUser addUser(AdminUser user){
		//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public AdminUser updateUser(long userId, AdminUser user){
		//user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserId(userId);
		return userRepository.save(user);
	}
	
	public void deleteUser(long userId){
		userRepository.delete(userId);
	}

}
