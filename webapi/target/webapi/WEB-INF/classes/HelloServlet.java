import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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