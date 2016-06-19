<%@page import="Model.Oferta"%>
<%@page import="Model.DAO.UsuarioDAO"%>
<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Transfer&ecirc;ncia Banc&aacute;ria</h1>
    </div>
<!--O	 sistema	 deve	 receber	a	 transfer�ncia	 do	usu�rio,	 fornecendo	 uma	 p�gina	 que	 ser�	 chamada	 por	 outros	
sistemas	 banc�rios	 com	 os	 dados	 da	 transfer�ncia:	 o	 CPF	 do	 usu�rio,	 o	 n�mero	 do	 banco	 de	 origem,	 o	
n�mero	da	conta	de	origem	e	o	valor	transferido;-->
    <a href="Servlet?t=listarOfertas"><button id="btListarOfertas">Listar Ofertas</button></a>

    <div class="form2" id="FormListaOfertas">
        <div class="forceColor2"></div>
        <div class="topbar2">
            <div class="spanColor2" id=""></div>
                <input name="cpf" type="text" class="input" placeholder="CPF XXX.XXX.XXX-XX"
                       required pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"/>
                <input name="numero_Agencia_Origem" type="text" class="input" placeholder="XXXX-XX"
                       required pattern="\d{4-8}-\d{1-2}"/>
                <input name="numero_Conta_Origem" type="text" class="input" placeholder="XXXXX-XX"
                       required pattern="\d{4-8}-\d{2}"/>
                <input name="valor_Tranferencia" type="text" class="input" placeholder="R$ XX,XX"
                       required pattern="[0-9]{3},[0-9]{2}"/>
            </div>
        </div>

    <c:if test="${requestScope.hasPriorPageCompra}">
        <a href='Gogo?t=listarOfertas&page=${requestScope.page-1}'>
            <button id="ant">Anterior</button></a>
    </c:if>
    <c:if test="${requestScope.hasNextPageCompra}">
        <a href='Gogo?t=listarOfertas&page=${requestScope.page+1}'>
            <button id="prox">Pr�xima</button>
        </a>
    </c:if> 

<%@include file="footer.jsp"%>