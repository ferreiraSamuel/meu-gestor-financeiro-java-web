/*
DATABASE:
    create table aluno (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(100) NOT NULL,
        idade INT(3) NOT NULL,
        nome_mae VARCHAR(100),
        nome_pai VARCHAR(100),
        telefone VARCHAR(11),
        ramal VARCHAR(3),
        email VARCHAR(100), 
        serie INT(1),
        turno VARCHAR(5)
    );
 */
package dao;

import beans.Receita;
import java.sql.Connection;
import database.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReceitasDAO {

    int result = 0;

    public int cadastrarReceita(Receita receita) throws ClassNotFoundException {
        System.out.println("Chamou função cadastrarReceita");
        String INSERT_USERS_SQL = "INSERT INTO receitas"
                + "  (titulo, descricao, valor, categoria_id) VALUES "
                + " (?, ?, ?, ?);";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);

            preparedStatement.setString(1, receita.getTitulo());
            preparedStatement.setString(2, receita.getDescricao());
            preparedStatement.setDouble(3, receita.getValor());
            preparedStatement.setInt(4, receita.getCategoria_id());

            System.out.println(preparedStatement);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }

        return result;
    }

    private List<Receita> receitas;

    public List<Receita> consultarReceitas() throws ClassNotFoundException {
        String SELECT_CATEGORIA_SQL = "select receitas.id, receitas.titulo, receitas.descricao, receitas.valor, receitas.categoria_id, categorias_receitas.titulo as categoria_label "
                + " from receitas "
                + " inner join categorias_receitas on receitas.categoria_id = categorias_receitas.id; ";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            List<Receita> receitas = new ArrayList<Receita>();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIA_SQL);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Receita receita = new Receita();
                receita.setId(result.getInt("id"));
                receita.setTitulo(result.getString("titulo"));
                receita.setDescricao(result.getString("descricao"));
                receita.setValor(result.getDouble("valor"));
                receita.setCategoria_id(result.getInt("categoria_id"));
                receita.setCategoria_label(result.getString("categoria_label"));

                receitas.add(receita);
            }

            result.close();
            connection.close();

            return receitas;
        } catch (SQLException e) {
            printSQLException(e);
        }

        return receitas;
    }
    
    public List consultarReceita(String id) throws ClassNotFoundException {
        String SELECT_RECEITA_SQL = "select receitas.id, receitas.titulo, receitas.descricao, receitas.valor, receitas.categoria_id, categorias_receitas.titulo as categoria_label "
                + " from receitas "
                + " inner join categorias_receitas on receitas.categoria_id = categorias_receitas.id "
                + " where receitas.id="+id;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            List<Receita> receitas = new ArrayList<Receita>();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECEITA_SQL);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Receita receita = new Receita();
                receita.setId(result.getInt("id"));
                receita.setTitulo(result.getString("titulo"));
                receita.setDescricao(result.getString("descricao"));
                receita.setValor(result.getDouble("valor"));
                receita.setCategoria_id(result.getInt("categoria_id"));
                receita.setCategoria_label(result.getString("categoria_label"));

                receitas.add(receita);
            }

            result.close();
            connection.close();

            return receitas;
        } catch (SQLException e) {
            printSQLException(e);
        }

        return receitas;
    }
     
    public int atualizarReceita(Receita receita) throws ClassNotFoundException {

        String INSERT_USERS_SQL = "UPDATE receitas "
                + " set titulo=?, descricao=?, valor=?, categoria_id=?"
                + " WHERE id = ?;";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);

            preparedStatement.setString(1, receita.getTitulo());
            preparedStatement.setString(2, receita.getDescricao());
            preparedStatement.setDouble(3, receita.getValor());
            preparedStatement.setInt(4, receita.getCategoria_id());
            preparedStatement.setInt(5, receita.getId());

            System.out.println(preparedStatement);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }

        return result;
    }
    
     public int excluirReceita(Receita receita) throws ClassNotFoundException {

       String DELETE_RECEITA_SQL = "DELETE from receitas "
                + " WHERE id = ?;";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RECEITA_SQL);

            preparedStatement.setInt(1, receita.getId());

            System.out.println(preparedStatement);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }

        return result;
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
