<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/navbar.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>

<form action="matricula" method="post">
	<div class="row">
		<div class="col">
			<input type="text" class="form-control" name="matriculaenv" placeholder="Introduce la matrícula">
		</div>
	</div>
	<button type="submit" class="btn btn-primary">Submit</button>
</form>
<p>${mensaje.texto}</p>
			
<%@ include file="../includes/footer.jsp"  %> 