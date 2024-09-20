
<!DOCTYPE html>
<html>
<%@ include file="head.jsp" %>
<body>
	<%@ include file="menu.jsp" %>
	<div class="container-fluid">
		<h1 class="text-uppercase">Sistema de Inserção de Rifas</h1>
		<div class="row">
			<div class="col-md-3">
				<ul class="list-group">
					<li class="list-group-item active fw-bold" aria-current="true">Ações
						de Cadastro</li>
					<li class="list-group-item"><a
						class="btn btn-primary m-1 fw-bold"
						href="controller?invoke=ListarEnderecos"> <i data-feather="map-pin"></i> Lista de Endereços</a></li>
					<li class="list-group-item"><a
						class="btn btn-primary m-1 fw-bold"
						href="controller?invoke=ListarClientes"> <i data-feather="users"></i> Lista de Clientes</a></li>
					<li class="list-group-item"><a
						class="btn btn-primary m-1 fw-bold"
						href="controller?invoke=ListarFuncionarios"> <i data-feather="user-plus"></i> Lista de
							Funcionários</a></li>

				</ul>
			</div>

			<div class="col-md-3">
				<ul class="list-group">
					<li class="list-group-item active fw-bold" aria-current="true">Ações
						de Consultas</li>
					<li class="list-group-item"><a
						class="btn btn-primary m-1 fw-bold"
						href="controller?invoke=ListarCompras"><i data-feather="search"></i> Buscar Compras</a></li>
					<li class="list-group-item"><a
						class="btn btn-primary m-1 fw-bold"
						href="controller?invoke=ListarClientesPorEndereco"><i data-feather="shopping-bag"></i> Buscar
							Compras por Endereço</a></li>

				</ul>
			</div>

			<div class="col-md-3">
				<ul class="list-group">
					<li class="list-group-item active fw-bold" aria-current="true">Carrinho</li>

					<li class="list-group-item"><a
						class="btn btn-primary m-1 fw-bold"
						href="controller?invoke=CarrinhoItem"> <i data-feather="shopping-cart"></i> Novo Carrinho</a></li>
				</ul>
			</div>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script>
		feather.replace();
	</script>
</body>
</html>