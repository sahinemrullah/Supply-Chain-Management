package com.webapp.servlets.invoices;

import com.webapp.models.invoices.InvoiceDetailsModel;
import com.webapp.servlets.BaseServlet;
import com.webapp.utils.IntegerUtils;
import com.webapp.utils.RequestBuilder;
import com.webapp.utils.Result;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;

@WebServlet(name = "ViewInvoiceServlet", urlPatterns = {"/faturalarim/goruntule"})
public class ViewInvoiceServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        if (idStr == null || !IntegerUtils.tryParse(idStr)) {
            response.sendRedirect("/");
        }

        HttpSession session = request.getSession();

        HashMap<String, String> parameters = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        String role = (String) session.getAttribute("role");
        if (role.equals("retailer")) {
            builder.append("retailers/");
        } else {
            builder.append("suppliers/");
        }

        builder.append(getUserId(session))
                .append("/invoices/")
                .append(idStr);
        
        Result result = RequestBuilder.create()
                .withURL(builder.toString())
                .withToken(getToken(session))
                .get(parameters);
        
        processResult(result, request, response);
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        InvoiceDetailsModel model = GSON.fromJson(result.getResponseMessage(), InvoiceDetailsModel.class);
        setModel(model, request);

        HttpSession session = request.getSession();

        String role = (String) session.getAttribute("role");
        request.setAttribute("role", role);
        request.setAttribute("id", request.getParameter("id"));

        request.getRequestDispatcher(getJSPPage()).forward(request, response);
    }

    @Override
    protected String getJSPPage() {
        return "/WEB-INF/invoice/details.jsp";
    }
}
