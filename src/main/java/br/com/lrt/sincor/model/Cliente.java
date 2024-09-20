package br.com.lrt.sincor.model;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import lombok.Data;

@Data
public class Cliente {
	private long id;
	private String cliente;
	private String identificador;
	private LocalDate dataRegistro;
	private Endereco endereco;
	private LinkedHashSet<Compra> compras;
	
}
