
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="head.jsp"%>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container-fluid">

		<div class="card">
			<div class="card-header">FORMULÁRIO DE CADASTRO:</div>
			<div class="card-body">
				<div class="alert alert-success fw-bold p-1">
					<c:out value="${model.message}" />
				</div>
				<form id="formEdit" action="controller?invoke=EnviarCliente"
					method="post">
					<div class="row">
						<div class="col-md-2">
							<div class="form-floating mb-2">
								<input type="number" name="id" value="${model.codigo}"
									class="form-control" id="id" readonly="readonly"
									placeholder="Codigo"> <label for="floatingInput">Codigo:</label>
							</div>
						</div>
						<div class="col-md-8">
							<div class="form-floating mb-2">
								<input type="text" name="cliente" class="form-control" id="nome"
									placeholder="name@example.com"> <label
									for="floatingInput">Nome:</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">
							<div class="form-floating mb-2">
								<input type="number" name="ident" class="form-control"
									id="ident" placeholder="Identificador"> <label
									for="ident">Identificador:</label>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-floating mb-2">
								<input type="date" name="dtr" class="form-control" id="drc"
									placeholder="Data de registo do cliente?"> <label
									for="floatingPassword">Data de Registro:</label>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-floating mb-2">
								<select name="endereco" class="form-select" id="endereco"
									aria-label="Floating label select example">
									<option selected>Todos Endereços</option>
									<c:forEach var="e" items="${model.enderecos}">
										<option value="${e.id}">${e.nome}</option>
									</c:forEach>
								</select> <label for="floatingSelect">Endereços:</label>
							</div>
						</div>
					</div>



					<button class="btn btn-primary fw-bold">
						<i data-feather="send"></i> ENVIAR
					</button>
					<button type="reset" class="btn btn-danger fw-bold">
						<i data-feather="x-circle"></i> LIMPAR
					</button>
				</form>
			</div>
		</div>

		<div class="card">
			<div class="card-header">Lista de Clientes</div>
			<div class="card-body">

				<table class="table table-striped border" style="text-align: left; font-family: monospace;" id="dataView">
					<thead>
						<tr>
							<th>Codigo</th>
							<th>Cliente</th>
							<th>Identificador</th>
							<th>Data do Registro</th>
							<th>Endereço</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="c" items="${model.clientes}">
							<tr>
								<td>${c.id}</td>
								<td>${c.cliente}</td>
								<td>${c.identificador}</td>
								<td>${c.dataRegistro}</td>
								<td>${c.endereco.nome}</td>
								<td>
									<div class="btn-group" role="group" aria-label="Basic example">
										<button id="bte" class="btn btn-primary fw-bold"
											data-bs-toggle="show.bs.table" data-bs-target="#dataView"
											data-bs-whatever="${c.id}" data-bs-ident="${c.identificador}"
											data-bs-dr="${c.dataRegistro}"
											data-bs-endereco="${c.endereco.id}"
											data-bs-cliente="${c.cliente}">Editar</button>
										<button id="btr" 
										data-bs-remover="${c.id}"
										class="btn btn-danger fw-bold">Remover</button>
									</div>
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
	<script type="text/javascript">
	$(document).ready( function () {
	    $('#dataView').DataTable();
	} );
	</script>
	<script type="text/javascript">		
		const exampleModal = document.getElementById('dataView')
		var cod = document.getElementById('id')
		var nome = document.getElementById('nome')
		var ident = document.getElementById('ident')
		var reg = document.getElementById('drc')
		var endereco = document.getElementById('endereco')
		exampleModal.addEventListener('click', event => {
		 const button = event.target
		 cod.value = button.getAttribute('data-bs-whatever');
		 nome.value = button.getAttribute('data-bs-cliente');
		 ident.value = button.getAttribute('data-bs-ident');
		 reg.value = button.getAttribute('data-bs-dr');
		 endereco.value = button.getAttribute('data-bs-endereco');
		 if ( button.getAttribute('data-bs-remover') !== null){
			 
			 
			 $.ajax({
				  type: 'POST',
				  url: "sinfar?invoke=RemoverCliente",
				  data: {
				    param: button.getAttribute('data-bs-remover')
				  },
				  success: function( result ) {	
					  if(result==='sucesso'){
						  alert('Removido com sucesso!')
						location.reload();
						
					  } else
						  $("#mensagem").text('Numero não esta disponível')						  
				  }
				});
		 }
		});		
	</script>
	<script>
      feather.replace();
    </script>
</body>
</html>