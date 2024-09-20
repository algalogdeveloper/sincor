
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<%@ include file="head.jsp"%>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container-fluid">
		<strong><c:out value="${model.message}" /></strong>
		<div class="card">
			<div class="card-header">Lista de compras</div>
			<div class="card-body">
				<table class="table table-striped" style="text-align: left; font-family: monospace;" id="dataView">
					<thead>
						<tr>
							<th>Codigo</th>
							<th>Cliente</th>
							<th>Situação</th>
							<th>R$ Valor</th>
							<th>Lista de milhar</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="o" items="${model.compras}">
							<tr>
								<td>${o.id}</td>
								<td>${o.cliente.cliente}</td>
								<td>${o.pagamento}</td>
								<td><f:formatNumber type="currency" value=" ${o.valor}" /></td>
								<td>${o.itens}</td>
								<td><button class="btn btn-primary fw-bold">
										<i data-feather="edit"></i> Edit
									</button>
									<button class="btn btn-danger fw-bold" data-bs-del="${o.id}">
										<i data-feather="trash"></i> Del
									</button></td>
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
	      var tableViewEvent =  document.getElementById('dataView');
	      tableViewEvent.addEventListener('click', event => {
	 		 const button = event.target;
	 			if (button.getAttribute('data-bs-del') !== null){
	 				 $.ajax({
	 					  type: 'POST',
	 					  url: "sinfar?invoke=RemoverCompra",
	 					  data: {
	 					    param: button.getAttribute('data-bs-del')
	 					  },
	 					  success: function( result ) {	
	 						  if(result==='sucesso'){
	 							alert('Removido com sucesso!')
	 							location.reload();	 							
	 						  } else
	 							  alert('Não foi possível excluir?')						  
	 					  }
	 					});
	 			}
	 		 
	      });
	</script>
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