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
            Locale locale = new Locale("pt", "BR");
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
                            <th></th>
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
                            <td>
                                <div class="d-flex gap-3 align-items-center">
                                    <a href="tratarDadosExcluir.jsp?id=<%=result.getId()%>&type=2">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#dc3545" class="bi bi-trash" viewBox="0 0 16 16">
                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                        </svg>
                                    </a>
                                    <a href="atualizarDespesa.jsp?id=<%=result.getId()%>">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="grey" class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </a>
                                </div>
                            </td>
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
                            <th></th>
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
                            <td>
                                <div class="d-flex gap-3 align-items-center">
                                    <a href="tratarDadosExcluir.jsp?id=<%=result.getId()%>&type=1">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#dc3545" class="bi bi-trash" viewBox="0 0 16 16">
                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                                        </svg>
                                    </a>
                                    <a href="atualizarReceita.jsp?id=<%=result.getId()%>">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="grey" class="bi bi-pencil" viewBox="0 0 16 16">
                                        <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </a>
                                </div>
                            </td>
                        </tr>
                        <%}%>
                    </table>    
                </div> 
            </div>
        </div>
    </body>
</html>
