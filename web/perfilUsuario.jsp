<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Meu Perfil</h1>
    </div>
    
        <form name="formularioCadastro" action="Servlet?t=cadastrarUsuario" method="POST">
        <div class="form2" id="FormPerfilUsuario">
            <div class="forceColor2"></div>
            <div class="topbar2">
                <div class="spanColor2"></div>
                <label name="nome" type="text" class="input">Nome Usuário</label>
                <label name="telefone" type="text" class="input">Telefone (XX)XXXX-XXXX</label>
                <label name="cpf" type="text" class="input">CPF XXX.XXX.XXX-XX</label>
                <label name="email" type="text" class="input">email@exemplo.com.br</label>
                <label name="senha" type="password" class="input">*****</label>
                <label name="foto" type="file" class="input" id="imagem">Sua imagem!</label>
            </div>
        </div>
    </form>


    <a href="login.jsp"><button id="btAlterarPerfil">Alterar Perfil</button></a>
    
<%@include file="footer.jsp"%>
    