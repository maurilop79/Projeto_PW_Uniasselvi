<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Jogos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar jogo</h1>
	<form name="frmJogo" action="update">
		<table>
			<tr>
				<td><input type="text" name="idjogo" id="caixa2" readonly
						value="<%out.print(request.getAttribute("idjogo"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="Caixa1"
						value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="plataforma" class="Caixa1"
						value="<%out.print(request.getAttribute("plataforma"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="desenvolvedor" class="Caixa1"
						value="<%out.print(request.getAttribute("desenvolvedor"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>