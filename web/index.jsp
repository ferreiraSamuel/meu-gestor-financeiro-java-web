<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.Receita"%>
<%@page import="beans.Despesa"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.List"%>
<%@page import="dao.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Meu Gestor Financeiro</title>
        <link rel="stylesheet" href="./css/style.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>
        <% 
        Locale locale = new Locale("pt","BR");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        %>
        <div class="container d-flex flex-column align-items-center mt-5">
            <h1 class="display-6">Meu Gestor Financeiro</h1>
            <div class="container d-flex justify-content-between mt-5">
                <div class="d-flex flex-column align-items-center w-75 me-5">
                    <table class="table table-hover table-striped w-100">
                        <a href="cadastrarDespesa.jsp" class="btn btn-outline-danger mb-3">Adicionar Despesa</a>
                        <tr>
                            <th>Título</th>
                            <th>Descrição</th>
                            <th>Valor</th>
                            <th>Categoria</th>
                        </tr>
                        <%
                            DespesasDAO despesasDAO = new DespesasDAO();
                            List<Despesa> resultsDespesa = despesasDAO.consultarDespesas();

                            for (Despesa result : resultsDespesa) {%>
                        <tr>
                            <td><%= result.getTitulo()%></td>
                            <td><%= result.getDescricao()%></td>
                            <td><%= numberFormat.format(result.getValor())%></td>
                            <td><%= result.getCategoria_label()%></td>
                        </tr>
                        <%}%>
                    </table>    
                </div>    
                <div class="d-flex flex-column align-items-center w-75 ms-5">
                    <table class="table table-hover table-striped w-100">
                        <a href="cadastrarReceita.jsp" class="btn btn-outline-success mb-3">Adicionar Receita</a>
                        <tr>
                            <th>Título</th>
                            <th>Descrição</th>
                            <th>Valor</th>
                            <th>Categoria</th>
                        </tr>
                        <%
                            ReceitasDAO receitasDAO = new ReceitasDAO();
                            List<Receita> resultsReceita = receitasDAO.consultarReceitas();

                            for (Receita result : resultsReceita) {%>
                        <tr>
                            <td><%= result.getTitulo()%></td>
                            <td><%= result.getDescricao()%></td>
                            <td><%= numberFormat.format(result.getValor())%></td>
                            <td><%= result.getCategoria_label()%></td>
                        </tr>
                        <%}%>
                    </table>    
                </div> 
            </div>
        </div>
    </body>
</html>
