<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="/WEB-INF/simplemvc.tld" prefix="mvc"%>

    <div class="menu">
        <div class="mainmenu clearfix">
            <h1 class="menuitem">Login</h1>
        </div>
    </div>
<mvc:error/>
<mvc:notice/>

    <form method ="post" action="Login/login.do" class="login">
        <div class="form">
        <div class="forceColor"></div>
        <div class="topbar">
        <div class="spanColor"></div>
            <input name="email" type="text" class="input" id="password" placeholder="E-mail"/>
            <input name="pwd" type="password" class="input" id="password" placeholder="Senha"/>
        </div>
                
            <button class="submit" id="submit" >Entrar</button>
        </div>        
        
        <button id="findpass"><a href="/Gogo/retrieve.do">Esqueceu sua senha?</a></button> <!--numseiqualéo.do-->
        <button id="linkCadastro"><a href="/Gogo/retrieve.do">Cadastrar-se</a></button>
        
    </form>
        
<%@include file="footer.jsp"%>
