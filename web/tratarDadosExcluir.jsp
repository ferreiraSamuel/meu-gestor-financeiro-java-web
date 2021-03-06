<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="dao.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Excluir dados!</title>
    </head>
    <body>
        <jsp:useBean id="receita" class="beans.Receita" />
        <jsp:useBean id="despesa" class="beans.Despesa" />

        <%
            if (Integer.parseInt(request.getParameter("type")) == 1) {%>
        <jsp:setProperty property="*" name="receita" />
        <%}%>

        <%
            if (Integer.parseInt(request.getParameter("type")) == 2) {%>
        <jsp:setProperty property="*" name="despesa" />
        <%}%>


        <%
            int status = 0;
            int tipo = Integer.parseInt(request.getParameter("type"));

            if (tipo == 1) {
                System.out.println("receita");
                ReceitasDAO receitasDAO = new ReceitasDAO();
                status = receitasDAO.excluirReceita(receita);
            };

            if (tipo == 2) {
                System.out.println("despesa");
                DespesasDAO despesasDAO = new DespesasDAO();
                status = despesasDAO.excluirDespesa(despesa);
            };

            if (status > 0) {
                String redirectURL = "http://localhost:8080/MeuGestorFinanceiro/";
                response.sendRedirect(redirectURL);
            } else {
                out.print("Algo deu errado!");
            }%>

        <a href="/MeuGestorFinanceiro">Voltar</a>
    </body>
</html>