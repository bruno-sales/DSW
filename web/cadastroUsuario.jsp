<%@include file="headerLogin.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2" id="tituloCadastroUsuario">Cadastro de Usu&aacute;rio</h1>
    </div>

    <form name="formularioCadastro" action="Servlet?t=cadastrarUsuario" method="POST">
        <div class="form2" id="FormCadastroUsuario">
            <div class="forceColor2"></div>
            <div class="topbar2">
                <div class="spanColor2"></div>
                <input name="nome" type="text" class="input" placeholder="Nome"
                       pattern="[a-zA-Z0-9\s]+$"/>
                <input name="telefone" type="text" class="input" placeholder="Telefone (XX)XXXX-XXXX"
                       pattern="\([0-9]{2}\)[0-9]{4,6}-[0-9]{3,4}$"/>
                <input name="cpf" type="text" class="input" placeholder="CPF XXX.XXX.XXX-XX"
                       pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"/>
                <input name="email" type="text" class="input" placeholder="email@exemplo.com.br"
                       pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-z]{2,4}$"/>
                <input name="senha" type="password" class="input" required pattern="[a-zA-Z0-9]{5}"
                       placeholder="Senha (Minimo 5 caracteres)"
                       onchange="formularioCadastro.pwd2.pattern = this.value;">
                <input name="pwd2" type="password" class="input" required pattern="[a-zA-Z0-9]{5}"
                       placeholder="Repita a senha" onchange="this.setCustomValidity(this.validity.patternMismatch ?
                                       'As senhas n�o conferem.' : '')"/>
            </div>

            <button class="submit" id="submit">Entrar</button>
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