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

public class JWTAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();
        if (!uri.contains("register") && !uri.contains("login")) {
            String header = httpRequest.getHeader(HttpServletUtils.AUTH_HEADER);
            if (header == null || !header.startsWith(HttpServletUtils.AUTH_PREFIX)) {
                httpResponse.setStatus(401);
            } else {
                String authToken = header.substring(HttpServletUtils.AUTH_PREFIX.length());

                Claims claims = EncryptionUtils.decodeJWT(authToken);

                httpRequest.setAttribute("userId", (String) claims.get("userId"));
                httpRequest.setAttribute("isRetailer", (boolean) claims.get("isRetailer"));
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

}
