package br.com.lrt.sincor.repository;

import br.com.lrt.sincor.datasource.DataSource;
import br.com.lrt.sincor.model.Cliente;
import br.com.lrt.sincor.service.CompraService;
import br.com.lrt.sincor.service.EnderecoService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;


public class ClienteRepository {

	private DataSource dataSource;

	public Cliente persiste(Cliente c) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("insert into clientes values (?,?,?,?,?)");
			ps.setObject(1, c.getId());
			ps.setObject(2, c.getCliente());
			ps.setObject(3, c.getIdentificador());
			ps.setObject(4, c.getDataRegistro());
			ps.setObject(5, c.getEndereco().getId());
			ps.execute();
			System.out.println("Insert:" + ps.toString());
			ps.close();
			dataSource.fecharConexao();
			return c;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public void remover(long id) {
		Cliente cliente = obter(id);
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement statement = connection.prepareStatement("delete from clientes where id = ?");
			statement.setObject(1, cliente.getId());
			statement.execute();
			statement.close();
			dataSource.fecharConexao();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public long maximoId() {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			ResultSet rs = connection.prepareStatement("select max(id) as maximoID from clientes").executeQuery();
			rs.next();
			long maximo = rs.getLong("maximoID");
			rs.close();
			dataSource.fecharConexao();
			return maximo;
		} catch (Exception e) {
			return 0;
		}
	}

	public LinkedHashSet<Cliente> listadeClientesPorEndereco(long id) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from clientes c where c.endereco_id = ?");
			ps.setObject(1, id);
			LinkedHashSet<Cliente> clientes = new LinkedHashSet<Cliente>();
			System.out.println("consulta:" + ps.toString());
			CompraService cs = new CompraService(new CompraRepository());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getLong(1));
				c.setCliente(rs.getString(2));
				c.setIdentificador(rs.getString(3));
				c.setDataRegistro(rs.getDate(4).toLocalDate());
				c.setCompras(cs.buscarCompraCliente(rs.getLong(1)));
				clientes.add(c);
			}
			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return clientes;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public LinkedHashSet<Cliente> listadeClientes() {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from clientes c order by id");
			LinkedHashSet<Cliente> clientes = new LinkedHashSet<Cliente>();
			System.out.println("Consulta:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			EnderecoService es = new EnderecoService(new EnderecoRepository());
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getLong(1));
				c.setCliente(rs.getString(2));
				c.setIdentificador(rs.getString(3));
				c.setDataRegistro(rs.getDate(4).toLocalDate());
				c.setEndereco(es.obter2(rs.getLong(5)));
				clientes.add(c);
			}
			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return clientes;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public Cliente obter(String id) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from clientes c where c.identificador = ?");
			ps.setObject(1, id);
			System.out.println("Consulta" + ps.toString());
			ResultSet rs = ps.executeQuery();
			EnderecoService es = new EnderecoService(new EnderecoRepository());
			Cliente c = null;
			if (rs.next()) {
				c = new Cliente();
				c.setId(rs.getLong(1));
				c.setCliente(rs.getString(2));
				c.setIdentificador(rs.getString(3));
				c.setDataRegistro(rs.getDate(4).toLocalDate());
				c.setEndereco(es.obter(rs.getLong(5)));
			}
			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return c;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public Cliente obter(long id) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from clientes c where c.id = ?");
			ps.setObject(1, id);
			System.out.println("Consulta:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			EnderecoService es = new EnderecoService(new EnderecoRepository());
			Cliente c = null;
			if (rs.next()) {
				c = new Cliente();
				c.setId(rs.getLong(1));
				c.setCliente(rs.getString(2));
				c.setIdentificador(rs.getString(3));
				c.setDataRegistro(rs.getDate(4).toLocalDate());
				c.setEndereco(es.obter2(rs.getLong(5)));
			}
			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return c;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public Cliente update(Cliente c) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement(
					"update clientes set cliente=?,identificador=?,dataregistro=?,endereco_id=? where id=?");
			ps.setObject(5, c.getId());
			ps.setObject(1, c.getCliente());
			ps.setObject(2, c.getIdentificador());
			ps.setObject(3, c.getDataRegistro());
			ps.setObject(4, c.getEndereco().getId());
			ps.execute();
			System.out.println("Update:" + ps.toString());
			ps.close();
			dataSource.fecharConexao();
			return c;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}

	}

}
