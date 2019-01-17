<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/navbar.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>

<form action="matricula" method="post">
	<div class="row">
		<div class="col">
			<input type="text" class="form-control" name="matriculaenv" placeholder="Introduce la matrícula">
		</div>
	</div>
	<button type="submit" class="btn btn-dark">Enviar</button>
</form>
<c:if test="${not empty mensaje}">	  
	<div class="alert alert-danger alert-dismissible fade show" role="alert">
	 ${mensaje.texto}			 
	</div>	 	
</c:if>
			
<%@ include file="../includes/footer.jsp"  %> 