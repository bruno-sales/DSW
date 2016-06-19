<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2" id="tituloEditarUsuario">Editar Perfil</h1>
    </div>
    <form name="formularioEditarUsuario" action="Servlet?t=editarUsuario" method="POST" enctype="multipart/form-data">
        <div class="form2" id="FormEditarUsuario">
            <div class="forceColor2"></div>
            <div class="topbar2">
                <div class="spanColor2"></div>
                
                <input name="nome" type="text" class="input" id="inputEditarUsuario" placeholder="Nome"
                       pattern="[a-zA-Z\s]+$"/>
                <input name="telefone" type="text" class="input" id="inputEditarUsuario" placeholder="Telefone (XX)XXXX-XXXX"
                       pattern="\([0-9]{2}\)[0-9]{4,6}-[0-9]{3,4}$"/>
                <input name="cpf" type="text" class="input" id="inputEditarUsuario" placeholder="CPF XXX.XXX.XXX-XX"
                <!--input da foto-->
                <input name="foto" type="file" class="input" id="inputImagem" placeholder="Imagem atual!">
                
                <input name="listaOpcoesImgs" type="text" class="input" id="inputOpcoesImgs" placeholder="Op&ccedil;&otilde;es">
                <datalist id="opcoesImg">
                    <option value="Foto 1">
                    <option value="Foto 2">
                    <option value="Foto 3">
                    <option value="Foto 4">
                    <option value="Foto 5">
                </datalist>
            </div>
            <button class="submit" id="submitAplicarAlteracoes">Aplicar alterações</button>
        </div>
    </form>
    
    <div class="errorMsg">
        <!-- Carregar mensagem de erro, se houver -->
        <c:if test="${not empty param.mensagem}">
            <c:out value="${param.mensagem}"/>
        </c:if>
    </div>

    <a href="perfilUsuario.jsp"><button id="findpass2">Ir para Perfil</button></a>
    
    
<%@include file="footer.jsp"%>