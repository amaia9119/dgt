<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/navbar.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>	
  
<form action="login" method="post">
	<div class="col">
		<input type="text" class="form-control" name="nombre" placeholder="Introduce tu usuario">
	</div>
	<div class="col">
		<input type="password" class="form-control" name="pass" placeholder="Introduce tu contrase�a">
	</div>
	<button type="submit" class="btn btn-dark">Iniciar sesi�n</button>
</form>

<%@ include file="../includes/footer.jsp"  %> 
