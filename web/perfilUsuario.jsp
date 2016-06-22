<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>

<div class="menu2">
    <div class="mainmenu2 clearfix">
        <h1 class="menuitem2">Meu Perfil</h1>
    </div>
    
        
        <div class="form2" id="FormPerfilUsuario">
            <div class="forceColor2"></div>
            <div class="topbar2">
                <div class="spanColor2"></div>
                <label name="nome" type="text" class="input" id="inputPerfilUsuario">Nome: <%= user.getNome()%></label>
                <label name="telefone" type="text" class="input" id="inputPerfilUsuario">Telefone: <%= user.getTelefone()%></label>
                <label name="cpf" type="text" class="input" id="inputPerfilUsuario">CPF: <%= user.getCpf()%></label>
                <label name="email" type="text" class="input" id="inputPerfilUsuario">Email: <%= user.getEmail()%></label>
                <label name="foto" type="file" class="input" id="inputImagem">
                    <img src="Gogo?t=carregarFotoUsuario&userId=<%= user.getIdUsuario()%>" style="width: 210px;height: 255px"/>                    
                </label>
            </div>
        </div>


    <a href="editarUsuario.jsp"><button id="btAlterarPerfil">Alterar Perfil</button></a>
    
<%@include file="footer.jsp"%>
    