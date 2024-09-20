package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.ClienteRepository;
import br.com.lrt.sincor.repository.EnderecoRepository;
import br.com.lrt.sincor.service.ClienteService;
import br.com.lrt.sincor.service.EnderecoService;
import java.time.LocalDate;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnviarCliente implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		ClienteService cs = new ClienteService(new ClienteRepository());
		EnderecoService es = new EnderecoService(new EnderecoRepository());
		cs.persiste(request.getParameter("cliente"), request.getParameter("ident"), LocalDate.parse(request.getParameter("dtr")), es.obter(Long.valueOf(request.getParameter("endereco")))); 
		HashMap<Object, Object> model = new HashMap<Object, Object>();
		model.put("message", "Dados enviado com sucesso");
		model.put("clientes", cs.listadeClientes());
		model.put("codigo", cs.maximoID()+1);
		model.put("enderecos", es.todos());
		request.setAttribute("model", model);		
		return "listadeeclientes.jsp";
	}

}
