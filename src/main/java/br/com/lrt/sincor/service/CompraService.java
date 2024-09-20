package br.com.lrt.sincor.service;

import br.com.lrt.sincor.model.Cliente;
import br.com.lrt.sincor.model.Compra;
import br.com.lrt.sincor.model.Funcionario;
import br.com.lrt.sincor.model.Itens;
import br.com.lrt.sincor.model.Situacao;
import br.com.lrt.sincor.repository.ClienteRepository;
import br.com.lrt.sincor.repository.CompraRepository;
import br.com.lrt.sincor.repository.EnderecoRepository;
import br.com.lrt.sincor.repository.ItemRepository;
import br.com.lrt.sincor.repository.MilharRepository;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompraService {

    private CompraRepository cr;

    public boolean enviarCompra(LinkedHashSet<Object> todos, String situacao, String desconto, String valor, String qtd,
            Funcionario funcionario, String cliente, String endereco) {
        MilharService ms = new MilharService(new MilharRepository());
        ClienteService cs = new ClienteService(new ClienteRepository());
        EnderecoService es = new EnderecoService(new EnderecoRepository());
        ItemService is = new ItemService(new ItemRepository());
        boolean execute = false;
        // enviando e validando cliente
        Cliente c = cs.persiste(cliente,
                todos.stream().findFirst().get().toString(),
                LocalDate.now(),
                es.obter(Long.parseLong(endereco)));
        if (!notEmptyCarrinho(todos) && validarQTD(todos.size(), Integer.parseInt(qtd)) > 0 && Objects.nonNull(c)) {
            Compra compra = new Compra();
            compra.setId((cr.maximoId() + 1));
            compra.setDescricao("Dados enviado com sucesso.");
            compra.setCliente(c);
            compra.setFuncionario(funcionario);
            compra.setDataHoraCompra(LocalDate.now());
            compra.setDesconto(Double.valueOf(desconto));
            compra.setValorUN(Double.parseDouble(valor));
            compra.setQtdNoCanhoto(Integer.parseInt(qtd));
            compra.setQtd(validarQTD(todos.size(), compra.getQtdNoCanhoto()));
            compra.setValor(calcularValor(compra.getValorUN(), compra.getQtd(), compra.getDesconto()));
            compra.setPagamento(Situacao.valueOf(situacao));
            compra.setDataHoraPagamento(null);
            compra = cr.persiste(compra);
            LinkedHashSet<Itens> itens = new LinkedHashSet<Itens>();
            for (Object object : todos) {
                Itens item = new Itens();
                item.setMilhar(ms.obter(String.valueOf(object)));
                item.setCompra(compra);
                itens.add(item);
            }
            compra.setItens(itens);
            // enviando compra
            if (Objects.nonNull(compra)) {
                for (Itens object : compra.getItens()) {
                    is.save(object);
                }
            }
            execute = true;
        }
        System.out.println("Processo executado:" + execute);
        return execute;
    }

    public int validarQTD(int todos, int qtd) {
        System.out.println("QTD:" + qtd);
        System.out.println("Size:" + todos);
        return todos >= qtd ? todos / qtd : -1;
    }

    public double calcularValor(double valorUN, int qtd, double desconto) {
        return (valorUN * qtd) - desconto;
    }

    public LinkedHashSet<Compra> todas() {
        return cr.todas();
    }

    public LinkedHashSet<Compra> buscarCompraClientePorID(String param) {
        return cr.buscarCompraClientePorID(Long.valueOf(param));
    }

    public LinkedHashSet<Compra> buscarCompraCliente(long param) {
        return cr.buscarCompraCliente(param);
    }

    public boolean notEmptyCarrinho(LinkedHashSet<Object> elementos) {
        return elementos.isEmpty();
    }

    public String removendoCompraDoCliente(long id) {
        String message = "erro";
        ItemService is = new ItemService(new ItemRepository());
        ClienteService cs = new ClienteService(new ClienteRepository());
        Compra c = cr.obter(id);
        System.out.println(c);
        if (Objects.nonNull(c)) {
            LinkedHashSet<Itens> itens = is.listaItensPorCompra(c.getId());
            for (Itens iten : itens) {
                is.remover(iten.getId());
            }

            cr.remover(id);
            cs.remover(String.valueOf(c.getCliente().getId()));
            message = "sucesso";
        }

        return message;
    }
}
