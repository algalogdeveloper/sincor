<nav class="navbar navbar-dark navbar-expand-lg bg-primary mb-3">
		<div class="container-fluid">
			<a class="navbar-brand text-uppercase fw-bold" href="#">[SinFar]</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item dropdown "><a
						class="nav-link dropdown-toggle active fw-bold" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Cadastros </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item fw-bold" href="controller?invoke=ListarEnderecos"> <i
									data-feather="map-pin"></i>  Endereços</a></li>
							<li><a class="dropdown-item fw-bold" href="controller?invoke=ListarClientes"><i
									data-feather="user"></i> Clientes</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item fw-bold" href="controller?invoke=ListarFuncionarios"><i
									data-feather="users"></i> Funcionarios</a></li>
									
									<li><a class="dropdown-item fw-bold" href="controller?invoke=CarrinhoItem"><i
									data-feather="shopping-cart"></i> Novo Carrinho</a></li>
						</ul></li>
						
						<li class="nav-item dropdown "><a
						class="nav-link dropdown-toggle active fw-bold" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Consultas </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item fw-bold" href="controller?invoke=ListarClientesPorEndereco"> <i
									data-feather="map-pin"></i> Buscar Compras por Endereços</a></li>
							<li><a class="dropdown-item fw-bold" href="controller?invoke=ListarCompras"><i
									data-feather="shopping-bag"></i> Todas as Compras</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item fw-bold" href="#"><i
									data-feather="users"></i> Funcionarios</a></li>
						</ul></li>
						
					<li class="nav-item dropdown "><a
						class="nav-link dropdown-toggle active fw-bold" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Usuario:${modelUsuario.funcionarioAutenticado.nome} </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item fw-bold" href="#"> <i
									data-feather="calendar"></i> Data de
									acesso:${modelUsuario.dataEHoraDeAcesso}</a></li>
							<li><a class="dropdown-item fw-bold" href="#"><i
									data-feather="user"></i> Login:${modelUsuario.funcionarioAutenticado.login}</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="controller?invoke=Logout"><i
									data-feather="power"></i> Logout</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link disabled">Disabled</a>
					</li>
				</ul>
				<form class="d-flex" role="search" action="sinfar?invoke=BuscarCompraCliente" method="post">
					<input class="form-control me-2" type="search" name="buscar" placeholder="Nome do cliente ou milhar?"
						aria-label="Search">
					<button class="btn btn-outline-success text-white fw-bold" type="submit"><i data-feather="search"></i> </button>
				</form>
			</div>
		</div>
	</nav>