<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">
</head>

<body>
	<div class="container-fluid">
		<h1>Autorizar acesso do Funcionário</h1>


		<div class="card">
			<div class="card-header">Autorizar acesso do Funcionário</div>
			<div class="card-body">
				<h5 class="card-title">Dados restritos</h5>
				<c:if test="${not empty model.message}">
				<p class="alert alert-danger p-1">${model.message}</p>
				</c:if>
				<form action="logar" method="post">
					<div class="mb-3">
						<label for="exampleFormControlInput1" class="form-label">Login:
						</label> <input type="text" class="form-control" name="login"
							id="exampleFormControlInput1" placeholder="Login">
					</div>
					<div class="mb-3">
						<label for="exampleFormControlInput1" class="form-label">Senha:
						</label> <input type="password" class="form-control" name="senha"
							id="exampleFormControlInput1" placeholder="Senha">
					</div>

					<button class="btn btn-primary fw-bold">Logar</button>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>
</html>