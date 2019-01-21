<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>	
 <main role="main">
<div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
<c:if test="${not empty mensaje}">	  
	<div class="alert alert-danger alert-dismissible fade show" role="alert">
	 ${mensaje.texto}			 
	</div>	 	
</c:if>
<form action="login" method="post">
	<div class="col">
		<input type="text" class="form-control" name="nombre" placeholder="Introduce tu usuario">
	</div>
	<div class="col">
		<input type="password" class="form-control" name="pass" placeholder="Introduce tu contraseña">
	</div>
	<button type="submit" class="btn btn-dark">Iniciar sesión</button>
</form>

<%@ include file="../includes/footer.jsp"  %> 
