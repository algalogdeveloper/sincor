package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.EnderecoRepository;
import br.com.lrt.sincor.service.EnderecoService;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListarEnderecos implements Invoke{

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		EnderecoService es = new EnderecoService(new EnderecoRepository());
		HashMap<Object, Object> model = new HashMap<Object, Object>();
		model.put("enderecos", es.todos());
		model.put("codigo", (es.maximoId()+1));
		request.setAttribute("model", model);
		return "listadeenderecos.jsp";
	}

}
