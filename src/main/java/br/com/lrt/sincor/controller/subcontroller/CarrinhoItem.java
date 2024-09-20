package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.EnderecoRepository;
import br.com.lrt.sincor.service.EnderecoService;
import java.util.LinkedHashSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarrinhoItem implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		LinkedHashSet<Object> cart = (LinkedHashSet<Object>) request.getSession().getAttribute("meuCarrinho");
		EnderecoService es = new EnderecoService(new EnderecoRepository());
		request.setAttribute("meuCarrinho", cart);
		request.setAttribute("enderecos", es.todos());
		return "carrinho.jsp";
	}

}
