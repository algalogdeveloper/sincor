
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="head.jsp"%>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container-fluid">

		<div class="card mb-1">
			<div class="card-header">Formulário de Cadastro</div>
			<div class="card-body">
				<c:if test="${not empty model.message}">
					<div class="alert alert-success fw-bold">
						<c:out value="${model.message}" />
					</div>
				</c:if>
				<form action="controller?invoke=EnviarFuncionario" method="post">
					<div class="row">
						<div class="col-md-1">
							<div class="form-floating mb-2">
								<input type="number" value="${model.codigo}"
									class="form-control" name="codigo" id="floatingInput"
									placeholder="name@example.com"> <label
									for="floatingInput">Codigo:</label>
							</div>

						</div>
						<div class="col-md-7">
							<div class="form-floating mb-2">
								<input type="text" class="form-control" name="nome"
									id="floatingInput" placeholder="name@example.com"> <label
									for="floatingInput">Nome:</label>
							</div>
						</div>

						<div class="col-md-4">

							<div class="form-floating mb-1">
								<select class="form-select" id="floatingSelect" name="permissao"
									aria-label="Floating label select example">

									<option>USUARIO</option>
									<option>PROPRIETARIO</option>
									<option>VENDEDOR</option>
									<option>CLIENTE</option>
									<option>COBRADOR</option>
								</select> <label for="floatingSelect">Permissões do Usuário:</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="form-floating mb-2">
								<input type="text" class="form-control" name="login"
									id="floatingInput" placeholder="name@example.com"> <label
									for="floatingInput">Login:</label>
							</div>

						</div>
						<div class="col-md-4">
							<div class="form-floating mb-2">
								<input type="text" class="form-control" name="senha"
									id="floatingInput" placeholder="name@example.com"> <label
									for="floatingInput">Senha:</label>
							</div>

						</div>
						<div class="col-md-4">
							<div class="form-floating mb-2">
								<input type="text" class="form-control" name="cfsenha"
									id="floatingInput" placeholder="name@example.com"> <label
									for="floatingInput">Confirmar Senha:</label>
							</div>

						</div>
					</div>
					<button class="btn btn-primary fw-bold">
						<i data-feather="send"></i> Enviar
					</button>
				</form>
			</div>
		</div>

		<div class="card">
			<div class="card-header">Lista de Funcionários</div>
			<div class="card-body">

				<table class="table table-striped table-hover" id="dataView"
					style="text-align: left; font-family: monospace;">
					<thead>
						<tr>
							<th>Codigo</th>
							<th>Nome</th>
							<th>Permissão</th>
							<th>Login</th>
							<th>Senha</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="e" items="${model.funcionarios}">
							<tr>
								<td>${e.id}</td>
								<td>${e.nome}</td>
								<td>${e.permissao}</td>
								<td>${e.login}</td>
								<td>${e.senha}</td>
								<td>
									<button data-feather="edit"></button>
									<button data-feather="trash" onclick="remover(${e.id})"></button>
								</td>
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
	<script>
	 function remover(param) {
		
		var view = confirm("Deseja excluir funcionário?");
		if(view){
			$.ajax({
				  type: 'POST',
				  url: "sinfar?invoke=RemoverFuncionario",
				  data: {
				    param: param
				  },
				  success: function( result ) {					  
					location.reload();	  
				  }
				});
		}
	}
	
	</script>
	<script>
		$(document).ready(function() {
			$('#dataView').DataTable();
		});
	</script>
	<script>
		feather.replace();
	</script>
</body>
</html>