package com.webapp.servlets.product;

import com.webapp.models.product.EditDiscountModel;
import com.webapp.utils.HttpRequestUtils;
import com.webapp.utils.IntegerUtils;
import com.webapp.utils.Response;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditDiscountServlet", urlPatterns = {"/product/editDiscount"})
public class EditDiscountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String discount = request.getParameter("discount");
        
        if(!IntegerUtils.tryParse(id) || !IntegerUtils.tryParse(discount)) {
            response.setStatus(400);
        } else {
            EditDiscountModel model = new EditDiscountModel();
            model.setId(Integer.parseInt(id));
            model.setDiscount(Integer.parseInt(discount));
            String token = (String) request.getSession().getAttribute("token");
            
            Response result = HttpRequestUtils.post("http://localhost:9080/product/editDiscount", model, token);
            
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
