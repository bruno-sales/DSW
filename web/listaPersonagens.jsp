<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<%    String usuario = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                usuario = cookie.getValue();
            }
        }
    }
/*    if (usuario == null) {
        response.sendRedirect("login.jsp");
    }*/
%>
<h3 class="boasVindas">Bem-vindo <%=usuario%></h3>

<div class="menu2">
        <div class="mainmenu2 clearfix">
            <h1 class="menuitem2">Personagens</h1>
        </div>

    <button id="findpass2"><a href="Servlet?t=listarPersonagens">Listar Personagens</a></button>
    
    <div class="form2">
        <div class="forceColor2"></div>
        <div class="topbar2">
            <div class="spanColor2"></div>
            <c:forEach var="person" items ="${requestScope.personas}">
                <label name="nome" type="text" class="input">${person.id} - ${person.nome}</label>
            </c:forEach>
        </div>
    </div>
    
    <button id="findpass3"><a href="Servlet?t=logoff">Logoff</a></button>
    
<%@include file="footer.jsp"%>