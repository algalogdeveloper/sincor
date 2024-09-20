package br.com.lrt.sincor.repository;

import br.com.lrt.sincor.datasource.DataSource;
import br.com.lrt.sincor.model.Compra;
import br.com.lrt.sincor.model.Situacao;
import br.com.lrt.sincor.service.ClienteService;
import br.com.lrt.sincor.service.FuncionarioService;
import br.com.lrt.sincor.service.ItemService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

public class CompraRepository {

	private DataSource dataSource;

	public Compra persiste(Compra c) {

		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection conn = dataSource.executeTransaction();
			PreparedStatement ps = conn.prepareStatement("insert into compras values (?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setObject(1, c.getId());
			ps.setObject(2, c.getDataHoraCompra());
			ps.setObject(3, c.getDataHoraPagamento());
			ps.setObject(4, c.getCliente().getId());
			ps.setObject(5, c.getFuncionario().getId());
			ps.setObject(6, c.getValor());
			ps.setObject(7, c.getValorUN());
			ps.setObject(8, c.getDesconto());
			ps.setObject(9, c.getQtd());
			ps.setObject(10, c.getQtdNoCanhoto());
			ps.setObject(11, c.getPagamento().name());
			ps.setObject(12, c.getDescricao());
			System.out.println("Insert: " + ps.toString());
			ps.execute();
			ps.close();
			dataSource.fecharConexao();
			return c;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

	}

	public long maximoId() {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			ResultSet rs = connection.prepareStatement("select max(id) as maximoID from compras").executeQuery();
			rs.next();
			long maximo = rs.getLong("maximoID");
			rs.close();
			dataSource.fecharConexao();
			return maximo;
		} catch (Exception e) {
			return 0;
		}
	}

	public void remover(long id) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement statement = connection.prepareStatement("delete from compras where id = ?");
			statement.setObject(1, id);
			statement.execute();
			statement.close();
			dataSource.fecharConexao();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public LinkedHashSet<Compra> todas() {

		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection conn = dataSource.executeTransaction();
			PreparedStatement ps = conn.prepareStatement("select * from compras c ");
			ResultSet rs = ps.executeQuery();
			LinkedHashSet<Compra> compras = new LinkedHashSet<Compra>();
			ClienteService cs = new ClienteService(new ClienteRepository());
			FuncionarioService fs = new FuncionarioService(new FuncionarioRepository());
			ItemService it = new ItemService(new ItemRepository());
			while (rs.next()) {
				Compra c = new Compra();
				c.setId(rs.getLong("id"));
				c.setDescricao(rs.getString("descricao"));
				c.setDataHoraCompra(rs.getDate("datahoracompra").toLocalDate());
				c.setValor(rs.getDouble("valor"));
				c.setValorUN(rs.getDouble("valorun"));
				c.setPagamento(Situacao.valueOf(rs.getString("situacao")));
				c.setDesconto(rs.getDouble("desconto"));
				c.setQtdNoCanhoto(rs.getInt("qtdnocanhoto"));
				c.setQtd(rs.getInt("qtd"));
				c.setCliente(cs.obter(rs.getLong("id_cliente")));
				c.setFuncionario(fs.obter(rs.getLong("id_funcionario")));
				c.setItens(it.listaItensPorCompra(rs.getLong("id")));
				compras.add(c);
			}

			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return compras;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}

	}

	public LinkedHashSet<Compra> buscarCompraClientePorID(long param) {

		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection conn = dataSource.executeTransaction();
			PreparedStatement ps = conn.prepareStatement("select * from compras c where id_cliente = ?");
			ps.setObject(1, param);
			System.out.println("Query01:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			LinkedHashSet<Compra> compras = new LinkedHashSet<Compra>();
			ClienteService cs = new ClienteService(new ClienteRepository());
			FuncionarioService fs = new FuncionarioService(new FuncionarioRepository());
			ItemService it = new ItemService(new ItemRepository());
			while (rs.next()) {
				Compra c = new Compra();
				c.setId(rs.getLong("id"));
				c.setDescricao(rs.getString("descricao"));
				c.setDataHoraCompra(rs.getDate("datahoracompra").toLocalDate());
				c.setValor(rs.getDouble("valor"));
				c.setValorUN(rs.getDouble("valorun"));
				c.setPagamento(Situacao.valueOf(rs.getString("situacao")));
				c.setDesconto(rs.getDouble("desconto"));
				c.setQtdNoCanhoto(rs.getInt("qtdnocanhoto"));
				c.setQtd(rs.getInt("qtd"));
				c.setCliente(cs.obter(rs.getLong("id_cliente")));
				c.setFuncionario(fs.obter(rs.getLong("id_funcionario")));
				c.setItens(it.listaItensPorCompra(rs.getLong("id")));
				compras.add(c);
			}

			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return compras;
		} catch (Exception e) {
			System.out.println(e);
			throw new IllegalArgumentException(e);
		}

	}
	
	
	
	public Compra obter(long param) {

		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection conn = dataSource.executeTransaction();
			PreparedStatement ps = conn.prepareStatement("select * from compras c where id = ?");
			ps.setObject(1, param);
			System.out.println("Query01:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			ClienteService cs = new ClienteService(new ClienteRepository());
			FuncionarioService fs = new FuncionarioService(new FuncionarioRepository());
			ItemService it = new ItemService(new ItemRepository());
			Compra c = null;
			while (rs.next()) {
			    c = new Compra();
				c.setId(rs.getLong("id"));
				c.setDescricao(rs.getString("descricao"));
				c.setDataHoraCompra(rs.getDate("datahoracompra").toLocalDate());
				c.setValor(rs.getDouble("valor"));
				c.setValorUN(rs.getDouble("valorun"));
				c.setPagamento(Situacao.valueOf(rs.getString("situacao")));
				c.setDesconto(rs.getDouble("desconto"));
				c.setQtdNoCanhoto(rs.getInt("qtdnocanhoto"));
				c.setQtd(rs.getInt("qtd"));
				c.setCliente(cs.obter(rs.getLong("id_cliente")));
				c.setFuncionario(fs.obter(rs.getLong("id_funcionario")));
				c.setItens(it.listaItensPorCompra(rs.getLong("id")));
		
			}

			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return c;
		} catch (Exception e) {
			System.out.println(e);
			throw new IllegalArgumentException(e);
		}

	}
	

	public LinkedHashSet<Compra> buscarCompraCliente(long param) {

		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection conn = dataSource.executeTransaction();
			PreparedStatement ps = conn.prepareStatement("select * from compras c where id_cliente = ?");
			ps.setObject(1, param);
			System.out.println("Query01:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			LinkedHashSet<Compra> compras = new LinkedHashSet<Compra>();
			ClienteService cs = new ClienteService(new ClienteRepository());
			FuncionarioService fs = new FuncionarioService(new FuncionarioRepository());
			ItemService it = new ItemService(new ItemRepository());
			while (rs.next()) {
				Compra c = new Compra();
				c.setId(rs.getLong("id"));
				c.setDescricao(rs.getString("descricao"));
				c.setDataHoraCompra(rs.getDate("datahoracompra").toLocalDate());
				c.setValor(rs.getDouble("valor"));
				c.setValorUN(rs.getDouble("valorun"));
				c.setPagamento(Situacao.valueOf(rs.getString("situacao")));
				c.setDesconto(rs.getDouble("desconto"));
				c.setQtdNoCanhoto(rs.getInt("qtdnocanhoto"));
				c.setQtd(rs.getInt("qtd"));
				c.setFuncionario(fs.obter(rs.getLong("id_funcionario")));
				c.setItens(it.listaItensPorCompra(rs.getLong("id")));
				compras.add(c);
			}

			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return compras;
		} catch (Exception e) {
			System.out.println(e);
			throw new IllegalArgumentException(e);
		}

	}

}
