package com.webapp.servlets.invoices;

import com.google.gson.reflect.TypeToken;
import com.webapp.models.PaginatedListModel;
import com.webapp.models.invoices.InvoiceListModel;
import com.webapp.servlets.PaginatedServlet;
import com.webapp.utils.RequestBuilder;
import com.webapp.utils.Result;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.lang.reflect.Type;

@WebServlet(name = "InvoiceListServlet", urlPatterns = {"/faturalarim/", "/faturalarim"})
public class InvoiceListServlet extends PaginatedServlet {

    @Override
    protected String getJSPPage() {
        return "/WEB-INF/invoice/invoiceList.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        
        StringBuilder builder = new StringBuilder();
        if (session.getAttribute("role").equals("retailer")) {
            builder.append("retailers/");
        } else {
            builder.append("suppliers/");
        }

        builder.append(getUserId(session))
                .append("/invoices");

        Result result = RequestBuilder.create()
                .withURL(builder.toString())
                .withToken(getToken(session))
                .get(parsePagingParameters(request));

        processResult(result, request, response);
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Type type = TypeToken.getParameterized(PaginatedListModel.class, InvoiceListModel.class).getType();
        PaginatedListModel<InvoiceListModel> model = GSON.fromJson(result.getResponseMessage(), type);
        setModel(model, request);
        request.getRequestDispatcher(getJSPPage()).forward(request, response);
    }

}
