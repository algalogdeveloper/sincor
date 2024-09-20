package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.model.Rota;
import br.com.lrt.sincor.repository.EnderecoRepository;
import br.com.lrt.sincor.service.EnderecoService;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnviarEndereco implements Invoke{

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		EnderecoService es = new EnderecoService(new EnderecoRepository());
		HashMap<Object, Object> model = new HashMap<Object, Object>();
		String message = "Dados enviados com sucesso!";
		es.persiste(Integer.parseInt(request.getParameter("id")),
				request.getParameter("nome"),
				Rota.valueOf(request.getParameter("rota")));
		model.put("message", message);
		model.put("codigo", (es.maximoId()+1));
		model.put("enderecos", es.todos());
		request.setAttribute("model", model);
		
		return "listadeenderecos.jsp";
	}

}
