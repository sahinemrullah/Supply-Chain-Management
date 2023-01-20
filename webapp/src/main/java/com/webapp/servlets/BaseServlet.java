
package com.webapp.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.webapp.models.AccessToken;
import com.webapp.utils.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public abstract class BaseServlet extends HttpServlet {
    
    protected static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    
    private static final String TOKEN = "token";
    private static final String USER_ID = "userId";
    private static final String ROLE = "role";
    
    protected static final String PAGE_401 = "/WEB-INF/index/401.jsp";
    protected static final String PAGE_403 = "/WEB-INF/index/403.jsp";
    protected static final String PAGE_404 = "/WEB-INF/index/404.jsp";
    protected static final String PAGE_500 = "/WEB-INF/index/500.jsp";
    
    protected abstract String getJSPPage();
    
    protected void processResult(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        switch(result.getStatusCode()) {
            case 200:
            case 201:
            case 204:
                onSuccessfullResponse(result, request, response);
                break;
            case 400:
                Map<String, String[]> map = GSON.fromJson(result.getResponseMessage(), Map.class);
                
                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                    request.setAttribute(entry.getKey() + "Error", entry.getValue());
                }
                
                for (Map.Entry<String, String[]> parameter : request.getParameterMap().entrySet()) {
                    request.setAttribute(parameter.getKey(), parameter.getValue()[0]);
                }
                
                request.getRequestDispatcher(getJSPPage()).forward(request, response);
                break;
            case 401:
                request.getRequestDispatcher(PAGE_401).forward(request, response);
                break;
            case 403:
                request.getRequestDispatcher(PAGE_403).forward(request, response);
                break;
            case 404:
                request.getRequestDispatcher(PAGE_404).forward(request, response);
                break;
            default:
                request.setAttribute("message", result.getResponseMessage());
                request.getRequestDispatcher(PAGE_500).forward(request, response);
                break;
        }
    }
    
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
    }
    
    protected boolean parseAccessToken(HttpServletRequest request, Result result) {
        try {
            AccessToken token = GSON.fromJson(result.getResponseMessage(), AccessToken.class);
            HttpSession session = request.getSession();
            session.setAttribute(TOKEN, token.getToken());
            session.setAttribute(ROLE, token.getRole());
            session.setAttribute(USER_ID, (Integer)token.getUserId());
            return true;
        } catch(JsonSyntaxException ex) {
            return false;
        }
    }
    
    protected Integer getUserId(HttpSession session) {
        return (Integer) session.getAttribute(USER_ID);
    }
    
    protected String getToken(HttpSession session) {
        return (String) session.getAttribute(TOKEN);
    }
    
    protected String getRole(HttpSession session) {
        return (String) session.getAttribute(ROLE);
    }
    
    protected <T> void setModel(T model, HttpServletRequest request) {
        request.setAttribute("model", model);
    }
}
