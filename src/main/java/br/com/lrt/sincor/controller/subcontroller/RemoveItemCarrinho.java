package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.service.Carrinho;
import java.util.LinkedHashSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveItemCarrinho implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		try {
			Carrinho carrinho = Carrinho.createCarrinho();
			LinkedHashSet<Object> cart = (LinkedHashSet<Object>) request.getSession().getAttribute("meuCarrinho");
			carrinho.remove(cart, request.getParameter("param"));
		} catch (Exception e) {
			
		}		
		return "controller?invoke=CarrinhoItem";
	}

}
