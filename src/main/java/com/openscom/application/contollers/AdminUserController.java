package com.openscom.application.contollers;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.openscom.application.models.AdminUser;
import com.openscom.application.services.AdminUserService;

@RestController
public class AdminUserController {
	
	@Autowired
	AdminUserService userService;
	
	@RequestMapping("/hello")
	public String hello(){
		return "Hello";
	}

	@RequestMapping("/admin/users")
	public ArrayList<AdminUser> getUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping("/admin/users/{userId}")
	public AdminUser getUser(@PathVariable long userId){
		return userService.getUser(userId);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/admin/users")
	public ResponseEntity<AdminUser> createUser(@RequestBody AdminUser user){
		ArrayList<AdminUser> allAdminUsers = userService.getAllUsers();
		for (Iterator<AdminUser> iterator = allAdminUsers.iterator(); iterator.hasNext();) {
			AdminUser adminUser = (AdminUser) iterator.next();
			if(adminUser.getEmail().equalsIgnoreCase(user.getEmail())){
				return new ResponseEntity<>(adminUser, HttpStatus.NOT_MODIFIED);
			}
		}
		return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
	}
	
	@RequestMapping( method = RequestMethod.PUT, path = "/admin/users/{userId}")
	public AdminUser updateUser(@PathVariable long userId, @RequestBody AdminUser user){
		return userService.updateUser(userId, user);
	}
	
	@RequestMapping( method = RequestMethod.DELETE, path = "/admin/users/{userId}")
	public ResponseEntity<AdminUser> deleteUser(@PathVariable long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
