<%@page import="Model.Oferta"%>
<%@page import="Model.DAO.UsuarioDAO"%>
<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Transfer&ecirc;ncia Banc&aacute;ria</h1>
    </div>
    
    <form name="formularioEditarUsuario" action="Servlet?t=registrarTransferencia" method="POST">   
        <div class="form2" id="FormListaOfertas">
            <div class="forceColor2"></div>
            <div class="topbar2">
                <div class="spanColor2" id=""></div>
                <input name="cpf" type="text" class="input" placeholder="CPF XXX.XXX.XXX-XX"
                       required pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"/>
                <input name="bancoOrigem" type="text" class="input" placeholder="Codigo do Banco XXX"
                       required pattern="[0-9]{3}"/>
                <input name="agenciaOrigem" type="text" class="input" placeholder="Agencia XXXX-X"
                       required pattern="\d{4}-\d{1}"/>
                <input name="contaOrigem" type="text" class="input" placeholder="Conta XXXXX-X"
                       required pattern="\d{5}-\d{1}"/>
                <input name="valor" type="text" class="input" placeholder="Valor R$ XX.XX"
                       required pattern="[0-9]{2}.[0-9]{2}"/>
                
                <button class="submit" id="submitAplicarAlteracoes">Registrar</button>
            </div>
        </div>
    </form>

    <%@include file="footer.jsp"%>