package com.royasoftware.school.settings.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class OAuth2ServerConfiguration {

	private static final String RESOURCE_ID = "restservice";

	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Configuration
	@EnableResourceServer
	protected class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Autowired
		private TokenStore tokenStore;

		private CustomLogoutSuccessHandler customLogoutSuccessHandler;

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			// TokenStore ts = new JwtTokenStore(jwtAccessTokenConverter);
			customLogoutSuccessHandler = new CustomLogoutSuccessHandler(tokenStore);
			// @formatter:off
			resources.resourceId(RESOURCE_ID).tokenStore(tokenStore);
			// @formatter:on
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			// @formatter:off
			http.csrf().disable()
					// .anonymous().and()
					.authorizeRequests()
					// .antMatchers(HttpMethod.OPTIONS,"*").permitAll()
					// .antMatchers("/").permitAll()
					// .antMatchers("/user/**").permitAll()
					// .antMatchers("/api/**").authenticated()//.hasAnyRole("ROLE_USER")//
					// .antMatchers("/actuator/**").authenticated()
					.and().formLogin()
					// .loginPage("/login")
					.loginPage("/oauth/token")
					// .failureUrl("/login?error=1")
					.failureHandler(authenticationFailureHandler()).permitAll().and().logout().logoutUrl("/oauth/logout")
					.logoutSuccessUrl("/").logoutSuccessHandler(customLogoutSuccessHandler).permitAll();
			// .antMatchers("/**").authenticated();
			// @formatter:on
			// http.addFilterBefore( new Auth2FilterRequest(),null);
		}
	}

	@Autowired
	private DataSource dataSource;

	@Configuration
	@EnableAuthorizationServer
	protected class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private JwtAccessTokenConverter jwtAccessTokenConverter;

		// private TokenStore tokenStore = new InMemoryTokenStore();
		@Autowired
		private TokenStore tokenStore;
		// private TokenStore tokenStore = new
		// JwtTokenStore(jwtAccessTokenConverter);

		@Autowired
		private CustomUserDetailsService userDetailsService;

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			// @formatter:off
			endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
					// ErrorFix: Next line fixed the "UserDetailsService is
					// required"-error while token refreshing using
					// refresh_token
			        .accessTokenConverter(jwtAccessTokenConverter)
					.userDetailsService(userDetailsService).accessTokenConverter(jwtAccessTokenConverter)
					.tokenEnhancer(new CustomTokenEnhancer());

			// endpoints.tokenStore(tokenStore)
			// .authenticationManager(authenticationManager)
			// .accessTokenConverter(jwtAccessTokenConverter)
			// .tokenEnhancer(new CustomTokenEnhancer());
			// @formatter:on
        
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
			security.tokenKeyAccess("permitAll()") 
					.checkTokenAccess("isAuthenticated()"); 
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			// @formatter:off
			clients.jdbc(dataSource)
//			 .inMemory()
//			 .withClient("clientapp") 
//			 .secret(new BCryptPasswordEncoder().encode("123456"))
//			 .authorizedGrantTypes("password", "authorization_code", "refresh_token", "client_credentials")
//			 .scopes("read", "write")
//			 .autoApprove(true)

			// .withClient("clientapp")
			// .secret("123456")
			// .authorizedGrantTypes(
			// "password","authorization_code", "refresh_token")
			// .scopes("read", "write")
			// .inMemory()
			// .withClient("clientapp")
			// .secret("123456")
			// .authorizedGrantTypes("refresh_token", "password")
			// .scopes("read", "write")
			// .accessTokenValiditySeconds(15)
			// .withClient("clientapp")
			// .authorizedGrantTypes("password",
			// "authorization_code","refresh_token", "implicit")
			// .secret("123456")
			// .inMemory()
			// .withClient("clientapp")
			// .authorizedGrantTypes("password","refresh_token")
			// .authorities("USER")
			// .scopes("read", "write")
			// .resourceIds(RESOURCE_ID)
			// .secret("123456")

			;
			// @formatter:on
		}

	}

	// @Bean
	// public TokenStore tokenStore() {
	// TokenStore ts = new
	// org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore();
	// return ts;
	// }
	@Bean
	public JdbcTokenStore tokenStore() {

		return new JdbcTokenStore(dataSource);
	}

	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	@Bean
	@Primary
	public AuthorizationServerTokenServices tokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		// ...
		tokenServices.setTokenStore(tokenStore());
		tokenServices.setSupportRefreshToken(true);
		// tokenServices.setClientDetailsService(new
		// CustomUserDetailsService()); //not working
		return tokenServices;
	}

	// @Bean
	// public TokenEnhancer tokenEnhancer() {
	// return new CustomTokenEnhancer();
	// }
	

    	

}
