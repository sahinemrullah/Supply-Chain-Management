package com.webapp.servlets.product;

import com.webapp.models.product.EditStockModel;
import com.webapp.utils.HttpRequestUtils;
import com.webapp.utils.IntegerUtils;
import com.webapp.utils.Result;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditStockServlet", urlPatterns = {"/product/editStock"})
public class EditStockServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String stock = request.getParameter("stock");
        
        if(!IntegerUtils.tryParse(id) || !IntegerUtils.tryParse(stock)) {
            response.setStatus(400);
        } else {
            EditStockModel model = new EditStockModel();
            model.setId(Integer.parseInt(id));
            model.setStock(Integer.parseInt(stock));
            String token = (String) request.getSession().getAttribute("token");
            
            Result result = HttpRequestUtils.post("http://localhost:9080/product/editStock", model, token);
            
            if(result.getStatusCode() == 200) {
                String referer = request.getHeader("Referer");
                response.sendRedirect(referer);
            } else {
                response.setStatus(result.getStatusCode());
                response.getWriter().write(result.getResponseMessage());
            }
        }
            
    }
}
