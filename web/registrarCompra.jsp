<%@page import="Model.Personagem"%>
<%@page import="java.util.List"%>
<%@page import="Model.DAO.PersonagemDAO"%>
<%@page import="Model.Oferta"%>
<%@page import="Model.DAO.UsuarioDAO"%>
<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Registrar Interesse de Compra</h1>
    </div>

    <form name="formularioRegistrarCompra" action="Servlet?t=registrarCompra" method="POST">   
        <div class="form2" id="FormListaOfertas">
            <div class="forceColor2"></div>
            <div class="topbar2">
                <div class="spanColor2" id=""></div>
                <%                    
                out.println("<select name='personagem' id='personagensDD' class='input'"
                        + "style='color: black;'>");
                    PersonagemDAO pDao = new PersonagemDAO();
                    List<Personagem> lista = pDao.lista(0, 1100000);

                    for (Personagem p : lista) {
                        out.println("<option value=\"" + p.getId() + "\" >" + p.getNome() + "</option>");
                    }
                %>

                <input name="qtd" type="text" class="input" placeholder="Quantidade"
                       required pattern="[0-9]"/>
                
                <input name="valor" type="text" class="input" placeholder="Valor unitário R$ X.XX"
                       required pattern="[0-9]{1}.[0-9]{2}"/>

                <button class="submit" id="submitAplicarAlteracoes">Registrar</button>
            </div>
        </div>
    </form>

                  <div class="errorMsgTrocarSenha">
        <!-- Carregar mensagem de erro, se houver -->
        <c:if test="${not empty param.mensagem}">
            <c:out value="${param.mensagem}"/>
        </c:if>
    </div>
                
    <%@include file="footer.jsp"%>