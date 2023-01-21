package com.webapp.servlets.retailer;

import com.google.gson.reflect.TypeToken;
import com.webapp.models.PaginatedListModel;
import com.webapp.models.supplier.OrderHistoryModel;
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

@WebServlet(name = "RetailerHomeServlet", urlPatterns = {"/tedarikci/", "/tedarikci"})
public class RetailerHomeServlet extends PaginatedServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        StringBuilder builder = new StringBuilder();
        
        builder.append("retailers/")
                .append(getUserId(session))
                .append("/orders");

        Result result = RequestBuilder.create()
                .withURL(builder.toString())
                .withToken(getToken(session))
                .get(parsePagingParameters(request));
        
        processResult(result, request, response);
    }

    @Override
    protected String getJSPPage() {
        return "/WEB-INF/retailer/home.jsp";
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        Type type = TypeToken.getParameterized(PaginatedListModel.class, OrderHistoryModel.class).getType();
        
        PaginatedListModel<OrderHistoryModel> model = GSON.fromJson(result.getResponseMessage(), type);
        
        setModel(model, request);
        
        request.getRequestDispatcher(getJSPPage()).forward(request, response);
    }

}
