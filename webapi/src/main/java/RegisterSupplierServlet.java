

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.abstractions.IValidation;
import com.webapi.application.models.RegisterModel;
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

@WebServlet("/supplier/register")
public class RegisterSupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new GsonBuilder().create();
	private ISupplierRepository supplierRepository;
	
    public RegisterSupplierServlet() {
    	supplierRepository = new SupplierRepository();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RegisterModel model = GSON.fromJson(HttpServletRequestUtils.getBody(request), RegisterModel.class);
		 
		 response.setContentType(HttpServletUtils.MEDIA_TYPE_JSON);
		 
		 IValidation validationResult = model.validate();
		 
		 if(validationResult.isSucceeded()) {
			 if(!supplierRepository.supplierExists(model.getEmail()))
			 {
				 Supplier supplier = new Supplier();
				 supplier.setEmail(model.getEmail());
				 supplier.setName(model.getName());
				 supplier.setPhoneNumber(model.getPhoneNumber());
				 supplier.setPasswordHash(EncryptionUtils.HashString(model.getPassword()));
				 if(supplierRepository.add(supplier))
				 	response.setStatus(200);
				 else
				 	response.setStatus(500);
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
