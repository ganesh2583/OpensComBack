package com.openscom.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableResourceServer
public class OpensComBackApplication extends SpringBootServletInitializer {
	

	public static void main(String[] args) {
		SpringApplication.run(OpensComBackApplication.class, args);
	}
	
	/*@Autowired
	public void authenticationManager(AuthenticationManagerBuilder authenticationManagerBuilder, AdminUserRepository adminUserRepository) throws Exception{
		authenticationManagerBuilder.userDetailsService(new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				System.out.println(email);
				System.out.println(adminUserRepository.findByEmail(email));
				return new CustomAdminUserDetails(adminUserRepository.findByEmail(email)) ;
			}
		});
	}*/
	
}
