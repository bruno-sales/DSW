<%@page import="Model.Oferta"%>
<%@page import="Model.DAO.PersonagemDAO"%>
<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Minhas Ofertas</h1>
    </div>

    <a href="Servlet?t=listarOfertas"><button id="findpass2">Listar Ofertas</button></a>

    <div class="form2" id="FormListaPersonagens">
        <div class="forceColor2"></div>
        <div class="topbar2">
            <div class="spanColor2"></div>
            <%                PersonagemDAO pDao = new PersonagemDAO();

            %>
            <c:forEach var="oferta" items ="${requestScope.ofertas}">
                <label name="nome" type="text" class="input">
                    
                    <% Oferta test = (Oferta) pageContext.getAttribute("oferta");

                        String nomePersona = pDao.getPersonagemPorId(test.getIdPersonagem()).getNome();
                        String tipoOferta = test.getTipoOferta().name();
                        String statusOferta = test.getStatus().name();
                    %> 

                    <%=tipoOferta%><br>
                    Personagem: <%=nomePersona%> - Valor: R$ ${oferta.valor}
                    - Data: ${oferta.data.toString("dd/MM/yyyy HH:mm")} - Quantidade: ${oferta.quantidade}
                    - Status:  <%=statusOferta%>
                    </label>
                </c:forEach>                
        </div>
    </div>

    <c:if test="${requestScope.hasPriorPageCompra}">
        <a href='Gogo?t=listarOfertas&page=${requestScope.page-1}'>
            <button id="ant">Anterior</button></a> | 
        </c:if>
        <c:if test="${requestScope.hasNextPageCompra}">
        <a href='Gogo?t=listarOfertas&page=${requestScope.page+1}'>
            <button id="prox">Pr�xima</button></a> |
    </c:if> 
    <%@include file="footer.jsp"%>