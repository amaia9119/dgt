<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/navbar.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>

<table  class="table table-striped">
	<thead>
		<tr>
			<th scope="col">Fecha</th>
			<th scope="col">Matr�cula</th>
			<th scope="col">Importe</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${multas}" var="m">
			<tr>
				<th scope="row">${m.fecha}</th>
				<td>${m.coche.matricula}</td>
				<td>${m.importe}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="../includes/footer.jsp"  %> 