<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add Topic</title>
</head>
<body>
	<%
	String role = (String) session.getAttribute("Logged_In_UserRole");
	if (role == null) {
		response.sendRedirect("login.jsp");
	} else if (role.equals("User")) {
		response.sendRedirect("index.jsp");
	} else {
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<jsp:include page="message.jsp"></jsp:include>
		<div class="d-flex justify-content-center align-items-center my-5">
			<div class="card w-75">
				<div class="card-header text-center">
					<p style="font-size: 22px">Add Topic</p>
				</div>
				<div class="card-body">
					<form action="AddTopicServlet" method="post">
						<label for="topic" class="form-label">Enter Topic<span
							style="color: red"> * </span>
						</label> <input type="text" class="form-control" name="topic" id="topic"
							required placeholder="Enter Topic" autofocus>
						<div class="d-flex flex-row justify-content-between my-4">
							<div>
								<button type="reset" class="btn btn-primary">Reset</button>
								<a href="topic_list.jsp" class="btn btn-danger">Cancel</a>
							</div>
							<button type="submit" class="btn btn-primary">Add Topic</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
	<%
	}
	%>
</body>
</html>