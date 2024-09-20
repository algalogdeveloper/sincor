package br.com.lrt.sincor.service;

import br.com.lrt.sincor.model.Milhar;
import br.com.lrt.sincor.repository.MilharRepository;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class MilharService {

	private MilharRepository mr;
	
	public Milhar obter(long param){
		return mr.obter(param);
	}
	
	public Milhar obter(String param){
		return mr.obter(param);
	}

	public void save(Milhar m) {
		mr.persiste(m); 
		
	}
	
	public boolean validarMilhar(String param) {
		return mr.validarMilhar(param);
	}
}
