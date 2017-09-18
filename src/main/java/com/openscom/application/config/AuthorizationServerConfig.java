package com.openscom.application.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	static final String CLIEN_ID = "osComClientId";
    static final String GRANT_TYPE = "password";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String RESOURCES_IDS = "osComClientResourceId";
    static final String SIGNING_KEY = "osComApp";
    
    /*@Autowired
    private TokenStore tokenStore;*/

    /*@Autowired
    private JwtAccessTokenConverter accessTokenConverter;*/

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                .withClient(CLIEN_ID)
                .secret(SIGNING_KEY)
                .authorizedGrantTypes(GRANT_TYPE)
                .scopes(SCOPE_READ, SCOPE_WRITE)
                .resourceIds(RESOURCES_IDS);
    }
    

    @Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
    
    /*@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        //endpoints.tokenStore(tokenStore)
        endpoints.tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter)
                .tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager);
    }*/
    
    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore())
			.authenticationManager(authenticationManager);
    }
}
