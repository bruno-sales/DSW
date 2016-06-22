<%@include file="headerLogin.jsp"%>

<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu">
    <div class="mainmenu clearfix">
        <h1 class="menuitem">Login</h1>
    </div>
</div>

<form action="Servlet?t=login" class="login" method="POST">
    <div class="form">
        <div class="forceColor"></div>
        <div class="topbar">
            <div class="spanColor"></div>
            <input name="email" type="text"                
                   class="input" id="email" placeholder="E-mail"/>

            <input name="senha" type="password" 
                   class="input" placeholder="Senha"/>        
        </div>

        <input type="submit" class="submit" id="submit" />
    </div>
</form>                

<a href="recuperarSenha.jsp"><button id="findpass">Esqueceu sua senha?</button></a>
<a href="cadastroUsuario.jsp"><button id="linkCadastro">Cadastrar-se</button></a>

<div class="errorMsg">
    <c:if test="${not empty param.mensagem}">
        <c:out value="${param.mensagem}"/>
    </c:if> 
</div>
<%@include file="footer.jsp"%>
