package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.service.Carrinho;
import java.util.LinkedHashSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LimparItemCarrinho implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		try {
			Carrinho carrinho = Carrinho.createCarrinho();
			LinkedHashSet<Object> cart = (LinkedHashSet<Object>) request.getSession().getAttribute("meuCarrinho");
			carrinho.clear(cart);			
		} catch (Exception e) {
			
		}
		return "controller?invoke=CarrinhoItem";
	}

}
