package com.royasoftware.school.settings.security;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.Uninterruptibles;

@Component
public class AccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    /**
     * The Logger for this class.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * A Spring Security UserDetailsService implementation based upon the
     * Account entity model.
     */
    @Autowired(required = false)
    private CustomUserDetailsService userDetailsService;

    /**
     * A PasswordEncoder instance to hash clear test password values.
     */
    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    public AccountAuthenticationProvider(){
    	logger.info("init AccountAuthenticationProvider");
    }
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
    	logger.info("> additionalAuthenticationChecks");
    	
    	System.out.println("AccountAuthenticationProvider > additionalAuthenticationChecks");

        if (token.getCredentials() == null || userDetails.getPassword() == null) {
            throw new BadCredentialsException("Credentials may not be null.");
        }
		logger.info("token.getCredentials()="+token.getCredentials()); 
		logger.info("userDetails.getPassword()="+userDetails.getPassword()); 

        if (!passwordEncoder.matches((String) token.getCredentials(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials.");
        }

        logger.info("< additionalAuthenticationChecks");
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
    	logger.info("retrieveUser username="+username);
    	System.out.println("AccountAuthenticationProvider retrieveUser username="+username);
//		//Simulate delay of a real network connection to see the animation on front end 
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//    	Uninterruptibles.sleepUninterruptibly(2000, TimeUnit.MILLISECONDS);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        logger.info("< retrieveUser");
        return userDetails;
    }


}
