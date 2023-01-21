package com.webapp.servlets.order;

import com.webapp.models.order.ViewOrderModel;
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

@WebServlet(name = "ViewOrderServlet", urlPatterns = {"/siparis/goruntule"})
public class ViewOrderServlet extends BaseServlet {

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
                .withURL("orders/" + idStr)
                .withToken(getToken(session))
                .get(parameters);
        
        processResult(result, request, response);
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ViewOrderModel model = GSON.fromJson(result.getResponseMessage(), ViewOrderModel.class);
        setModel(model, request);

        HttpSession session = request.getSession();

        String role = (String) session.getAttribute("role");
        request.setAttribute("role", role);
        request.setAttribute("id", request.getParameter("id"));

        request.getRequestDispatcher(getJSPPage()).forward(request, response);
    }

    @Override
    protected String getJSPPage() {
        return "/WEB-INF/order/details.jsp";
    }
}
