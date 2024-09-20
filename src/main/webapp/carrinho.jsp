
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="head.jsp"%>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container-fluid">
		<h1 class="h1">Carrinho</h1>
		<div class="card">
			<div class="card-header">Loja Virtual de Milhar</div>
			<div class="card-body">
				<strong class="card-title"> Milhar: <c:if
						test="${not empty erro }">
						<span class="text-danger p-1">${erro}</span>
					</c:if>
				</strong>
				<form action="controller?invoke=EnviarItem" method="post">
					<div class="form-floating mb-1">
						<input name="numero" type="number" maxlength="4" min="0"
							max="9999" class="form-control fs-6"
							placeholder="Leave a comment here" id="numero">
					</div>
					<c:if test="${not empty meuCarrinho}">
						<a href="controller?invoke=LimparItemCarrinho"
							class="btn btn-danger fw-bold"><i data-feather="eye-off"></i>
							Limpar Carrinho</a>
						<hr>
					</c:if>
				</form>
				<div class="row">
					<span class="fw-bold">Quantidade de elementos no
						carrinho:${meuCarrinho.size()}</span>
				</div>
				<c:forEach var="c" items="${meuCarrinho}">
					<a class="btn btn-light"
						href="controller?invoke=RemoveItemCarrinho&param=${c}"><span
						class="fw-bold">${c}</span></a>
				</c:forEach>
				<br>
			</div>
		</div>

		<div class="card">
			<div class="card-header">Dados do Cliente</div>
			<div class="card-body">
				<form action="controller?invoke=EnviarCompraCliente" method="post">
					<c:if test="${not empty message}">
						<div class="fw-bold alert alert-success p-1">${message}
							<i data-feather="check"></i>
						</div>
					</c:if>
					<div class="row">
						<div class="col-md-4">
							<div class="mb-1">
								<label for="exampleFormControlInput1" class="form-label fw-bold">Nome:</label>
								<input type="text" name="cliente" class="form-control"
									id="exampleFormControlInput1" placeholder="Nome do cliente?">
							</div>
						</div>
						<div class="col-md-8">
							<div class="mb-1">
								<label for="exampleFormControlInput1" class="form-label fw-bold">Endereço:</label>
								<select class="form-select" name="endereco"
									aria-label="Default select example">
									<option selected>Todos endereços</option>
									<c:forEach var="e" items="${enderecos}">
										<option value="${e.id}"
											<c:if test="${e.id eq modelDados.endereco}"> selected </c:if>>${e.nome}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-md-3">
							<div class="mb-1">
								<label for="exampleFormControlInput1" class="form-label fw-bold">R$
									Valor:</label> <input type="number" class="form-control" name="valor"
									id="exampleFormControlInput1" value="${modelDados.valor}"
									placeholder="Valor">
							</div>
						</div>
						<div class="col-md-3">
							<div class="mb-1">
								<label for="exampleFormControlInput1" class="form-label fw-bold">Quantidade
									no canhoto:</label> <input type="number" class="form-control"
									value="${modelDados.qtd}" name="qtd"
									id="exampleFormControlInput1" placeholder="Qtd">
							</div>
						</div>
						<div class="col-md-3">
							<div class="mb-1">
								<label for="exampleFormControlInput1" class="form-label fw-bold">R$
									Desconto:</label> <input type="number" class="form-control"
									name="desconto" id="exampleFormControlInput1"
									placeholder="Desconto">
							</div>
						</div>
						<div class="col-md-3">
							<div class="mb-1">
								<label for="exampleFormControlInput1" class="form-label fw-bold">Situação:</label>
								<select class="form-select" name="situacao"
									aria-label="Default select example">
									<option selected="selected" value="Emaberto">Em aberto</option>
									<option>Concluido</option>
									<option>Analise</option>
									<option>Cancelado</option>
								</select>
							</div>
						</div>
					</div>
					<button class="btn btn-primary fw-bold">
						<i data-feather="send"></i> Enviar
					</button>

					<button class="btn btn-danger fw-bold">
						<i data-feather="x-circle"></i> Limpar
					</button>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
		var milhar = document.getElementById('numero');
		milhar.addEventListener('input', function(event) {
			if (milhar.value.length === 4) {
				window.location.href = 'controller?invoke=EnviarItem&numero='
						+ milhar.value;
			}
		});
		milhar.focus();
	</script>
	<script>
		feather.replace();
	</script>
</body>
</html>