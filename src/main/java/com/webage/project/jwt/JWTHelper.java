package com.webage.project.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTHelper {

	public static boolean verifyToken(String token) {

		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("me@me.com")
		        .build(); 
		    DecodedJWT jwt = verifier.verify(token);
		    return true;
		} catch (JWTVerificationException exception){
			return false;
		}		
		
	}

	public static String getScopes(String token) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("me@me.com")
		        .build();
		    DecodedJWT jwt = verifier.verify(token);
		    return jwt.getClaim("scopes").asString();
		} catch (JWTVerificationException exception){
			return null;
		}
	}
}
