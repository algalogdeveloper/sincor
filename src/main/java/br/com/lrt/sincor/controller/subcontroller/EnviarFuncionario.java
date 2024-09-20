package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.FuncionarioRepository;
import br.com.lrt.sincor.service.FuncionarioService;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnviarFuncionario implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		FuncionarioService fs = new FuncionarioService(new FuncionarioRepository());
		fs.persiste(request.getParameter("nome"), request.getParameter("permissao"), request.getParameter("login"),
				request.getParameter("senha"));
		HashMap<Object, Object> model = new HashMap<Object, Object>();
		model.put("funcionarios", fs.todos());
		model.put("codigo", fs.maximoID()+1);
		model.put("message", "Dados enviado com sucesso!");
		request.setAttribute("model", model);
		return "listadefuncionarios.jsp";
	}

}
