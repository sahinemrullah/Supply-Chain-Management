package com.webapi.application.servlets.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"OutOfStockProductListServlet", "PendingOrdersServlet", "ProductListServlet", "EditDiscountServlet", "EditStockServlet", "CreateProductServlet", "ConfirmOrderServlet"})
public class RetailerOnlyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        boolean isRetailer = (boolean) httpRequest.getAttribute("isRetailer");
        
        if(isRetailer)
            chain.doFilter(request, response);
        else {
            httpResponse.setStatus(403);
        }
    }

}
