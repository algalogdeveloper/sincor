
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<%@ include file="head.jsp"%>
<body>
	<%@ include file="menu.jsp"%>
	<div class="container-fluid">
		<h1>Buscar Clientes por Enderecos</h1>
		<div class="card mb-1">
			<div class="card-header">Endereço:</div>
			<div class="card-body">
				<c:if test="${not empty model.message}">
					<div class="alert alert-success p-1">
						<span class="m-1"><c:out value="${model.message}" /></span>
					</div>
				</c:if>
				<form action="controller?invoke=ListarClientesPorEndereco"
					method="post">

					<div class="form-floating mb-1">
						<select class="form-select" id="floatingSelect" name="endereco"
							aria-label="Floating label select example">
							<c:forEach var="e" items="${model.enderecos}">
								<option value="${e.id}">${e.nome}</option>
							</c:forEach>
						</select> <label for="floatingSelect">Todos endereços com cliente:</label>
					</div>
					<button class="btn btn-primary fw-bold">
						<i data-feather="search"></i> Buscar
					</button>
				</form>
			</div>
		</div>

		<div class="card">
			<div class="card-header">Lista de compras por endereço:</div>
			<div class="card-body">
				<table
					class="table table-striped table-hover fw-bold" style="text-align: left; font-family: monospace;"  id="dataView">
					<thead>
						<tr>
							<th>Codigo</th>
							<th>Cliente</th>
							<th>Identificador</th>
							<th>Data do Registro</th>
							<th>Números</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="e" items="${model.clientes}">
							<tr>
								<td>${e.id}</td>
								<td>${e.cliente}</td>
								<td>${e.identificador}</td>
								<td>${e.dataRegistro}</td>
								<td>${e.compras}</td>
								<td><button style="text-align: center;" class=""
										data-feather="edit" data-bs-toggle="modal"
										data-bs-target="#modelClientesCompras"
										data-bs-editar="${e.id}">Edit</button>
									<button data-feather="trash"								
									data-bs-del="${e.id}" >Del</button>
									<button data-feather="list"></button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<%@ include file="modelclientecompras.jsp"%>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
	<script src="https://cdn.datatables.net/2.1.4/js/dataTables.js"></script>
	<script>
	const exampleModal = document.getElementById('modelClientesCompras');	
	exampleModal.addEventListener('show.bs.modal', event => {		
		const button = event.relatedTarget	;	
		  $.getJSON( "sinfar?invoke=BuscarCompraDoCliente&param="+button.getAttribute("data-bs-editar"), {
			    tags: "mount rainier",
			    tagmode: "any",
			    format: "json"
		   }) .done(function(data) {
			   $("#dados").text("");
		    	  var novaLinha;
			      $.each(data, function(i,item ) {
			    	  $.each(item.itens, function(j,elem ) {
			    		  	novaLinha += "<tr>"
				    	    novaLinha += "<td>" + elem.id + "</td>";
				    	    novaLinha += "<td>" + elem.milhar.numero +"</td>";
	                        novaLinha += "<td>" + "<input id='codigo"+elem.milhar.numero +"'  class='form-control' data-bs-target='#dataTabela' data-bs-item='"+elem.id+"'    data-bs-value='"+elem.milhar.numero +"' value='"+elem.milhar.numero +"' type='number'>"+"</td>";
	                        novaLinha += "</tr>"
			    	  });
			    	  $("tbody#dados").html(novaLinha);
			      });
                  
		   });		
		});	
	</script>
	<script>
	const updateTable = document.getElementById('dataTabela');		
	updateTable.addEventListener('input', e => {		
		const component = e.target	;
		var update = updateTable.querySelector("#codigo"+component.getAttribute("data-bs-value"))
	    var itemValue = component.getAttribute("data-bs-item");
		if(update.value.length===4){
	    	$.ajax({
				  type: 'POST',
				  url: "sinfar?invoke=UpdateItemNumero",
				  data: {
				    param: update.value,
				    param2:itemValue
				  },
				  success: function( result ) {	
					  if(result==='sucesso')
						location.reload();
					  else
						  $("#mensagem").text('Numero não esta disponível')						  
				  }
				});
	    }
	});
	</script>
	<script>
	const BTE = document.getElementById('dataView');	
	BTE.addEventListener('click', ev => {
		const obter = ev.target	;
		if(obter.getAttribute("data-bs-del")!==null)
			alert('Implementar');
	});
	</script>
	
	
	<script>
		$(document).ready(function() {
			$('#dataView').DataTable();
		});
	</script>
	<script>
	 var input_buscar = document.getElementById('buscar');
	 var dataTabela = document.getElementById('dados');
	 input_buscar.addEventListener('keyup',()=>{
		var linhas =  dataTabela.getElementsByTagName('tr');
		for (var l in linhas){
		    if(true===isNaN(l)) continue;
		    var conteudoLinha = linhas[l].innerHTML;
		    if(true===conteudoLinha.includes($("#buscar").val()))
		    	linhas[l].style.display=''
		    	else
		    	linhas[l].style.display='none'		    		
		}
	 });	
	</script>
	<script>
		feather.replace();
	</script>
</body>
</html>