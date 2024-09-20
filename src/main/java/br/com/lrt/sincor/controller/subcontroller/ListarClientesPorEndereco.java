package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.EnderecoRepository;
import br.com.lrt.sincor.service.EnderecoService;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarClientesPorEndereco implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		EnderecoService es = new EnderecoService(new EnderecoRepository());
		HashMap<Object, Object> model = new HashMap<Object, Object>();
		try {
           String endereco = request.getParameter("endereco");
           model.put("clientes", es.obter(Long.valueOf(endereco)).getClientes());
		} catch (Exception e) {}
		model.put("enderecos", es.todosEnderecosComClientes());
		request.setAttribute("model", model);
		return "buscarclientesporendereco.jsp";
	}

}
