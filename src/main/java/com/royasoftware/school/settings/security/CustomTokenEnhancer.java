package com.royasoftware.school.settings.security;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    	
    	logger.info("enhance");
    	System.out.println("CustomTokenEnhancer enhance");
        User user = (User) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();

//        additionalInfo.put("customInfo", "some_stuff_here");
        try {
        	
			additionalInfo.put("authority", user.getAuthorities().iterator().next().getAuthority().toLowerCase().replace("role_", ""));
			
		} catch (Exception e) {
			additionalInfo.put("authority", null);
			e.printStackTrace();
		}

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
        calendar.add(Calendar.SECOND, 300);
//        System.out.println(calendar.getTime());
        ((DefaultOAuth2AccessToken) accessToken).setExpiration(new Date(calendar.getTimeInMillis()));
        return accessToken;
    }
}
