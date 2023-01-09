package com.webapi.application.servlets.retailer;



import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.abstractions.IValidation;
import com.webapi.application.models.RegisterModel;
import com.webapi.application.utils.EncryptionUtils;
import com.webapi.application.utils.HttpServletRequestUtils;
import com.webapi.application.utils.HttpServletUtils;
import com.webapi.domain.entities.Retailer;
import com.webapi.persistence.abstractions.IRetailerRepository;
import com.webapi.persistence.concretes.RetailerRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.webapi.application.abstractions.IValidator;
import com.webapi.application.validators.RegisterModelValidator;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/retailer/register")
public class RegisterRetailerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new GsonBuilder().create();
	private IRetailerRepository retailerRepository;
	
    public RegisterRetailerServlet() {
    	retailerRepository = new RetailerRepository();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RegisterModel model = GSON.fromJson(HttpServletRequestUtils.getBody(request), RegisterModel.class);
		 
		 response.setContentType(HttpServletUtils.MEDIA_TYPE_JSON);
		 
                 IValidator validator = new RegisterModelValidator(model);
                 
		 IValidation validationResult = validator.validate();
		 
		 if(validationResult.isSucceeded()) {
			 if(!retailerRepository.retailerExists(model.getEmail()))
			 {
				 Retailer retailer = new Retailer();
				 retailer.setEmail(model.getEmail());
				 retailer.setName(model.getName());
				 retailer.setPhoneNumber(model.getPhoneNumber());
				 retailer.setPasswordHash(EncryptionUtils.hashString(model.getPassword()));
                             try {
                                 if(retailerRepository.add(retailer))
                                     response.setStatus(200);
                             } catch (SQLException ex) {
                                 response.setStatus(500);
                             }
			 } else {
				 validationResult.addError("email", "Bu email adresi ile ilişkili bir tedarikçi bulunmaktadır.");
			 }
		 }
		 
		 if(!validationResult.isSucceeded()) {
			response.setStatus(400);
			response.getWriter().write(GSON.toJson(validationResult.getValidationErrors()));
		 }
	}

}
