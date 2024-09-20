package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Funcionario;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.CompraRepository;
import br.com.lrt.sincor.service.CompraService;
import java.util.HashMap;
import java.util.LinkedHashSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnviarCompraCliente implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		CompraService cs = new CompraService(new CompraRepository());
		try {
			LinkedHashSet<Object> cart = (LinkedHashSet<Object>) request.getSession().getAttribute("meuCarrinho");
			HashMap<Object, Object> model = (HashMap<Object, Object>) request.getSession().getAttribute("modelUsuario");
			Funcionario funcionario = (Funcionario) model.get("funcionarioAutenticado");
			System.out.println("Carro:"+cart);
			if (permissao.name().equals("USUARIO")) {		
				boolean ok = cs.enviarCompra(cart, request.getParameter("situacao"), request.getParameter("desconto"), request.getParameter("valor"), request.getParameter("qtd"), funcionario, request.getParameter("cliente"), request.getParameter("endereco"));
				if (ok) {
					request.setAttribute("message", "Dados enviado com sucesso");
					cart.clear();
				} else {
					request.setAttribute("erro", "Não foi possível enviar a aposta do cliente!");
				}
				carregarDados(request);
			}else {
				request.setAttribute("erro", "Você não possui permissão para inserir canhotos!");
				cart.clear();
			}
		} catch (Exception e) {
			request.setAttribute("erro", "Carrinho pode está vazio!");
			
			System.out.println("Erro:"+e);
		}
		return "controller?invoke=CarrinhoItem";
	}

	public void carregarDados(HttpServletRequest request) {
		HashMap<Object, Object> model = new HashMap<Object, Object>();
		model.put("endereco", request.getParameter("endereco"));
		model.put("valor", request.getParameter("valor"));
		model.put("qtd", request.getParameter("qtd"));
		request.getSession().setAttribute("modelDados", model);

	}

}
