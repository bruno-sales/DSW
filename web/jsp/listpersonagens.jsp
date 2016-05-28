<%-- 
    Document   : listarLivros.jsp
    Created on : 11/07/2011, 16:26:40
    Author     : 2010012
--%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
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

            <table>
            <c:forEach var="person" items ="${requestScope.personas}">
                <tr>
                    <td>${person.id}</td> 
                    <td>${person.nome}</td>
                </tr>
            </c:forEach>
            </table>
    </body>
</html>
