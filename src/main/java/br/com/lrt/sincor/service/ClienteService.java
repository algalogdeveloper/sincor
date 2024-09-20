package br.com.lrt.sincor.service;

import br.com.lrt.sincor.model.Cliente;
import br.com.lrt.sincor.model.Endereco;
import br.com.lrt.sincor.repository.ClienteRepository;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;

public class ClienteService {

    private ClienteRepository cr;

    public ClienteService(ClienteRepository cr) {
        super();
        this.cr = cr;
    }

    public String remover(String id) {
        cr.remover(Long.parseLong(id));
        return "sucesso";
    }

    public Cliente persiste(String cliente, String ident, LocalDate dtr, Endereco e) {
        Cliente c = obter(ident);
        if (Objects.isNull(obter(ident))) {
            c = new Cliente();
            c.setId((cr.maximoId() + 1));
            c.setCliente(cliente);
            c.setIdentificador(ident);
            c.setDataRegistro(dtr);
            c.setEndereco(e);
            c = cr.persiste(c);
        } else {
            c.setId(c.getId());
            c.setCliente(cliente);
            c.setIdentificador(ident);
            c.setDataRegistro(dtr);
            c.setEndereco(e);
            c = cr.update(c);
        }
        return c;
    }

    public long maximoID() {
        return cr.maximoId();
    }

    public LinkedHashSet<Cliente> listadeClientesPorEndereco(long id) {

        return cr.listadeClientesPorEndereco(id);
    }

    public LinkedHashSet<Cliente> listadeClientes() {
        return cr.listadeClientes();
    }

    public Cliente obter(String id) {
        return cr.obter(id);
    }

    public Cliente obter(long id) {
        return cr.obter(id);
    }

}
