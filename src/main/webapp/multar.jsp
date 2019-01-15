<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/navbar.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>

<form action="multas" method="post">
			<input type="hidden" class="form-control" name="coche_id" value="${coche.id}">
			<input type="hidden" class="form-control" name="id_agente" value="${sessionScope.ag.id}">
		<div class="col">
			<input type="text" class="form-control" placeholder="${coche.matricula}" readonly>
		</div>
		<div class="col">
			<input type="number" class="form-control" name="importe" placeholder="Importe">
			<!--<div class="input-group-prepend">
			<div class="input-group-text">€</div>
			</div>-->
		</div>
		<div class="col">
			<textarea class="form-control" name="concepto" placeholder="Concepto" rows="3"></textarea>
		</div>
	<button type="submit" class="btn btn-dark">Crear multa</button>
</form>

<%@ include file="../includes/footer.jsp"  %> 