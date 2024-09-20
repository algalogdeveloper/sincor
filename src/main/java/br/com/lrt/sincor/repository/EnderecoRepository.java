package br.com.lrt.sincor.repository;

import br.com.lrt.sincor.datasource.DataSource;
import br.com.lrt.sincor.model.Endereco;
import br.com.lrt.sincor.model.Rota;
import br.com.lrt.sincor.service.ClienteService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

public class EnderecoRepository {

	private DataSource dataSource;

	public void persiste(Endereco e) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("insert into enderecos values (?,?,?)");
			ps.setObject(1, e.getId());
			ps.setObject(2, e.getNome());
			ps.setObject(3, e.getRota().name());
			ps.execute();
			System.out.println("Log:" + ps.toString());
			ps.close();			
			dataSource.fecharConexao();
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}

	}

	public LinkedHashSet<Endereco> todos() {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from enderecos e order by e.id");
			LinkedHashSet<Endereco> enderecos = new LinkedHashSet<Endereco>();
			System.out.println("Log:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enderecos.add(new Endereco(rs.getLong(1), rs.getString(2), Rota.valueOf(rs.getString(3))));
			}
			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return enderecos;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}
	
	public LinkedHashSet<Endereco> todosEnderecosComClientes() {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select e.id as chave, e.nome as rua, e.rota as rota from enderecos e , clientes c where e.id = c.endereco_id ;");
			LinkedHashSet<Endereco> enderecos = new LinkedHashSet<Endereco>();
			System.out.println("Log:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				enderecos.add(new Endereco(rs.getLong("chave"), rs.getString("rua"), Rota.valueOf(rs.getString("rota"))));
			}

			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return enderecos;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public Endereco obter(long id) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from enderecos e where e.id = ?");
			ps.setObject(1, id);
			System.out.println("Log:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			Endereco endereco = null;
			ClienteService cs = new ClienteService(new ClienteRepository());
			if (rs.next()) {
				endereco = new Endereco(rs.getLong(1), rs.getString(2), Rota.valueOf(rs.getString(3)));
				endereco.setClientes(cs.listadeClientesPorEndereco(rs.getLong(1)));
			}

			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return endereco;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}
	
	public Endereco obter2(long id) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from enderecos e where e.id = ?");
			ps.setObject(1, id);
			System.out.println("Log:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			Endereco endereco = null;
			if (rs.next()) {
				endereco = new Endereco(rs.getLong(1), rs.getString(2), Rota.valueOf(rs.getString(3)));
			}

			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return endereco;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public long maximoId() {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			ResultSet rs = connection.prepareStatement("select max(id) as maximoID from enderecos").executeQuery();
			rs.next();
			long maximo = rs.getLong("maximoID");
			rs.close();
			dataSource.fecharConexao();
			return maximo;
		} catch (Exception e) {
			return 0;
		}
	}

}
