package com.webapp.servlets.product;

import com.webapp.models.product.ProductDetailsModel;
import com.webapp.servlets.BaseServlet;
import com.webapp.utils.HttpRequestUtils;
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

@WebServlet(name = "ProductDetailsServlet", urlPatterns = {"/urun/detay"})
public class ProductDetailsServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || !IntegerUtils.tryParse(idStr)) {
            response.sendRedirect("/");
        }

        HttpSession session = request.getSession();

        HashMap<String, String> parameters = new HashMap<>();

        Result result = RequestBuilder.create()
                .withURL("products/" + idStr)
                .withToken(getToken(session))
                .get(parameters);

        processResult(result, request, response);
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ProductDetailsModel model = GSON.fromJson(result.getResponseMessage(), ProductDetailsModel.class);
        setModel(model, request);

        request.getRequestDispatcher(getJSPPage()).forward(request, response);
    }

    @Override
    protected String getJSPPage() {
        return "/WEB-INF/product/productDetails.jsp";
    }
}
