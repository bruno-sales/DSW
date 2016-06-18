<%@include file="headerLogin.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>


<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Recuperar Senha</h1>
    </div>

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


<form name="formularioCadastro" action="Servlet?t=cadastrarUsuario" method="POST">
        <div class="form2">
            <div class="forceColor2"></div>
            <div class="topbar2">
                <div class="spanColor2"></div>
                <input name="nome" type="text" class="input" id="password" placeholder="Nome"
                       pattern="[a-zA-Z\s]+$"/>
            </div>
            <button class="submit" id="submit">Entrar</button>
        </div>
    </form>


<form name="formularioCadastro" action="Servlet?t=cadastrarUsuario" method="POST">
        <div class="form2">
            <div class="forceColor2"></div>
            <div class="topbar2">
                <div class="spanColor2"></div>
                <input name="email" type="text" class="input" id="email" placeholder="E-mail"/>
            </div>
        <button class="submit" id="submit">Enviar Token</button>
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