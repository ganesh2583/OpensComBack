package com.openscom.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.openscom.application.CustomAdminUserDetails;
import com.openscom.application.repositories.AdminUserRepository;

@Configuration
@EnableResourceServer
@EnableWebSecurity
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
    private AdminUserRepository adminUserRepository;
    
    /*@Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }*/

	@Autowired
    public void configureGlobal(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        /*authenticationManagerBuilder.inMemoryAuthentication()
        	.withUser("admin").password("admin").roles("ROLE");*/
		authenticationManagerBuilder.userDetailsService(new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				System.out.println(email);
				System.out.println(adminUserRepository.findByEmail(email));
				return new CustomAdminUserDetails(adminUserRepository.findByEmail(email)) ;
			}
		})
    	.passwordEncoder(new BCryptPasswordEncoder());
    }
    
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated().and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.csrf().disable();
	}
	
	@Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
         resources.resourceId(AuthorizationServerConfig.RESOURCES_IDS);
    }
}
