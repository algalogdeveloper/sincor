
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="head.jsp"%>
<body>
	<%@ include file="menu.jsp"%>

	<div class="container-fluid">

		<h1>Inserção e Busca de Enderecos</h1>
		<div class="card mb-1">
			<div class="card-header fw-bold">Formulário de Cadastro</div>
			<div class="card-body">
                <c:if test="${not empty model.message}">
				<div class="alert alert-success p-1"><label class="fw-bold">${model.message}</label> 
					<i data-feather="check"></i>
				</div>
				</c:if>
				<form action="controller?invoke=EnviarEndereco" method="post">
					<div class="row">
						<div class="col-auto">
							<div class="form-floating mb-2">
								<input type="text" value="${model.codigo}" name="id"
									class="form-control" id="floatingInput"
									placeholder="name@example.com"> <label
									for="floatingInput">Codigo:</label>
							</div>
						</div>
						<div class="col-md-8">
							<div class="form-floating mb-2">
								<input type="text" class="form-control" name="nome"
									id="floatingInput" placeholder="name@example.com"> <label
									for="floatingInput">Nome do Endereço:</label>
							</div>

						</div>
					</div>
					<div class="form-floating mb-1">
						<select class="form-select" id="floatingSelect" name="rota"
							aria-label="Floating label select example">
							<option>Rota1</option>
							<option>Rota2</option>
							<option>Rota3</option>
							<option>Rota4</option>
							<option>Rota5</option>
						</select> <label for="floatingSelect">Qual rota o endereço
							pertence:</label>
					</div>
					<button class="btn btn-primary fw-bold">
						<i data-feather="send"></i> Enviar
					</button>
				</form>
			</div>

		</div>

		<div class="card">
			<div class="card-header fw-bold">Lista de Endereços</div>
			<div class="card-body">
				<table class="table table-striped table-hover" id="dataView">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nome</th>
							<th>Rota</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="e" items="${model.enderecos}">
							<tr>
								<td>${e.id}</td>
								<td>${e.nome}</td>
								<td>${e.rota}</td>
								<td><button data-feather="edit" title="Editar">Edit</button>
									<button data-feather="trash" title="Remover">Del</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>




	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>

	<script src="https://cdn.datatables.net/2.1.4/js/dataTables.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#dataView').DataTable();
		});
	</script>
	<script>
		feather.replace();
	</script>
</body>
</html>