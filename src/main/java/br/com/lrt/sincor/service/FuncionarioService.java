package br.com.lrt.sincor.service;

import br.com.lrt.sincor.model.Funcionario;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.FuncionarioRepository;
import java.util.LinkedHashSet;
import java.util.Objects;

public class FuncionarioService {

	private FuncionarioRepository fr;

	public void persiste(String nome, String permissao, String login, String senha) {
		Funcionario f = new Funcionario((maximoID() + 1), nome, Permissao.valueOf(permissao), login, senha);
		System.out.println(f);
		if(Objects.isNull(permitirAcesso(login, senha)))
		fr.persiste(f);
	}
	
	
	public LinkedHashSet<Funcionario> todos () {
		return fr.todos();
	}

	public FuncionarioService(FuncionarioRepository fr) {
		super();
		this.fr = fr;
	}

	public boolean autorizarFuncionario(String login, String senha) {
		return fr.autorizarFuncionario(login, senha);
	}

	public Funcionario permitirAcesso(String login, String senha) {
		return fr.permitirAcesso(login, senha);
	}

	public long maximoID() {
		return fr.maximoId();
	}


	public Funcionario obter(long id) {
		// TODO Auto-generated method stub
		return fr.obter(id);
	}


	public void remover(String param) {
		fr.remover(Long.valueOf(param));
		
	}

}
