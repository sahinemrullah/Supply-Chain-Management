package com.webapi.application.utils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import jakarta.xml.bind.DatatypeConverter;

import io.jsonwebtoken.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import com.google.common.hash.Hashing;
import java.util.HashMap;
import java.util.Map;

public final class EncryptionUtils {

	private static final String SECRET_KEY = "as6df153aFGS52D5S41DF2f1sa2df165sdg4fdfbasdf123etbres";
	
	private EncryptionUtils() {
		
	}
	
	public static String hashString(String str) {
		return Hashing.sha256()
				.hashString(str, StandardCharsets.UTF_8)
				.toString();
	}
	
	public static boolean checkHash(String password, String hashToCompare) {
		return StringUtils.isEqual(hashString(password), hashToCompare);
	}

	public static String createJWT(String id, boolean isRetailer, String issuer, String subject, long ttlMillis) {
		  
	    //The JWT signature algorithm we will be using to sign the token
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);

	    //We will sign our JWT with our ApiKey secret
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", id);
            claims.put("isRetailer", isRetailer);
            
	    //Let's set the JWT Claims
	    JwtBuilder builder = Jwts.builder()
                    .setId(id)
	            .setIssuedAt(now)
	            .setSubject(subject)
	            .setIssuer(issuer)
                    .addClaims(claims)
	            .signWith(signingKey, signatureAlgorithm);
	  
	    //if it has been specified, let's add the expiration
	    if (ttlMillis > 0) {
	        long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }  
	  
	    //Builds the JWT and serializes it to a compact, URL-safe string
	    return builder.compact();
	}
	
	public static Claims decodeJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
		Claims claims = (Claims) Jwts.parserBuilder()
	              .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
	              .build()
	              .parse(jwt)
	              .getBody();
		
	    return claims;
	}

}
