<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/navbar.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>

<form action="matricula" method="post">
	<div class="row">
		<div class="col">
			<input type="text" class="form-control" placeholder="${coche.matricula}" readonly>
		</div>
		<div class="col">
			<input type="number" class="form-control" placeholder="Importe">
			<!--<div class="input-group-prepend">
			<div class="input-group-text">€</div>
			</div>-->
		</div>
		<div class="col">
			<textarea class="form-control" placeholder="Concepto" rows="3"></textarea>
		</div>
	</div>
	<button type="submit" class="btn btn-primary">Submit</button>
</form>

<%@ include file="../includes/footer.jsp"  %> 