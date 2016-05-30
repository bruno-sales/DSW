<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
        <div class="mainmenu2 clearfix">
            <h1 class="menuitem2">Cadastro de Usuario</h1>
        </div>

<form name="formularioCadastro" action="Servlet?t=cadastrarUsuario" method="POST">
        <div class="form2">
        <div class="forceColor2"></div>
        <div class="topbar2">
            <div class="spanColor2"></div>
                <input name="nome" type="text" class="input" id="password" placeholder="Nome"
                       pattern="[a-z\s]+$"/>
                <input name="telefone" type="text" class="input" id="password" placeholder="Telefone (XX)XXXX-XXXX"
                       pattern="\([0-9]{2}\) [0-9]{4,6}-[0-9]{3,4}$"/>
                <input name="cpf" type="text" class="input" id="password" placeholder="CPF XXX.XXX.XXX-XX"
                       pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"/>
                <input name="email" type="text" class="input" id="password" placeholder="email@exemplo.com.br"
                        pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/>
                <input name="senha" type="password" class="input" id="password" required pattern="[a-z]{5}"
                        placeholder="Senha (Minimo 5 caracteres)"
                        onchange="formularioCadastro.pwd2.pattern = this.value;">
                <input name="pwd2" type="password" class="input" id="password" required pattern="[a-z]{5}"
                        placeholder="Repita a senha" onchange="this.setCustomValidity(this.validity.patternMismatch ?
                            'As senhas não conferem.' : '')"/>
            </div>
                
            <button class="submit" id="submit">Entrar</button>
        </div>
</form>

     <div id="errorMsg">            
        <c:if test="${not empty param.mensagem}">
        <c:out value="${param.mensagem}"/>
        </c:if>000
        </div>
        
<button id="findpass2"><a href="Servlet?t=listarPersonagens">&nbsp Ir a lista de personagens! &nbsp</a></button> 


<%@include file="footer.jsp"%>