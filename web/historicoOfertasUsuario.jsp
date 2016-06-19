<%@page import="Model.Oferta"%>
<%@page import="Model.DAO.PersonagemDAO"%>
<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Meu Histórico de Ofertas</h1>
    </div>

    <a href="Servlet?t=listarOfertas"><button id="btListarOfertas">Exibir Hist&oacute;rico</button></a>

    <div class="form2" id="FormListaOfertas">
        <div class="forceColor2"></div>
        <div class="topbar2">
            <div class="spanColor2"></div>
            <%                PersonagemDAO pDao = new PersonagemDAO();

            %>
            <c:forEach var="historico_Ofertas" items ="${requestScope.historicoOfertas}">
                <label name="nome" type="text" class="input">
                    
                    <% Oferta test = (Oferta) pageContext.getAttribute("historico_Ofertas");

                        String nomePersona = pDao.getPersonagemPorId(test.getIdPersonagem()).getNome();
                        String tipoOferta = test.getTipoOferta().name();
                        String statusOferta = test.getStatus().name();
                    %> 

                    <h4 id="tituloTipoOferta"><%=tipoOferta%></h4><br>
                    Personagem: <%=nomePersona%>&nbsp;&nbsp;|&nbsp;
                    Valor: R$ ${oferta.valor}&nbsp;&nbsp;|&nbsp;Quantidade: ${oferta.quantidade}<br> 
                    Data: ${oferta.data.toString("dd/MM/yyyy HH:mm")}&nbsp;&nbsp;|&nbsp;
                    Status:  <%=statusOferta%>
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
            <button id="prox">Próxima</button></a> |
    </c:if> 
    <%@include file="footer.jsp"%>