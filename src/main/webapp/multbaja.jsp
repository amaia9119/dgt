<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/navbar.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>

<table  class="table table-striped">
	<thead>
		<tr>
			<th scope="col">Matrícula</th>
			<th scope="col">Fecha de baja</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${multbaja}" var="mub">
			<tr>
				<th scope="row">${mub.coche.matricula}</th>
				<td>${mub.fecha_baja}</td>
				<td><span class="icon-bin"></span></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="../includes/footer.jsp"  %> 