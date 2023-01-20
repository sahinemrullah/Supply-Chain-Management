
package com.webapp.servlets;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public abstract class PaginatedServlet extends BaseServlet {

    
    protected Map<String, String> parsePagingParameters(HttpServletRequest request) {
        HashMap<String, String> parameters = new HashMap<>();
        
        String pageNumberStr = request.getParameter("pageNumber");
        
        if(pageNumberStr != null)
            parameters.put("pageNumber", pageNumberStr);
        
        String pageSizeStr = request.getParameter("pageSize");
        
        if(pageSizeStr != null)
            parameters.put("pageSize", pageSizeStr);
        
        return parameters;
    }
}
