<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Usu&aacute;rios</h1>
    </div>
<!--
    <nav id="colorNav">
	<ul>
		<li class="green">
			<a href="#" class="icon-home"></a>
			<ul>
				<li><a href="#">Dropdown item 1</a></li>
				<li><a href="#">Dropdown item 2</a></li>
				<!-- More dropdown options -->
<!--			</ul>
		</li>

		<!-- More menu items -->
<!--
	</ul>
</nav>-->
    
<a href="Servlet?t=listarUsuarios"><button id="findpass2">Listar Usu&aacute;rios</button></a>

    <div class="form2" id="FormListaPersonagens"> <!--Por enquanto ficam na mesma posição-->
        <div class="forceColor2"></div>
        <div class="topbar2">
            <div class="spanColor2"></div>
            <c:forEach var="user" items ="${requestScope.usuarios}"> <!--É assim?-->
                <label name="nome" type="text" class="input">${user.id} - ${user.nome}</label>
            </c:forEach>                
        </div>
    </div>

        <c:if test="${requestScope.hasPriorPage}">
            <a href='Gogo?t=listarUsuarios&page=${requestScope.page-1}'>
                <button id="ant">Anterior</button></a> | 
        </c:if>
        <c:if test="${requestScope.hasNextPage}">
            <a href='Gogo?t=listarUsuarios&page=${requestScope.page+1}'>
                <button id="prox">Próxima</button></a> |
        </c:if> 
    
    <%@include file="footer.jsp"%>