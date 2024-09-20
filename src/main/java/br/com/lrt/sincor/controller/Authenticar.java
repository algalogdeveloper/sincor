package br.com.lrt.sincor.controller;

import br.com.lrt.sincor.model.Funcionario;
import br.com.lrt.sincor.repository.FuncionarioRepository;
import br.com.lrt.sincor.service.FuncionarioService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(urlPatterns = "/logar")
public class Authenticar extends HttpServlet {
	private static final long serialVersionUID = 1L;
        @Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FuncionarioService fs = new FuncionarioService(new FuncionarioRepository());
		HashMap<Object, Object> model = new HashMap<Object, Object>();
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		boolean autenticado = fs.autorizarFuncionario(login, senha);
		System.out.println("Autenticado: " + autenticado);
		if (autenticado) {
			HttpSession session = request.getSession(autenticado);
			Funcionario autenticarFuncionario = fs.permitirAcesso(login, senha);
			if (Objects.nonNull(autenticarFuncionario)) {
				model.put("funcionarioAutenticado", autenticarFuncionario);
				model.put("dataEHoraDeAcesso", LocalDateTime.now());
				session.setAttribute("modelUsuario", model);
				response.sendRedirect("controller?invoke=Home");
			}

		} else {
			model.put("message", "Login ou senha invalidos!");
			request.setAttribute("model", model);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}

	}

}
