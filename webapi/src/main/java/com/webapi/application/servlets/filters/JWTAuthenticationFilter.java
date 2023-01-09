/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapi.application.servlets.filters;

import com.webapi.application.utils.EncryptionUtils;
import com.webapi.application.utils.HttpServletUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class JWTAuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String uri = httpRequest.getRequestURI();
        if(!uri.contains("register") && !uri.contains("login")) {
            String header = httpRequest.getHeader(HttpServletUtils.AUTH_HEADER);
            if (header == null || !header.startsWith(HttpServletUtils.AUTH_PREFIX)) {
                httpResponse.setStatus(401);
            } else {
                String authToken = header.substring(HttpServletUtils.AUTH_PREFIX.length());

                Claims claims = EncryptionUtils.decodeJWT(authToken);

                Date now = new Date(System.currentTimeMillis());

                if(claims.getExpiration().compareTo(now) < 0) {
                    httpResponse.setStatus(401);
                } else {
                    chain.doFilter(request, response);
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

}
