<%@page import="Model.LancamentosDinheiros"%>
<%@page import="Model.LancamentosPersonagens"%>
<%@page import="Model.DAO.PersonagemDAO"%>
<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Meu Extrato de Conta Corrente</h1>
    </div>

    <a href="Servlet?t=extratoContaCorrente&tipo=dinheiro"><button id="btExtratoDinheiro">Exibir Extrato Dinheiro</button></a>
    <a href="Servlet?t=extratoContaCorrente&tipo=personagem"><button id="btExtratoPersonagem">Exibir Extrato Personagem</button></a>

    <div class="form2" id="extratoPersonagem">
        <div class="forceColor2"></div>
        <div class="topbar2">
            <div class="spanColor2"></div>
            <%                PersonagemDAO pDao = new PersonagemDAO();

            %>
            <c:if test="${requestScope.tipo == 'dinheiro'}">
                <label name="nome" type="text" class="input">
                    Saldo Atual: R$ ${requestScope.saldo}
                </label>
                <c:forEach var="extrato_Cc" items ="${requestScope.extrato}">
                    <label name="nome" type="text" class="input">
                        <% LancamentosDinheiros lancamento = (LancamentosDinheiros) pageContext.getAttribute("extrato_Cc");
                        %> 

                        <h4 id="tituloTipoOferta"><%= lancamento.getOperacao().name()%></h4><br>                        
                        Valor: R$ ${extrato_Cc.valor}&nbsp;&nbsp;|&nbsp;Data: ${extrato_Cc.data.toString("dd/MM/yyyy HH:mm")}<br> 
                        Historico:  ${extrato_Cc.historico}
                    </label>
                </c:forEach>      
            </c:if> 
            <c:if test="${requestScope.tipo == 'personagem'}">
                <c:forEach var="extrato_Cc" items ="${requestScope.extrato}">
                    <label name="nome" type="text" class="input">
                        <% LancamentosPersonagens persona = (LancamentosPersonagens) pageContext.getAttribute("extrato_Cc");

                            String nomePersona = pDao.getPersonagemPorId(persona.getIdPersonagem()).getNome();
                        %> 

                        <h4 id="tituloTipoOferta"><%= persona.getOperacao().name()%></h4><br>                        
                        Data: ${extrato_Cc.data.toString("dd/MM/yyyy HH:mm")}<br> 
                        Personagem: <%=nomePersona%>&nbsp;&nbsp;|&nbsp;Quantidade: ${extrato_Cc.quantidade}
                        &nbsp;&nbsp;|&nbsp;Preço Unitário: ${extrato_Cc.precoUnitario}<br> 
                        Historico:  ${extrato_Cc.historico}
                    </label>
                </c:forEach>      
            </c:if>
        </div>
    </div>

    <c:if test="${requestScope.hasPriorPage}">
        <a href='Gogo?t=extratoContaCorrente&page=${requestScope.page-1}&tipo=${requestScope.tipo}'>
            <button id="ant2">Anterior</button></a> | 
        </c:if>
        <c:if test="${requestScope.hasNextPage}">
        <a href='Gogo?t=extratoContaCorrente&page=${requestScope.page+1}&tipo=${requestScope.tipo}'>
            <button id="prox2">Próxima</button></a> |
    </c:if> 
    <%@include file="footer.jsp"%>