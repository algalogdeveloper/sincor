package br.com.lrt.sincor.model;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Compra {
	
	private long id;
	private String descricao;
	private LocalDate dataHoraCompra;
	private LocalDate dataHoraPagamento;
	private double valor;
	private int qtd;
	private Situacao pagamento;
	private double desconto;
	private double valorUN;
	private int qtdNoCanhoto;
	private Funcionario funcionario;
	private Cliente cliente;
	private LinkedHashSet<Itens> itens;
	
	public String toString() {
		return itens.toString()
				.replace("[", "")
				.replace("]", "");
	}

}
