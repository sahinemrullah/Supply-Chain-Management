package com.webapi.application.servlets.supplier;



import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.models.LoginModel;
import com.webapi.application.utils.EncryptionUtils;
import com.webapi.application.utils.HttpServletRequestUtils;
import com.webapi.application.utils.HttpServletUtils;
import com.webapi.domain.entities.Supplier;
import com.webapi.persistence.abstractions.ISupplierRepository;
import com.webapi.persistence.concretes.SupplierRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/supplier/login")
public class LoginSupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new GsonBuilder().create();
	private ISupplierRepository supplierRepository;
	
    public LoginSupplierServlet() {
    	supplierRepository = new SupplierRepository();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 LoginModel model = GSON.fromJson(HttpServletRequestUtils.getBody(request), LoginModel.class);
		 
		 response.setContentType(HttpServletUtils.MEDIA_TYPE_PLAIN_TEXT);

		 Supplier supplier = supplierRepository.getSupplier(model.getEmail());
		 
		 if(supplier != null && EncryptionUtils.checkHash(model.getPassword(), supplier.getPasswordHash())) {
			 String jwt = EncryptionUtils.createJWT(String.valueOf(supplier.getId()), false, "localhost:8080", "localhost:8080");

			 response.setStatus(200);
			 response.getWriter().write(jwt);
		 } else {
			 response.setStatus(400);
		 }
	}

}
