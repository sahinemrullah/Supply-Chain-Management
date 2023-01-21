/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.webapp.servlets.cart;

import com.webapp.models.cart.Cart;
import com.webapp.models.cart.CartItem;
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

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/cart/add"})
public class AddToCartServlet extends BaseServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || !IntegerUtils.tryParse(idStr)) {
            response.sendRedirect("/");
        }

        HttpSession session = request.getSession();

        HashMap<String, String> parameters = new HashMap<>();

        parameters.put("id", idStr);

        Result result = RequestBuilder.create()
                .withURL("products/" + idStr)
                .withToken(getToken(session))
                .get(parameters);

        processResult(result, request, response);
    }

    @Override
    protected void onSuccessfullResponse(Result result, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CartItem item = GSON.fromJson(result.getResponseMessage(), CartItem.class);

        Cart cart = getCart(request);

        CartItem existingItem = cart.getItems()
                .stream()
                .filter(itm -> itm.getId() == Integer.parseInt(request.getParameter("id")))
                .findFirst()
                .orElse(null);

        if (existingItem == null) {
            item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            cart.addItem(item);
        }
        
        response.sendRedirect("/sepet");
    }

    @Override
    protected String getJSPPage() {
        return "";
    }

}
