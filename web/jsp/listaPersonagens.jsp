<!--
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
-->
    <h2>Personagens</h2>

            <table id="tabelaPersonagens">
            <c:forEach var="person" items ="${requestScope.personas}">
                <tr>
                    <td>${person.id}</td> 
                    <td>${person.nome}</td>
                </tr>
            </c:forEach>
            </table>

<%@include file="footer.jsp"%>