<%-- 
    Document   : listarLivros.jsp
    Created on : 11/07/2011, 16:26:40
    Author     : 2010012
--%>

<%@page import="Model.Personagem"%>
<%@page import="java.util.List"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Page</title>
    </head>
    <body>
        <h1>Personagens</h1>

        <ul>
            <c:forEach var="person" items ="${personagens}">
                
                <li><b>${person.id}</b> - ${person.nome}</li>
                
            </c:forEach>
        </ul>
    </body>
</html>
