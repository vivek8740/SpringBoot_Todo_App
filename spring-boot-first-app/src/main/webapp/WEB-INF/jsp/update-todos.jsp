<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<h1>Update Todos</h1>
		<table class="table table-striped">
			<thead>
				<tr>
					<td>Description</td>
					<td>Target Date</td>
					<td>Status</td>
					<td>Edit</td>
					<td>Delete</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
						<td>${todo.desc}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
						<td><a type="button" class="btn btn-warning" href="/update-todos?id=${todo.id}">Update</a></td>
						<td><a type="button" class="btn btn-danger" href="/delete-todos?id=${todo.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<a class="link" href="/add-todos">Add New Todo</a>
		</div>

<%@ include file="common/footer.jspf" %>