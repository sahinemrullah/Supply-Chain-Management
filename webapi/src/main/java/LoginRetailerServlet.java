

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapi.application.models.LoginModel;
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

@WebServlet("/retailer/login")
public class LoginRetailerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new GsonBuilder().create();
	private IRetailerRepository retailerRepository;
	
    public LoginRetailerServlet() {
    	retailerRepository = new RetailerRepository();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 LoginModel model = GSON.fromJson(HttpServletRequestUtils.getBody(request), LoginModel.class);
		 
		 response.setContentType(HttpServletUtils.MEDIA_TYPE_PLAIN_TEXT);

		 Retailer retailer = retailerRepository.getRetailer(model.getEmail());
		 
		 if(retailer != null && EncryptionUtils.checkHash(model.getPassword(), retailer.getPasswordHash())) {
			 String jwt = EncryptionUtils.createJWT(String.valueOf(retailer.getId()), "localhost:8080", "localhost:8080", 10 * 60 * 1000);

			 response.setStatus(200);
			 response.getWriter().write(jwt);
		 } else {
			 response.setStatus(400);
		 }
	}

}
