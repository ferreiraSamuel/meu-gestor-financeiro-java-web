package dao;

import beans.CategoriaDespesa;
import java.sql.Connection;
import database.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDespesaDAO {

    private List<CategoriaDespesa> categorias;

    public List<CategoriaDespesa> consultarCategoriasDespesa() throws ClassNotFoundException {
        String SELECT_CATEGORIA_SQL = "select * from categorias_despesas";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            List<CategoriaDespesa> categorias =  new ArrayList<CategoriaDespesa>();
                    
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIA_SQL);

            ResultSet result = preparedStatement.executeQuery();
            
            while (result.next()) {
                CategoriaDespesa categoria = new CategoriaDespesa();
                categoria.setId(result.getInt("id"));
                categoria.setTitulo(result.getString("titulo"));
                
                categorias.add(categoria);
            }
            
            result.close();
            connection.close();
            
            return categorias;
        } catch (SQLException e) {
            printSQLException(e);
        }

        return categorias;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
