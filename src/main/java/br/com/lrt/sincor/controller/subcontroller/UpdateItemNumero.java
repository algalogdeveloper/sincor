package br.com.lrt.sincor.controller.subcontroller;

import br.com.lrt.sincor.controller.Invoke;
import br.com.lrt.sincor.model.Permissao;
import br.com.lrt.sincor.repository.ItemRepository;
import br.com.lrt.sincor.repository.MilharRepository;
import br.com.lrt.sincor.service.ItemService;
import br.com.lrt.sincor.service.MilharService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateItemNumero implements Invoke {

	@Override
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao) {
		ItemService itemService = new ItemService(new ItemRepository());
		MilharService ms = new MilharService(new MilharRepository());
		String numero = request.getParameter("param");
		String numero2 = request.getParameter("param2");
		boolean validar = ms.validarMilhar(numero);
		String message="erro";
		if(validar) {
			itemService.update(ms.obter(numero),numero2);
			message = "sucesso";
		}
		System.out.println("Update: " + numero+ " Validou: "+message);
		return message;
	}

}
