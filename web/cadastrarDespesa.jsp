<%@page import="beans.CategoriaDespesa"%>
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
        <title>Cadastrar Despesa</title>
    </head>
    <body>
        <div class="container d-flex flex-column align-items-center mt-5">
            <h1 class="display-6">Cadastrar Despesa</h1>

            <form action="tratarDados.jsp" method="POST" class="container d-flex mt-5 flex-column w-50">
                <input type="hidden" name="type" id="type" value="2">

                <div class="mb-3 d-flex flex-column">
                    <label for="titulo" class="form-label">Título:</label>
                    <input class="form-control" type="text" placeholder="O que foi essa despesa?" id="titulo" name="titulo">
                </div>

                <div class="mb-3 d-flex flex-column">
                    <label for="descricao" class="form-label">Descrição:</label>
                    <input class="form-control" type="text" placeholder="Descreva sobre ela" id="descricao"  name="descricao">
                </div>                   

                <div class="mb-3 d-flex flex-column">
                    <label for="valor" class="form-label">Valor:</label>
                    <input class="form-control" type="text" placeholder="Qual o valor R$?" id="valor" name="valor">
                </div>                   

                <div class="mb-4 d-flex flex-column">
                    <label for="categoria_id" class="form-label">Escolha uma categoria:</label>
                     <select class="form-select" id="categoria_id" name="categoria_id">
                    <%
                        CategoriasDespesaDAO categoriasReceitaDAO = new CategoriasDespesaDAO();
                        List<CategoriaDespesa> results = categoriasReceitaDAO.consultarCategoriasDespesa();

                        for (CategoriaDespesa result : results) {%>
                    <option value="<%= result.getId()%>"><%= result.getTitulo()%></option>
                    <%}%>
                    //                  %>
                </select>
                </div>        
               

                <input type="submit" id="btnSubmit" value="Cadastrar" class="btn btn-outline-secondary">
            </form>
        </div> 
    </body>
</html>
