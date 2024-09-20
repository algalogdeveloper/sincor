package br.com.lrt.sincor.service;

import br.com.lrt.sincor.model.Itens;
import br.com.lrt.sincor.model.Milhar;
import br.com.lrt.sincor.repository.ItemRepository;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class ItemService {
	
	private ItemRepository ir;
	
	public void remover(long id) {
		ir.remover(id);
	}
	
	public void save(Itens itens) {
		itens.setId((ir.maximoId()+1));
		itens.setDataItem(LocalDate.now());		
		ir.persiste(itens);
	}
	
	public LinkedHashSet<Itens> listaItensPorCompra(long param){
		return ir.listaItensPorCompra(param);
	}
	
	public void update(Milhar milhar, String param) {
		Itens item = new  Itens();
		item.setId(Long.parseLong(param));
		item.setMilhar(milhar);
		ir.update(item);
	}

}
