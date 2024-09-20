package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.FuncionarioRepository;
import br.com.lrt.sincor.service.FuncionarioService;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarFuncionarios implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		FuncionarioService fs = new FuncionarioService(new FuncionarioRepository());
		HashMap<Object, Object> model = new HashMap<Object, Object>();
		model.put("funcionarios", fs.todos());
		model.put("codigo", fs.maximoID()+1);
		request.setAttribute("model", model);
		return "listadefuncionarios.jsp";
	}

}
