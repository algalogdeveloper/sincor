package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.FuncionarioRepository;
import br.com.lrt.sincor.service.FuncionarioService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoverFuncionario implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		String param = request.getParameter("param");
		FuncionarioService fs = new FuncionarioService(new FuncionarioRepository());
		fs.remover(param);
		return "sucesso";
	}

}
