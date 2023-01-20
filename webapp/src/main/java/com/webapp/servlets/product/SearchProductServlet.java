package com.webapp.servlets.product;

import com.google.gson.reflect.TypeToken;
import com.webapp.models.PaginatedListModel;
import com.webapp.models.product.ProductSearchModel;
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
import java.util.Map;

@WebServlet(name = "SearchProductServlet", urlPatterns = {"/urun/ara"})
public class SearchProductServlet extends PaginatedServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");

        HttpSession session = request.getSession();

        Map<String, String> parameters = parsePagingParameters(request);

        parameters.put("query", query);

        Result result = RequestBuilder.create()
                .withURL("products/search")
                .withToken(getToken(session))
                .get(parameters);

        processResult(result, request, response);
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        Type type = TypeToken.getParameterized(PaginatedListModel.class, ProductSearchModel.class).getType();
        
        PaginatedListModel<ProductSearchModel> model = GSON.fromJson(result.getResponseMessage(), type);
        
        setModel(model, request);
        
        request.getRequestDispatcher(getJSPPage()).forward(request, response);
    }

    @Override
    protected String getJSPPage() {
        return "/WEB-INF/product/productList.jsp";
    }
}
