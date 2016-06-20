<%@page import="Model.DAO.UsuarioDAO"%>
<%@page import="Model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	response.setHeader ("Cache-Control", "no-cache");
	response.setHeader ("Pragma", "no-cache");
	response.setDateHeader ("Expires", 0); 
%>

<html>
  <head>
	<title>Bolsa Gogo</title>
        
        <script src="http://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>

        <link href='http://fonts.googleapis.com/css?family=Raleway:300,200' rel='stylesheet' type='text/css'>
    
	<link rel="stylesheet" type="text/css" href="media/layout.css">
        <link rel="stylesheet" type="text/css" href="media/reset.css">
  </head>
	
  <body>
      
      <!-- Recuperar cookie do navegador do usuário -->
<%    String cookieUsuarioId = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                cookieUsuarioId = cookie.getValue();
            }
        }
    }
    //Se não existir usuário, redireciona para a página de login
    if (cookieUsuarioId == null) {
        response.sendRedirect("login.jsp");
    }
    
    int idUsuario = Integer.parseInt(cookieUsuarioId);
    
    Usuario user = new Usuario();
    UsuarioDAO userDao = new UsuarioDAO();
    
    user = userDao.getUsuarioPorId(idUsuario);
    
%>

<h3 class="boasVindas">Bem-vindo, <%= user.getNome()%> <br>
    Ultimo Login: <%= user.getUltimoLogin().toString("dd/MM/yyyy HH:mm") %></h3>
    
    <ul>
        <li>
            <a href="perfilUsuario.jsp"><button id="itemMenu" class="itemMenu1">Meu Perfil</button></a>
        </li>
        <li>
            <a href="editarUsuario.jsp"><button id="itemMenu" class="itemMenu2">Editar Perfil</button></a>
        </li>
        <li>
            <a href="trocarSenha.jsp"><button id="itemMenu" class="itemMenu3">Trocar Senha</button></a>
        </li>       
        <li>
            <a href="transferenciasBancarias.jsp"><button id="itemMenu" class="itemMenu4">Registrar Transferência</button></a>
        </li>     
        <li>
            <a href="registrarPersonagem.jsp"><button id="itemMenu" class="itemMenu5">Cadastrar Personagem</button></a>
        </li> 
        <li>
            <a href="registrarCompra.jsp"><button id="itemMenu" class="itemMenu6">Cadastrar Oferta de Compra</button></a>
        </li> 
        <li>
            <a href="registrarVenda.jsp"><button id="itemMenu" class="itemMenu7">Cadastrar Oferta de Venda</button></a>
        </li> 
        <li>
            <a href="Servlet?t=listarOfertas"><button id="itemMenu" class="itemMenu8">Minhas Ofertas</button></a>
        </li> 
        <li>
            <a href="extratoContaCorrente.jsp"><button id="itemMenu" class="itemMenu9">Meu Extrato</button></a>
        </li>        
        <li>
            <a href="Servlet?t=listarOfertas"><button id="itemMenu" class="itemMenu10">Cotação Histórica</button></a>
        </li>
        <li>
            <a href="Servlet?t=logoff"><button id="itemMenu" class="itemMenu11">Logoff</button></a>
        </li>
    </ul>