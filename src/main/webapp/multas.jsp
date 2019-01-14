<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/navbar.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>

<table  class="table table-striped">
	<thead>
		<tr>
			<th scope="col">Fecha</th>
			<th scope="col">Matrícula</th>
			<th scope="col">Importe</th>
			<th scope="col">Eliminar</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${multas}" var="m">
			<tr>
				<th scope="row">${m.fecha}</th>
				<td>${m.coche.matricula}</td>
				<td>${m.importe}</td>
				<td><span class="icon-bin"></span></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="../includes/footer.jsp"  %> 