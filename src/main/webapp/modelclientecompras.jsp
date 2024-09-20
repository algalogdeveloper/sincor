
<div class="modal fade" id="modelClientesCompras" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title text-uppercase" id="exampleModalLabel">Atualizar
					milhar do cliente</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<span class="text text-danger p-1 fw-bold" id="mensagem">Atualize
					apenas o milhar digitado errado do cliente </span>
				<div class="form-floating mb-3">
					<input type="text" class="form-control" id="buscar"
						placeholder="Buscar numero"> <label for="floatingInput">Buscar</label>
				</div>				
				<table class="table table-striped border" id="dataTabela">
					<thead>
						<tr>
							<th scope="col">Codigo</th>
							<th scope="col">Numero</th>
							<th scope="col">Alterar</th>
						</tr>
					</thead>
					<tbody id="dados">
					</tbody>
				</table>
			</div>

		</div>
	</div>
</div>