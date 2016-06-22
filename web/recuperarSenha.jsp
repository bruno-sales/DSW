<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="headerLogin.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
            <!--document.getElementsByName("fname")[0].tagName;
            document.getElementsByName("btNovoToken")[0].style.display = "inline";-->
<script>
function habilitaPainelEntradaToken() {
    document.getElementsByName("painelEnvioToken")[0].style.display = "none";
    document.getElementsByName("btTemToken")[0].style.display = "none";
    document.getElementsByName("painelEntradaToken")[0].style.display = "inline"; //
    document.getElementsByName("btNovoToken")[0].style.display = "inline";
}
function habilitaPainelEnvioToken() {
    document.getElementsByName("painelEnvioToken")[0].style.display = "inline";
    document.getElementsByName("btTemToken")[0].style.display = "inline";
    document.getElementsByName("painelEntradaToken")[0].style.display = "none";
    document.getElementsByName("btNovoToken")[0].style.display = "none";
}
function habilitaPainelTrocaSenha() {
    document.getElementsByName("painelEnvioToken")[0].style.display = "block";
    document.getElementsByName("btTemToken")[0].style.display = "none";
    document.getElementById("painelEntradaToken").style.display = "block";
    document.getElementsByName("btNovoToken")[0].style.display = "none";
    }
</script>

<div class="menu">
    <div class="mainmenu clearfix">
        <h1 class="menuitem">Recuperar Senha</h1>
    </div>
</div>

<form name="painelEnvioToken" action="Gogo?t=enviarToken" class="login" method="POST">
    <div class="form">
        <div class="forceColor"></div>
        <div class="topbar">
            <div class="spanColor"></div>
            <input name="email" type="text"                
                   class="input" id="email" placeholder="E-mail"/>
        </div>
        <button type="submit" class="submit" id="submit">Enviar Token</button>
    </div>
</form>


<form name="painelEntradaToken" action="Gogo?t=verificarToken" style="display: none" class="login" method="POST">
    <div class="form">
        <div class="forceColor"></div>
        <div class="topbar">
            <div class="spanColor"></div>
            <input name="email" type="text"                
                   class="input" id="email" placeholder="E-mail"/>
            <input name="token" type="text"                
           class="input" id="token" placeholder="Token"/>  
        </div>
            <button type="submit" class="submit" id="submit" onclick="habilitaPainelTrocaSenha">Verificar Token</button>
    </div>
</form>

<button name="btNovoToken" type="submit" class="submit" id="btNovoToken" style="display: none" onclick="habilitaPainelEnvioToken()">Obter novo Token</button>
<button name="btTemToken" type="submit" class="submit" id="btTemToken" onclick="habilitaPainelEntradaToken()">Já possuo um Token</button>
  
<c:if test="${requestScope.tokenValido}">
    <form action="Gogo?t=recuperarSenha" class="login" method="POST">
        <div class="form">
            <div class="forceColor"></div>
            <div class="topbar">
                <div class="spanColor"></div>
                    <input name="senha" type="password" class="input" id="password" required pattern="[a-zA-Z0-9]{5}"
                         placeholder="Senha (Minimo 5 caracteres)"
                         onchange="formularioCadastro.pwd2.pattern = this.value;"/>

                      <input name="pwd2" type="password" class="input" id="password" required pattern="[a-zA-Z0-9]{5}"
                          placeholder="Repita a senha" onchange="this.setCustomValidity(this.validity.patternMismatch ?
                               'As senhas não conferem.' : '')"/>
            </div>
                <button type="submit" class="submit" id="submit">Alterar minha senha</button>
        </div>
    </form>  
</c:if> 

<div class="errorMsg">      
    <!-- Carregar mensagem de erro, se houver -->
    <c:if test="${not empty param.mensagem}">
        <c:out value="${param.mensagem}"/>
    </c:if> 
    <c:if test="${not empty requestScope.mensagem}">
        <c:out value="${requestScope.mensagem}"/>
    </c:if> 
</div>
<%@include file="footer.jsp"%>