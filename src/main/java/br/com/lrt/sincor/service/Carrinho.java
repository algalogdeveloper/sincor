package br.com.lrt.sincor.service;

import br.com.lrt.sincor.repository.MilharRepository;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Carrinho {
	
	protected MilharService ms;

	private Carrinho() {
		ms = new MilharService(new MilharRepository());
	}

	private static Carrinho carrinho;

	public static Carrinho createCarrinho() {
		if (Objects.isNull(carrinho)) {
			carrinho = new Carrinho();
		}
		
		return carrinho;
	}

	public LinkedHashSet<Object> carregarCarregarCarrinho(LinkedHashSet<Object> todos, String numero) {
		if (Objects.isNull(todos)) {
			todos = new LinkedHashSet<Object>();
		}
		if (ms.validarMilhar(numero)) {
			todos.add(numero);
		}
		
		return todos;
	}

	public void clear(LinkedHashSet<Object> todos) {
		todos.clear();
	}

	public void remove(LinkedHashSet<Object> todos, Object o) {
		for (Object object : todos) {
			if (object.equals(o)) {
				todos.remove(object);
			}
		}
	}

}
