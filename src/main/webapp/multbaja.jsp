<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/navbar.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>

<table  class="table table-striped responsive no-wrap">
	<thead>
		<tr>
			<th scope="col">Fecha alta</th>
			<th scope="col">Fecha baja</th>
			<th scope="col">Matrícula</th>
			<th scope="col">Importe</th>
			<th scope="col">Concepto</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${multbaja}" var="mub">
			<tr>
				<td scope="row">${mub.fecha_alta}</td>
				<td>${mub.fecha_baja}</td>
				<th>${mub.coche.matricula}</th>
				<td>${mub.importe}</td>
				<td>${mub.concepto}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="../includes/footer.jsp"  %> 