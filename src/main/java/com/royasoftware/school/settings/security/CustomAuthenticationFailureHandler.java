package com.royasoftware.school.settings.security;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler 
implements AuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

  private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void onAuthenticationFailure(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException exception) 
    throws IOException, ServletException {
	  logger.info("onAuthenticationFailure username=");
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      Map<String, Object> data = new HashMap<>();
      data.put(
        "timestamp", 
        Calendar.getInstance().getTime());
      data.put(
        "exception", 
        exception.getMessage());

      response.getOutputStream()
        .println(objectMapper.writeValueAsString(data));
  }
}
