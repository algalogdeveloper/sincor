package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.CompraRepository;
import br.com.lrt.sincor.service.CompraService;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarCompras implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		CompraService cs = new CompraService(new CompraRepository());
		HashMap<Object, Object> model = new HashMap<Object, Object>();
		model.put("compras", cs.todas());
		request.setAttribute("model", model);
		return "listadecompras.jsp";
	}

}
