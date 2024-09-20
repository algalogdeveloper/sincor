package br.com.lrt.sincor.repository;

import br.com.lrt.sincor.datasource.DataSource;
import br.com.lrt.sincor.model.Itens;
import br.com.lrt.sincor.service.MilharService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

public class ItemRepository {
	
	private DataSource dataSource;
	
	public long maximoId() { 
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			ResultSet rs = connection.prepareStatement("select max(id) as maximoID from itens").executeQuery();
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
			PreparedStatement statement = connection.prepareStatement("delete from itens where id = ?");
			statement.setObject(1, id);
			statement.execute();
			statement.close();
			dataSource.fecharConexao();
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	
	public void persiste(Itens item) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("insert into itens values (?,?,?,?)");
			ps.setObject(1, item.getId());
			ps.setObject(2, item.getDataItem());
			ps.setObject(3, item.getCompra().getId());	
			ps.setObject(4, item.getMilhar().getId());
			ps.execute();
			System.out.println("Insert:" + ps.toString());
			ps.close();
			dataSource.fecharConexao();

		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}
	
	
	public void update(Itens item) {
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("update itens set id_milhar = ? where id = ? ");
			ps.setObject(2, item.getId());	
			ps.setObject(1, item.getMilhar().getId());
			ps.execute();
			System.out.println("Insert:" + ps.toString());
			ps.close();
			dataSource.fecharConexao();

		} catch (SQLException e2) {
			throw new IllegalArgumentException(e2);
		}
	}
	
	public LinkedHashSet<Itens> listaItensPorCompra(long param){
		try {
			dataSource = DataSource.conexaoUnica();
			dataSource.openConexao();
			Connection connection = dataSource.executeTransaction();
			PreparedStatement ps = connection.prepareStatement("select * from itens i where i.id_compra = ? order by i.id_milhar");
			ps.setObject(1, param);
			System.out.println("Query:" + ps.toString());
			ResultSet rs = ps.executeQuery();
			LinkedHashSet<Itens> itens = new LinkedHashSet<Itens>();
			MilharService ms = new MilharService(new MilharRepository());
			while(rs.next()) {
				Itens item = new Itens(rs.getLong(1), null, rs.getDate(2).toLocalDate(), ms.obter(rs.getLong(4)));
				itens.add(item);
			}
			ps.close();
			rs.close();
			dataSource.fecharConexao();
			return itens;
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
		
	}

}
