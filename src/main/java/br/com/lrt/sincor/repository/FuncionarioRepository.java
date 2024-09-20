package br.com.lrt.sincor.repository;

import br.com.lrt.sincor.datasource.DataSource;
import br.com.lrt.sincor.model.Funcionario;
import br.com.lrt.sincor.model.Permissao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Objects;

public class FuncionarioRepository {

	private DataSource dataSource;

	public void persiste(Funcionario f) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("insert into funcionarios values (?,?,?,?,?)");
			ps.setObject(1, f.getId());
			ps.setObject(2, f.getNome());
			ps.setObject(3, f.getPermissao().name());
			ps.setObject(4, f.getLogin());
			ps.setObject(5, f.getSenha());
			ps.execute();
			System.out.println("Log:" + ps.toString());
			ps.close();
			dataSource.fecharConexao();

		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public boolean autorizarFuncionario(String login, String senha) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection
					.prepareStatement("select * from funcionarios f where f.login = ? and f.senha = ?");
			ps.setObject(1, login);
			ps.setObject(2, senha);
			System.out.println("consulta cliente:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			Funcionario f = null;
			if (rs.next()) {
				f = new Funcionario(rs.getLong(1), rs.getString(2), Permissao.valueOf(rs.getString(3)), rs.getString(4),
						rs.getString(5));

			}

			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return Objects.nonNull(f);
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public Funcionario permitirAcesso(String login, String senha) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection
					.prepareStatement("select * from funcionarios f where f.login = ? and f.senha = ?");
			ps.setObject(1, login);
			ps.setObject(2, senha);
			System.out.println("consulta cliente:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			Funcionario f = null;
			if (rs.next()) {
				f = new Funcionario(rs.getLong(1), rs.getString(2), Permissao.valueOf(rs.getString(3)), rs.getString(4),
						rs.getString(5));

			}

			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return f;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public LinkedHashSet<Funcionario> todos() {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from funcionarios f");
			System.out.println("consulta cliente:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			Funcionario f = null;
			LinkedHashSet<Funcionario> funcionarios = new LinkedHashSet<Funcionario>();
			while (rs.next()) {
				f = new Funcionario(rs.getLong(1), rs.getString(2), Permissao.valueOf(rs.getString(3)), rs.getString(4),
						rs.getString(5));
				funcionarios.add(f);
			}

			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return funcionarios;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public long maximoId() {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			ResultSet rs = connection.prepareStatement("select max(id) as maximoID from funcionarios").executeQuery();
			rs.next();
			long maximo = rs.getLong("maximoID");
			rs.close();
			dataSource.fecharConexao();
			return maximo;
		} catch (Exception e) {
			return 0;
		}
	}

	public Funcionario obter(long id) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from funcionarios f where id = ?");
			ps.setObject(1, id);
			System.out.println("consulta cliente:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			Funcionario f = null;
			while (rs.next()) {
				f = new Funcionario(rs.getLong(1), rs.getString(2), Permissao.valueOf(rs.getString(3)), rs.getString(4),
						rs.getString(5));
			}
			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return f;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	public void remover(Long valueOf) {
		
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("delete from funcionarios f where id = ?");
			ps.setObject(1, valueOf);
			ps.execute();
			System.out.println("SQL:" + ps.toString());
			ps.close();
			dataSource.fecharConexao();			
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

}
