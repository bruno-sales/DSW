<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Editar Perfil</h1>
    </div>

    
    <form name="formularioCadastro" action="Servlet?t=cadastrarUsuario" method="POST">
        <div class="form2">
            <div class="forceColor2"></div>
            <div class="topbar2">
                <div class="spanColor2"></div>
                <input name="nome" type="text" class="input" id="password" placeholder="Nome"
                       pattern="[a-zA-Z\s]+$"/>
                <input name="telefone" type="text" class="input" id="password" placeholder="Telefone (XX)XXXX-XXXX"
                       pattern="\([0-9]{2}\)[0-9]{4,6}-[0-9]{3,4}$"/>
                <input name="cpf" type="text" class="input" id="password" placeholder="CPF XXX.XXX.XXX-XX"
                       pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"/>
                <!--input da foto-->
                <input type="text" class="input" id="imagem" placeholder="Escolha uma nova imagem!">
                
            </div>

            <button class="submit" id="submit">Aplicar alterações</button>
        </div>
    </form>

    <div class="errorMsg">
        <!-- Carregar mensagem de erro, se houver -->
        <c:if test="${not empty param.mensagem}">
            <c:out value="${param.mensagem}"/>
        </c:if>
    </div>

    <a href="login.jsp"><button id="findpass2">Ir para pagina de login</button></a>
    
    
<%@include file="footer.jsp"%>