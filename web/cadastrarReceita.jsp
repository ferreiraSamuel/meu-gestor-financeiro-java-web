<%@page import="beans.CategoriaReceita"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.List"%>
<%@page import="dao.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <title>Cadastrar Receita</title>
    </head>
    <body>
        <h1>Cadastrar Receita</h1>
        <div>
            <form action="tratarDados.jsp" method="POST">
                <input type="hidden" name="type" id="type" value="1">

                <input type="text" placeholder="O que foi essa receita?" id="titulo" name="titulo">

                <input type="text" placeholder="Descreva sobre ela" id="descricao"  name="descricao">

                <input type="text" placeholder="Qual o valor?" id="valor" name="valor">

                <select id="categoria_id" name="categoria_id">
                    <%
                        CategoriasReceitaDAO categoriasReceitaDAO = new CategoriasReceitaDAO();
                        List<CategoriaReceita> results = categoriasReceitaDAO.consultarCategoriasReceita();

                        for (CategoriaReceita result : results) {%>
                    <option value="<%= result.getId()%>"><%= result.getTitulo()%></option>
                    <%}%>
                    %>
                </select>

                <input type="submit" id="btnSubmit" value="Cadastrar"  class="btn btn-outline-secondary">
            </form>
        </div>
    </body>
</html>
