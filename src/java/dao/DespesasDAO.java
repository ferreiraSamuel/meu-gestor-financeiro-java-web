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

import beans.Despesa;
import java.sql.Connection;
import database.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DespesasDAO {

    int result = 0;

    public int cadastrarDespesa(Despesa despesa) throws ClassNotFoundException {

        System.out.println("Chamou função cadastrarDespesa");
        String INSERT_USERS_SQL = "INSERT INTO despesas"
                + "  (titulo, descricao, valor, categoria_id) VALUES "
                + " (?, ?, ?, ?);";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);

            preparedStatement.setString(1, despesa.getTitulo());
            preparedStatement.setString(2, despesa.getDescricao());
            preparedStatement.setDouble(3, despesa.getValor());
            preparedStatement.setInt(4, despesa.getCategoria_id());

            System.out.println(preparedStatement);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }

        return result;
    }

    private List<Despesa> despesas;

    public List<Despesa> consultarDespesas() throws ClassNotFoundException {
        String SELECT_CATEGORIA_SQL = "select despesas.id, despesas.titulo, despesas.descricao, despesas.valor, despesas.categoria_id, categorias_despesas.titulo as categoria_label "
                + " from despesas "
                + " inner join categorias_despesas on despesas.categoria_id = categorias_despesas.id; ";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            List<Despesa> despesas = new ArrayList<Despesa>();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIA_SQL);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Despesa despesa = new Despesa();
                despesa.setId(result.getInt("id"));
                despesa.setTitulo(result.getString("titulo"));
                despesa.setDescricao(result.getString("descricao"));
                despesa.setValor(result.getDouble("valor"));
                despesa.setCategoria_id(result.getInt("categoria_id"));
                despesa.setCategoria_label(result.getString("categoria_label"));

                despesas.add(despesa);
            }

            result.close();
            connection.close();

            return despesas;
        } catch (SQLException e) {
            printSQLException(e);
        }

        return despesas;
    }

    public List consultarDespesa(String id) throws ClassNotFoundException {
        String SELECT_DESPESA_SQL = "select despesas.id, despesas.titulo, despesas.descricao, despesas.valor, despesas.categoria_id, categorias_despesas.titulo as categoria_label "
                + " from despesas "
                + " inner join categorias_despesas on despesas.categoria_id = categorias_despesas.id "
                + " where despesas.id="+id;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            List<Despesa> despesas = new ArrayList<Despesa>();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DESPESA_SQL);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Despesa despesa = new Despesa();
                despesa.setId(result.getInt("id"));
                despesa.setTitulo(result.getString("titulo"));
                despesa.setDescricao(result.getString("descricao"));
                despesa.setValor(result.getDouble("valor"));
                despesa.setCategoria_id(result.getInt("categoria_id"));
                despesa.setCategoria_label(result.getString("categoria_label"));

                despesas.add(despesa);
            }

            result.close();
            connection.close();

            return despesas;
        } catch (SQLException e) {
            printSQLException(e);
        }

        return despesas;
    }
     
    public int atualizarDespesa(Despesa despesa) throws ClassNotFoundException {

        String UPDATE_DESPESA_SQL = "UPDATE despesas "
                + " set titulo=?, descricao=?, valor=?, categoria_id=?"
                + " WHERE id = ?;";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DESPESA_SQL);

            preparedStatement.setString(1, despesa.getTitulo());
            preparedStatement.setString(2, despesa.getDescricao());
            preparedStatement.setDouble(3, despesa.getValor());
            preparedStatement.setInt(4, despesa.getCategoria_id());
            preparedStatement.setInt(5, despesa.getId());

            System.out.println(preparedStatement);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }

        return result;
    }
    
    public int excluirDespesa(Despesa despesa) throws ClassNotFoundException {

        String DELETE_DESPESA_SQL = "DELETE from despesas "
                + " WHERE id = ?;";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = new ConnectionFactory().getConnection();) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DESPESA_SQL);

            preparedStatement.setInt(1, despesa.getId());

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
