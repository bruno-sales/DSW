<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Personagens</h1>
    </div>

    <a href="Servlet?t=listarPersonagens"><button id="findpass2">Listar Personagens</button></a>

    <div class="form2" id="FormListaPersonagens">
        <div class="forceColor2"></div>
        <div class="topbar2">
            <div class="spanColor2"></div>
            <c:forEach var="person" items ="${requestScope.personas}">
                <label name="nome" type="text" class="input">${person.id} - ${person.nome}</label>
            </c:forEach>                
        </div>
    </div>
    
        <c:if test="${requestScope.hasPriorPage}">
            <a href='Gogo?t=listarPersonagens&page=${requestScope.page-1}'>
                <button id="ant">Anterior</button></a> | 
        </c:if>
        <c:if test="${requestScope.hasNextPage}">
            <a href='Gogo?t=listarPersonagens&page=${requestScope.page+1}'>
                <button id="prox">Pr�xima</button></a> |
        </c:if> 
    
    <%@include file="footer.jsp"%>