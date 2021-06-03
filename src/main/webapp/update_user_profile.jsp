<%@page import="in.ramkumar.service.UserService"%>
<%@page import="in.ramkumar.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Update Profile</title>
</head>
<body>
	<%
	String role = (String) session.getAttribute("Logged_In_UserRole");
	if (role == null) {
		response.sendRedirect("login.jsp");
	} else {
		String email = (String) session.getAttribute("Logged_In_UserEmail");
		UserService userService = new UserService();
		User user = userService.getUserByEmail(email);
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<jsp:include page="message.jsp"></jsp:include>
		<div class="d-flex justify-content-center align-items-center my-5">
			<div class="card w-75">
				<div class="card-header text-center">
					<p style="font-size: 22px">
						Edit Profile (
						<%=user.getName()%>
						)
					</p>
				</div>
				<div class="card-body">
					<form action="UpdateUserProfileServlet" method="post">
						<label for="answer" class="form-label">Enter Name<span
							style="color: red"> * </span>
						</label> <input type="text"  class="form-control"
							name="name" id="name" onmouseout="check()" required placeholder="Enter Name"
							autofocus value="<%=user.getName()%>"> <label
							for="answer" class="form-label mt-4">Enter Email<span
							style="color: red"> * </span>
						</label> <input onmouseout="check()"  type="email"  class="form-control"
							name="email" id="email" required placeholder="Enter Email"
							value="<%=user.getEmail()%>"> <label for="answer"
							class="form-label mt-4">Enter Password<span
							style="color: red"> * </span>
						</label> <input type="password" onmouseout="check()" 
							class="form-control" name="password" id="password" required
							placeholder="Enter Password" value="<%=user.getPassword()%>">
						<div class="d-flex flex-row justify-content-between my-4">
							<div>
								<a href="index.jsp" class="btn btn-danger">Cancel</a>
							</div>
							<button type="submit" id="save" class="btn btn-primary" disabled>Save
								Changes</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
	<script>
		function check() {
			var name = document.getElementById('name').value.trim();
			var email = document.getElementById('email').value.trim();
			var password = document.getElementById('password').value.trim();
			var save = document.getElementById('save');
			if (name === '<%=user.getName()%>' && email === '<%=user.getEmail()%>' && password ==='<%=user.getPassword()%>') {
				save.disabled = true;
			} else{
				save.disabled = false;
			}
		}
	</script>
	<%
	}
	%>
</body>
</html>