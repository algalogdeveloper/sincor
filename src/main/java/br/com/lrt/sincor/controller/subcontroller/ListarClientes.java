package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.ClienteRepository;
import br.com.lrt.sincor.repository.EnderecoRepository;
import br.com.lrt.sincor.service.ClienteService;
import br.com.lrt.sincor.service.EnderecoService;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarClientes implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		ClienteService cs = new ClienteService(new ClienteRepository());
		EnderecoService es = new EnderecoService(new EnderecoRepository());
		HashMap<Object, Object> model = new HashMap<Object, Object>();
		model.put("clientes", cs.listadeClientes());
		model.put("codigo", cs.maximoID()+1);
		model.put("enderecos", es.todos());
		request.setAttribute("model", model);		
		return "listadeeclientes.jsp";
	}

}
