<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/navbar.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>


<c:if test="${not empty mensaje}">	  
	<div class="alert alert-danger alert-dismissible fade show" role="alert">
	 ${mensaje.texto}			 
	</div>	 	
</c:if>
<form action="multas" method="post">
			<input type="hidden" class="form-control" name="coche_id" value="${coche.id}">
			<input type="hidden" class="form-control" name="id_agente" value="${sessionScope.ag.id}">
		<div class="col">
			<label>Matrícula:</label>
			<input type="text" class="form-control" placeholder="${coche.matricula}" readonly>
		</div>
		<div class="col">
			<label>Modelo:</label>
			<input type="text" class="form-control" placeholder="${coche.modelo}" readonly>
		</div>
		<div class="col">
			<label>Distancia recorrida:</label>
			<input type="text" class="form-control" placeholder="${coche.km}km" readonly>
		</div>
		<div class="col">
			<label>Importe:</label>
			<input type="number" class="form-control" name="importe" placeholder="Importe" value="${importe}">
			<!--<div class="input-group-prepend">
			<div class="input-group-text">€</div>
			</div>-->
		</div>
		<div class="col">
			<label for="concepto">Concepto  <span id="contadorLabel">(0/250)</span></label>
			<textarea id="concepto" class="form-control" name="concepto" placeholder="Concepto" value="${concepto}" rows="3"></textarea>
		</div>
	<button type="submit" class="btn btn-dark">Crear multa</button>
</form>

<%@ include file="../includes/footer.jsp"  %> 