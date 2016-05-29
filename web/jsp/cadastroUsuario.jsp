<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="header.jsp"%>
<%@ page import="java.util.Enumeration" %>

<%@taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@taglib uri="/WEB-INF/fmt.tld" prefix="fmt"%>
<%@taglib uri="/WEB-INF/simplemvc.tld" prefix="mvc"%>

<h2>Cadastro de Usuário</h2>

<mvc:error/>
<mvc:notice/>

<c:set var="usuario" value="${requestScope.usuario}"/><%--faz sentido?--%>

<%--int id; String foto; boolean administrador;--%> 

<form name="formularioCadastro" action="ALGUMACOISA.DO/JAVA"><%--o que vai aqui?--%>
	<input type="hidden" name="id" value="${usuario.idUsuario}"/><%--como settar o id?--%>

	<table>
	<tr>
	  <th align="right">
		Nome:
	  </th>
	  <td align="left">
		<input type="text" name="nome" value="${usuario.nome}" pattern="[a-z\s]+$" size="50"/>
	  </td>
	</tr>

	<tr>
	  <th align="right">
		Telefone:
	  </th>
	  <td align="left">
		<input type="text" name="telefone" value='<fmt:formatNumber value="${usuario.telefone}" pattern="\([0-9]{2}\) [0-9]{4,6}-[0-9]{3,4}$"/>' size="10"/>
	  </td>
	</tr>

	<tr>
	  <th align="right">
		CPF:
	  </th>
	  <td align="left">
		<input type="text" name="cpf" value="<fmt:formatNumber value="${usuario.cpf}"
                       pattern="\d{3}\.\d{3}\.\d{3}-\d{2}"/>" size="15"
                       <%--    title="Digite o CPF no formato nnn.nnn.nnn-nn"--%>/>
                <script type="text/javascript">
                    function valida() {
                       if (document.formularioCadastro.cpf.validity.patternMismatch) {
                          alert("O CPF está incorreto");
                       } else {
                          alert("O CPF está correto");
                       }
                       return false;
                    }
                </script>
	  </td>
	</tr>	

        <tr>
	  <th align="right">
		E-mail:
	  </th>
	  <td align="left">
		<input type="text" name="email" value="<fmt:formatNumber value="${usuario.email}" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"/>" size="30"/>
	  </td>
	</tr>	
        
        <tr>
	  <th align="right">
		Senha:
	  </th>
	  <td align="left">
		<input type="text" name="pwd" value="<fmt:formatNumber value="${usuario.senha}" pattern=""/>" size="12"/>
	  </td>
	</tr>	
        
	<tr>
	  <td colspan="2" align="right">
		<input type="submit"/>
	  </td>									
	</tr>
	</table>					
</form>

<p>
  <a href="/Gogo/retrieve.do">Retorna para a lista</a>
</p>

<%@include file="footer.jsp"%>