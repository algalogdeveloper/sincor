package br.com.lrt.sincor.repository;

import br.com.lrt.sincor.datasource.DataSource;
import br.com.lrt.sincor.model.Milhar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Objects;

public class MilharRepository {

	private DataSource dataSource;
	
	public void persiste(Milhar m) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("insert into milhars values (?,?)");
			ps.setObject(1, m.getId());
			ps.setObject(2, m.getNumero());
			ps.execute();
			System.out.println("Insert:" + ps.toString());
			ps.close();
			dataSource.fecharConexao();
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}
	
	
	public LinkedHashSet<Milhar> todos(){
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from milhar m");
			System.out.println("Consulta" + ps.toString());
			ResultSet rs = ps.executeQuery();
			LinkedHashSet<Milhar> todos = new LinkedHashSet<Milhar>();
			if (rs.next()) {
				Milhar milhar = new Milhar(rs.getLong(1), rs.getString(2));
				todos.add(milhar);
			}
			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return todos;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}
	
	public Milhar obter(String param){
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from milhars c where c.numero = ?");
			ps.setObject(1, param);
			System.out.println("Consulta:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			Milhar milhar = null;
			if (rs.next()) {
				 milhar = new Milhar(rs.getLong(1), rs.getString(2));
			}
			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return milhar;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}
	
	public Milhar obter(long param){
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from milhars c where c.id = ?");
			ps.setObject(1, param);
			System.out.println("Consulta:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			Milhar milhar = null;
			if (rs.next()) {
				 milhar = new Milhar(rs.getLong(1), rs.getString(2));
			}
			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return milhar;
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}
	
	public boolean validarMilhar(String param) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select m.id as codigo, m.numero as value from milhars m , itens i where m.id = i.id_milhar and m.numero = ?");
			ps.setObject(1, param);
			System.out.println("Consulta:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			Milhar milhar = null;
			if (rs.next()) {
				 milhar = new Milhar(rs.getLong("codigo"), rs.getString("value"));
			}
			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return Objects.isNull(milhar);
		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}

	
	
}
