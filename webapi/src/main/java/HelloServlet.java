import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;
   private static final Gson GSON = new GsonBuilder().create();

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
         throws ServletException, IOException {
      resp.setContentType("application/json");
      resp.getWriter().write(GSON.toJson(5));
   }
}