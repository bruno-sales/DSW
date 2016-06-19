<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Ofertas</h1><!--Dividir depois pra compra e venda-->
    </div>
    
    <a href="Servlet?t=listarOfertasVenda"><button id="btOfertasCompra">Listar Ofertas de Compra</button></a>

    <div class="form2" id="FormListaOfertasCompra">
        <div class="forceColor2"></div>
        <div class="topbar2">
            <div class="spanColor2"></div>
            <c:forEach var="oferta_Compra" items ="${requestScope.ofertaCompra}">
                <label name="nome" type="text" class="input">${oferta_Compra.id} - ${oferta_Compra.nome}</label>
            </c:forEach>                
        </div>
    </div>
    
    <a href="Servlet?t=listarOfertasCompra"><button id="btOfertasVenda">Listar Ofertas de Venda</button></a>

    <div class="form2" id="FormListaOfertasVenda">
        <div class="forceColor2"></div>
        <div class="topbar2">
            <div class="spanColor2"></div>
            <c:forEach var="oferta_Venda" items ="${requestScope.ofertasVenda}">
                <label name="nome" type="text" class="input">${oferta_Venda.id} - ${oferta_Venda.nome}</label>
            </c:forEach>                
        </div>
    </div>
    
        <c:if test="${requestScope.hasPriorPage}">
            <a href='Gogo?t=listarOfertasCompra&page=${requestScope.page-1}'>
                <button id="ant">Anterior</button></a> | 
        </c:if>
        <c:if test="${requestScope.hasNextPage}">
            <a href='Gogo?t=listarOfertasCompra&page=${requestScope.page+1}'>
                <button id="prox">Próxima</button></a> |
        </c:if> 
                
        <c:if test="${requestScope.hasPriorPage}">
            <a href='Gogo?t=listarOfertasVenda&page=${requestScope.page-1}'>
                <button id="ant">Anterior</button></a> | 
        </c:if>
        <c:if test="${requestScope.hasNextPage}">
            <a href='Gogo?t=listarOfertasVenda&page=${requestScope.page+1}'>
                <button id="prox">Próxima</button></a> |
        </c:if> 
    
    <%@include file="footer.jsp"%>