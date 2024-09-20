package br.com.lrt.sincor.model;

public class Funcionario {
	
	private long id;
	private String nome;
	private Permissao permissao;
	private String login;
	private String senha;
	
	
	
	public Funcionario(long id, String nome, Permissao permissao, String login, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.permissao = permissao;
		this.login = login;
		this.senha = senha;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Permissao getPermissao() {
		return permissao;
	}
	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", permissao=" + permissao + ", login=" + login + ", senha="
				+ senha + "]";
	}
	

}
