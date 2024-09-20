	package br.com.lrt.sincor.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Itens {
	private long id;
	private Compra compra;
	private LocalDate dataItem;
	private Milhar milhar;
	
	public String toString() {
		return milhar.getNumero();
	}
	
  
}
