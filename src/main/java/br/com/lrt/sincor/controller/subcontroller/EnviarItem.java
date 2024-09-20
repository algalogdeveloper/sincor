package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.MilharRepository;
import br.com.lrt.sincor.service.Carrinho;
import br.com.lrt.sincor.service.MilharService;
import java.util.LinkedHashSet;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EnviarItem implements Invoke {

	protected Carrinho carrinho;
	protected HttpSession sessionCart = null;

	public EnviarItem() {
		carrinho = Carrinho.createCarrinho();
	}

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		try {
			LinkedHashSet<Object> cart = (LinkedHashSet<Object>) request.getSession().getAttribute("meuCarrinho");
			MilharService ms = new MilharService(new MilharRepository());
			if (Objects.isNull(cart)) {
				sessionCart = request.getSession(true);
			}
			String milhar = request.getParameter("numero");
			if (ms.validarMilhar(milhar))
				cart = carrinho.carregarCarregarCarrinho(cart, milhar);
			else
				request.setAttribute("erro", "Milhar não está disponível!");
			sessionCart.setAttribute("meuCarrinho", cart);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "controller?invoke=CarrinhoItem";
	}

}
