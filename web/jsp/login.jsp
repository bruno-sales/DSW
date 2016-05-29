<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

    <div class="menu">
        <div class="mainmenu clearfix">
            <h1 class="menuitem">Login</h1>
        </div>
    </div>

<html:form action="/login" styleClass="login">
        <div class="form">
        <div class="forceColor"></div>
        <div class="topbar">
        <div class="spanColor"></div>
            <html:text property="email" styleClass="input" styleId="teste"/>
            <html:text property="senha" styleClass="input" styleId="teste2"/>
        </div>
                
            <html:submit styleClass="submit" styleId="submit" >Entrar</html:submit>
        </div>        
        
        <button id="findpass"><a href="login.jsp">Esqueceu sua senha?</a></button> <!--numseiqualéo.do-->
        <button id="linkCadastro"><a href="cadastroUsuario.jsp">Cadastrar-se</a></button>
        
    </html:form>
        
<%@include file="footer.jsp"%>
