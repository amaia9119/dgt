<div class="pos-f-t">
  <nav class="navbar navbar-dark bg-dark">
  	<a href="javascript:history.back(-1);" title="Ir la p�gina anterior"><span class="icon-arrow-left2"></span></a>
  	<span>${sessionScope.agente.nombre}</span>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  </nav>
  <div class="collapse" id="navbarToggleExternalContent">
    <div class="bg-dark p-4">
      <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Inicio <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/matricula">Multar</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/multas">Ver multas</a>
	      </li>
	    </ul>
    </div>
  </div>
</div>

<main role="main">
<div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">