package br.com.lrt.sincor.controller;

import br.com.lrt.sincor.model.Permissao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Invoke {
	
	public Object invoke(HttpServletRequest request, HttpServletResponse response, Permissao permissao);

}
