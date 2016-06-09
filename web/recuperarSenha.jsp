<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--<%@include file="header.jsp"%>-->

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<script>
    function habilitaPainelEntradaToken() {
        document.getElementById("painelEnvioToken").style.display = "none";
        document.getElementById("btTemToken").style.display = "none";
        document.getElementById("painelEntradaToken").style.display = "inline";
        document.getElementById("btNovoToken").style.display = "inline";
    }
    function habilitaPainelEnvioToken() {
        document.getElementById("painelEnvioToken").style.display = "inline";
        document.getElementById("btTemToken").style.display = "inline";
        document.getElementById("painelEntradaToken").style.display = "none";
        document.getElementById("btNovoToken").style.display = "none";
    }
</script>

<div id="painelEnvioToken">
    <form name="formularioToken" action="Gogo?t=enviarToken" method="POST">
        <input name="email" type="text"                
               class="input" id="email" placeholder="E-mail"/>   
        <button id="findpass3">Enviar Token</button>
    </form>
</div>

<div id="painelEntradaToken" style="display: none">
    <form name="formularioToken" action="Gogo?t=verificarToken" method="POST">
        <input name="email" type="text"                
               class="input" id="email" placeholder="E-mail"/>   <br>
        <input name="token" type="text"                
               class="input" id="token" placeholder="token"/>   
        <button id="findpass3">Validar Token</button>
    </form>
</div>

<button id="btNovoToken" style="display: none" onclick="habilitaPainelEnvioToken()">Obter novo Token</button>
<button id="btTemToken" onclick="habilitaPainelEntradaToken()">Já possuo um Token</button>




<c:if test="${requestScope.tokenValido}">
    <form action="Gogo?t=recuperarSenha" method="Post">
        <input name="usuarioId" type="text" style="visibility: hidden;" value="${requestScope.usuarioId}"/>
        
          <input name="senha" type="password" 
           class="input" id="password" required pattern="[a-zA-Z0-9]{5}"
           placeholder="Senha (Minimo 5 caracteres)"
           onchange="formularioCadastro.pwd2.pattern = this.value;"/>

        <input name="pwd2" type="password" 
               class="input" id="password" required pattern="[a-zA-Z0-9]{5}"
               placeholder="Repita a senha" 
               onchange="this.setCustomValidity(this.validity.patternMismatch ?
                               'As senhas não conferem.' : '')"/>   

        <button id="findpass3">Alterar minha senha</button>
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