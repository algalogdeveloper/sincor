package br.com.lrt.sincor.controller;

import br.com.lrt.sincor.model.Permissao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/sinfar")
public class JsonController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String entity = "br.com.lrt.sincor.controller.subcontroller." + request.getParameter("invoke");
			Class<?> instance = Class.forName(entity);
			@SuppressWarnings("deprecation")
			Invoke invoke = (Invoke) instance.newInstance();
			Object mvc = invoke.invoke(request, response,Permissao.USUARIO);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Gson gson = new
					GsonBuilder()
					.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
					.create();					
			response.getWriter().print(gson.toJson(mvc));
		} catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			System.out.println("Erro:" + e);
		}	

	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String entity = "br.com.lrt.sincor.controller.subcontroller." + request.getParameter("invoke");
			Class<?> instance = Class.forName(entity);
			Invoke invoke = (Invoke) instance.newInstance();
			Object mvc = invoke.invoke(request, response,Permissao.USUARIO);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Gson gson = new
					GsonBuilder()
					.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
					.create();					
			response.getWriter().print(gson.toJson(mvc));
		} catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
			System.out.println("Erro:" + e); 
		}	

	}
	
}
class LocalDateTypeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate>{

	 private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	  @Override
	  public JsonElement serialize(final LocalDate date, final Type typeOfSrc,
	      final JsonSerializationContext context) {
	    return new JsonPrimitive(date.format(formatter));
	  }

	  @Override
	  public LocalDate deserialize(final JsonElement json, final Type typeOfT,
	      final JsonDeserializationContext context) throws JsonParseException {
	    return LocalDate.parse(json.getAsString(), formatter);
	  }
	
}


