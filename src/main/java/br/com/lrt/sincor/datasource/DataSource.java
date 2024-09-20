package br.com.lrt.sincor.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DataSource {

    private static Connection connection = null;
    private static DataSource dataSource;
    private static int qtd = 0;

    public static DataSource conexaoUnica() {

        if (Objects.isNull(dataSource)) {
            qtd++;
            dataSource = new DataSource();
        }
        System.out.println("Numeros de conexões:" + qtd);
        return dataSource;
    }

    public synchronized void openConexao() {
        try {
            DriverManager.setLoginTimeout(10);
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sinfasdb", "postgres", "root");
            System.out.println("Tempo de conexão = " + DriverManager.getLoginTimeout()); //to get login timeout  

        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Connection executeTransaction() {
        return connection;
    }

    public void fecharConexao() {
        try {
            System.out.println("Conexão fechada.");
            connection.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

}
