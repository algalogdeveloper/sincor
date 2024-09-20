package br.com.lrt.sincor.model;

import java.util.LinkedHashSet;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Endereco {
	
	private long id;
	private String nome;
	private Rota rota;
	private LinkedHashSet<Cliente> clientes;
	
	public Endereco(long id, String nome, Rota rota) {
		super();
		this.id = id;
		this.nome = nome;
		this.rota = rota;
	}

	
}
