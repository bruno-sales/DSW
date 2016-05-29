<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="/WEB-INF/simplemvc.tld" prefix="mvc"%>

<h2>Login</h2>

<mvc:error/>
<mvc:notice/>

<c:set var="usuario" value="${requestScope.usuario}"/>

    <form action="ActionLogin.java">
        <input type="hidden" name="id" value="${usuario.idUsuario}"/>
        E-mail: <input name="email" type="text" value="${usuario.email}">
        Senha: <input name="pwd" type="text" value="${usuario.senha}">
    </form>

<%@include file="footer.jsp"%>