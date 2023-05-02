<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
@SuppressWarnings("unchecked")
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("jogos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Cadastro de Jogos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Cadastro de Jogos</h1>
	<a href="novo.html" class="Botao1">Novo jogo</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Plataforma</th>
				<th>Desenvolvedor</th>
				<th>Opções</th>

			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getIdjogo()%></td>
				<td><%=lista.get(i).getNome()%></td>
				<td><%=lista.get(i).getPlataforma()%></td>
				<td><%=lista.get(i).getDesenvolvedor()%></td>
				<td><a href="select?idjogo=<%=lista.get(i).getIdjogo()%>" class="Botao1">Editar</a>
					<a href="javascript: confirmar(<%=lista.get(i).getIdjogo()%>)" class="Botao2">Excluir</a>
				</td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>