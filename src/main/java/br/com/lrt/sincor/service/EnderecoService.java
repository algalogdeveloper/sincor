package br.com.lrt.sincor.service;

import br.com.lrt.sincor.model.Endereco;
import br.com.lrt.sincor.model.Rota;
import br.com.lrt.sincor.repository.EnderecoRepository;
import java.util.LinkedHashSet;
import java.util.Objects;

public class EnderecoService {

	private EnderecoRepository er;

	public EnderecoService(EnderecoRepository er) {
		this.er = er;
	}

	public Endereco obter(long id) {
		return er.obter(id);
	}
	
	public Endereco obter2(long id) {
		return er.obter2(id);
	}

	public void persiste(long id, String endereco, Rota r) {
		if (Objects.isNull(obter(id)))
			er.persiste(new Endereco(id, endereco, r));

	}

	public LinkedHashSet<Endereco> todos() {
		return er.todos();
	}

	public long maximoId() {
		return er.maximoId();
	}
	
	public LinkedHashSet<Endereco> todosEnderecosComClientes() {
		return er.todosEnderecosComClientes();
	}

}
