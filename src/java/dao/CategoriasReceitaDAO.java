package dao;

//import beans.CategoriaReceita;
import beans.CategoriaReceita;
import java.sql.Connection;
import database.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriasReceitaDAO {

    private List<CategoriaReceita> categorias;

    public List<CategoriaReceita> consultarCategoriasReceita() throws ClassNotFoundException {
        String SELECT_CATEGORIA_SQL = "select * from categorias_receitas";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            List<CategoriaReceita> categorias =  new ArrayList<CategoriaReceita>();
                    
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIA_SQL);

            ResultSet result = preparedStatement.executeQuery();
            
            while (result.next()) {
                CategoriaReceita categoria = new CategoriaReceita();
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
