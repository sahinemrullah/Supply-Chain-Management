package com.webapi.application.servlets;

import com.webapi.application.abstractions.IRequest;
import com.webapi.application.abstractions.IRequestHandler;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.webapi.application.abstractions.IJsonParser;
import com.webapi.application.abstractions.IResult;
import com.webapi.application.concretes.GsonJsonParser;
import com.webapi.application.exceptions.ModelValidationException;
import com.webapi.application.exceptions.NotFoundException;
import com.webapi.application.utils.HttpServletUtils;
import java.io.IOException;

public abstract class BaseServlet<T extends IRequest, U extends Object> extends HttpServlet {

    protected abstract IRequestHandler<T, U> getRequestHandler();
    
    protected abstract T parseRequest(HttpServletRequest httpRequest) throws ModelValidationException;

    protected void processRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        try {
            httpResponse.setContentType(HttpServletUtils.MEDIA_TYPE_JSON);

            T request = parseRequest(httpRequest);

            IResult<U> result = getRequestHandler().handle(request);

            if (result.isSucceeded()) {
                U item = result.getItem();
                if (item != null)
                    httpResponse.getWriter().write(getJsonParser().toJson(item));
            } else {
                httpResponse.setStatus(400);
                httpResponse.getWriter().write(getJsonParser().toJson(result.getErrors()));
            }
        } catch (ModelValidationException mx) {
            httpResponse.setStatus(400);
            httpResponse.getWriter().write(mx.getMessage());
        } catch (IOException ex) {
            throw ex;
        } catch (NotFoundException ex) {
            httpResponse.setStatus(404);
            httpResponse.getWriter().write(ex.getMessage());
        } catch (Exception ex) {
            httpResponse.setStatus(500);
        }
    }

    protected IJsonParser getJsonParser() {
        return new GsonJsonParser();
    }
    
    protected Integer tryParseInt(String str) {
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }
    
    protected Integer getUserId(HttpServletRequest httpRequest) {
        return tryParseInt((String) httpRequest.getAttribute("userId"));
    } 
    
    protected boolean getIsRetailer(HttpServletRequest httpRequest) {
        return (boolean) httpRequest.getAttribute("isRetailer");
    }
}
