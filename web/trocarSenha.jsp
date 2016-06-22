<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2" id="tituloEditarUsuario">Alterar Senha</h1>
    </div>
    <form name="formularioTrocaSenha" action="Servlet?t=trocarSenha" method="POST">
        <div class="form2" id="FormTrocarSenha">
            <div class="forceColor2"></div>
            <div class="topbar2">
                <div class="spanColor2"></div>
                
                <input name="senhaAtual" type="password" class="input" id="inputCadastroUsuario" required placeholder="Senha atual"
                       pattern="[a-zA-Z0-9]{5}"/>
                <input name="novaSenha" type="password" class="input" id="inputCadastroUsuario" required pattern="[a-zA-Z0-9]{5}"
                       placeholder="Nova senha (Minimo 5 caracteres)"
                       onchange="formularioTrocaSenha.pwd2.pattern = this.value;"/>
                <input name="pwd2" type="password" class="input" id="inputCadastroUsuario" required pattern="[a-zA-Z0-9]{5}"
                       placeholder="Repita a nova senha" onchange="this.setCustomValidity(this.validity.patternMismatch ?
                                       'As senhas não conferem.' : '')"/>
            </div>
            <button class="submit">Aplicar alteração</button>
        </div>
    </form>
    
    
    <div class="errorMsgTrocarSenha">
        <!-- Carregar mensagem de erro, se houver -->
        <c:if test="${not empty param.mensagem}">
            <c:out value="${param.mensagem}"/>
        </c:if>
    </div>

    <a href="perfilUsuario.jsp"><button id="findpass3">Ir para Perfil</button></a>
    
    
<%@include file="footer.jsp"%>