package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.CompraRepository;
import br.com.lrt.sincor.service.CompraService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuscarCompraDoCliente implements Invoke {
 
	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		CompraService cs = new CompraService(new CompraRepository());
		return cs.buscarCompraClientePorID(request.getParameter("param")).toArray();
	}

}
