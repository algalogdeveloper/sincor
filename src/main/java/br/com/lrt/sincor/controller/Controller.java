package br.com.lrt.sincor.controller;

import br.com.lrt.sincor.model.Funcionario;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
	}

        @Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			@SuppressWarnings("unchecked")
			HashMap<Object, Object> model = (HashMap<Object, Object>) request.getSession().getAttribute("modelUsuario");
			if (Objects.nonNull(model)) {
				Funcionario funcionario = (Funcionario) model.get("funcionarioAutenticado");
				System.out.println("Logado: "+funcionario); 
				Class<?> invoke = Class
						.forName("br.com.lrt.sincor.controller.subcontroller." + request.getParameter("invoke"));
				Invoke mvc = (Invoke) invoke.newInstance();
				Object object = mvc.invoke(request, response, funcionario.getPermissao());
				RequestDispatcher dispatcher = request.getRequestDispatcher((String) object);
				dispatcher.forward(request, response);
			}else {				
				response.sendRedirect(getServletContext().getContextPath()+"/index.jsp");
			}
		} catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | ServletException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
