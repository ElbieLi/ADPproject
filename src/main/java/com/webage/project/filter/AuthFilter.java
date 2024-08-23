package com.webage.project.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.webage.project.jwt.JWTHelper;

@Component
@Order(1)
public class AuthFilter implements Filter{

	private String auth_scope = "com.webage.auth.apis";
	private String api_scope = "com.webage.data.apis";
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//get authorization header
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		
		
		//if token header is not null and false, skip authenticaiton check
		String tokenheader = req.getHeader("tokencheck");
		if( tokenheader != null && !tokenheader.equalsIgnoreCase("true") ) {
			chain.doFilter(request, response);
			return;		
		}
		
		//the following cases will not has authentication check
		if(   !uri.startsWith("/api/events") 
	       && !uri.startsWith("/api/registrations")
	       && !uri.equals("/api/customers")
	       ) {
			chain.doFilter(request, response);
			return;			
		}else{
			//check if the header of the client's request contains JWT token
			String authheader = req.getHeader("authorization");
			if(authheader != null && authheader.length() > 7 && authheader.startsWith("Bearer")) {
				String jwt_token = authheader.substring(7, authheader.length());
				if(JWTHelper.verifyToken(jwt_token)) {
					String request_scopes = JWTHelper.getScopes(jwt_token);
					if(request_scopes.contains(api_scope) || request_scopes.contains(auth_scope)) {
						chain.doFilter(request, response);
						return;
					}
				}
			}
		}		
		//return error message if authentication failed
		res.sendError(HttpServletResponse.SC_FORBIDDEN, "failed authentication");

	}
	
}
