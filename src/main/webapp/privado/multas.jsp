<%@ include file="../includes/header.jsp"  %>
<%@ include file="../includes/navbar.jsp"  %>
<%@ include file="../includes/mensajes.jsp"  %>

<table  class="table table-striped responsive no-wrap">
	<thead>
		<tr>
			<th scope="col">Fecha</th>
			<th scope="col">Matr�cula</th>
			<th scope="col">Importe</th>
			<th scope="col">Concepto</th>
			<th scope="col">Anular</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${multas}" var="m">
			<tr>
				<th scope="row">${m.fecha}</th>
				<td>${m.coche.matricula}</td>
				<td>${m.importe}</td>
				<td>${m.concepto}</td>
				<td><button type="button" class="btn anular" data-toggle="modal" data-target="#modal${m.id}">
					<span class="icon-bin"></span></button>
				</td>
			</tr>
			<!-- Modal -->
			<div class="modal fade" id="modal${m.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Atenci�n</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			       �Est�s seguro que deseas anular la multa?
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
			        <a href="${pageContext.request.contextPath}/eliminar?id=${m.id}" class="btn btn-danger">Anular multa</a>
			      </div>
			    </div>
			  </div>
			</div>
		</c:forEach>
	</tbody>
</table>







<%@ include file="../includes/footer.jsp"  %> 