package com.openscom.application.repositories;

import org.springframework.data.repository.CrudRepository;

import com.openscom.application.models.AdminUser;

public interface AdminUserRepository extends CrudRepository<AdminUser, Long>{

	AdminUser findByEmail(String email);
}
