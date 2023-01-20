package com.webapp.servlets.product;

import com.webapp.models.product.EditDiscountModel;
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

@WebServlet(name = "EditDiscountServlet", urlPatterns = {"/product/edit-discount"})
public class EditDiscountServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String discount = request.getParameter("discount");

        if (!IntegerUtils.tryParse(id) || !IntegerUtils.tryParse(discount)) {
            response.setStatus(400);
        } else {
            EditDiscountModel model = new EditDiscountModel();
            model.setId(Integer.parseInt(id));
            model.setDiscount(Integer.parseInt(discount));
            
            HttpSession session = request.getSession();

            StringBuilder builder = new StringBuilder();

            builder.append("suppliers/")
                    .append(getUserId(session))
                    .append("/products/")
                    .append(id)
                    .append("/edit-discount");

            Result result = RequestBuilder.create()
                    .withURL(builder.toString())
                    .withToken(getToken(session))
                    .post(model);
            
            processResult(result, request, response);
        }
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    @Override
    protected String getJSPPage() {
        return "";
    }
}
