/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapp.servlets.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();
        String uri = httpRequest.getRequestURI();
        if (uri.contains("kayit")
                || uri.contains("giris")) {
            chain.doFilter(request, response);
        } else {
            String requestedWith = httpRequest.getHeader("X-Requested-With");
            boolean isAJAXRequest = requestedWith != null && requestedWith.equals("XMLHttpRequest");
            httpRequest.setAttribute("isAJAXRequest", isAJAXRequest);
            
            if (session == null || session.getAttribute("token") == null) {
                if (isAJAXRequest) {
                    httpResponse.setStatus(401);
                } else {
                    httpResponse.sendRedirect("/giris");
                }
            } else {
                chain.doFilter(request, response);
            }
        }
    }

}
