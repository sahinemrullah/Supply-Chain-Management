package com.webapp.servlets.supplier;

import com.webapp.models.LoginModel;
import com.webapp.servlets.BaseServlet;
import com.webapp.utils.RequestBuilder;
import com.webapp.utils.Result;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SupplierLoginServlet", urlPatterns = {"/satici/giris"})
public class SupplierLoginServlet extends BaseServlet {

    @Override
    protected String getJSPPage() {
        return "/WEB-INF/retailer/login.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(getJSPPage()).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginModel model = new LoginModel();
        model.setEmail(request.getParameter("email"));
        model.setPassword(request.getParameter("password"));

        Result result = RequestBuilder.create()
                .withURL("suppliers/login")
                .post(model);

        processResult(result, request, response);
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (parseAccessToken(request, result)) {
            response.sendRedirect("/satici/");
        } else {
            request.getRequestDispatcher(PAGE_500).forward(request, response);
        }
    }
}
