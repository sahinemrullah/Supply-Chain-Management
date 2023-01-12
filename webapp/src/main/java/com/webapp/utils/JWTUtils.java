/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpSession;
import java.util.Map.Entry;

public final class JWTUtils {

    private JWTUtils() {

    }

    public static void parseToken(HttpSession session) {
        String token = (String) session.getAttribute("token");

        if (token != null) {
            int i = token.lastIndexOf('.');
            String withoutSignature = token.substring(0, i+1);
            Claims claims = (Claims) Jwts.parserBuilder()
                                         .build()
                                         .parse(withoutSignature)
                                         .getBody();
            
            for (Entry<String, Object> entry : claims.entrySet()) {
                session.setAttribute(entry.getKey(), entry.getValue());
            }
        }
    }
}
