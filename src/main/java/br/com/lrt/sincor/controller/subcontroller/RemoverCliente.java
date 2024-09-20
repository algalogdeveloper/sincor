package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.ClienteRepository;
import br.com.lrt.sincor.service.ClienteService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoverCliente implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		ClienteService cs = new ClienteService(new ClienteRepository());
		return cs.remover(request.getParameter("param"));
	}

}
