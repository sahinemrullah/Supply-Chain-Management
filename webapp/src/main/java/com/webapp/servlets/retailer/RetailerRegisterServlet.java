package com.webapp.servlets.retailer;

import com.webapp.models.RegisterModel;
import com.webapp.servlets.BaseServlet;
import com.webapp.utils.RequestBuilder;
import com.webapp.utils.Result;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "RetailerSignUpServlet", urlPatterns = {"/tedarikci/kayitol"})
public class RetailerRegisterServlet extends BaseServlet {
    

    @Override
    protected String getJSPPage() {
        return "/WEB-INF/retailer/register.jsp";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(getJSPPage()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RegisterModel model = new RegisterModel();
        model.setName(request.getParameter("name"));
        model.setPhoneNumber(request.getParameter("phoneNumber"));
        model.setEmail(request.getParameter("email"));
        model.setPassword(request.getParameter("password"));
        model.setPasswordVerification(request.getParameter("passwordVerification"));

        Result result = RequestBuilder.create()
                                        .withURL("retailers/register")
                                        .post(model);
        
        processResult(result, request, response);
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("/tedarikci/giris");
    }
    
}
