<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container">
		<jsp:include page="message.jsp"></jsp:include>
		<form action="RegistrationServlet" method="post">
			<div class="mb-3">
				<label class="form-label" for="name">Enter your Name <span
					style="color: red"> * </span></label> <input class="form-control" type="text"
					name="name" id="name" autofocus required placeholder="Name" />
			</div>
			<div class="mb-3">
				<label class="form-label" for="email">Enter your Email <span
					style="color: red"> * </span></label> <input class="form-control"
					type="email" name="email" id="email" required placeholder="Email" />
			</div>
			<div class="mb-3">
				<label class="form-label" for="password">Enter your Password <span
					style="color: red"> * </span></label> <input class="form-control"
					type="password" name="password" id="password" required
					placeholder="Password" />
			</div>
			<div class="row mb-3">
				<div class="form-check">
					<input class="form-check-input" type="radio" name="role"
						value="User" id="user" checked /> <label
						class="form-check-label ml-4" for="user"> User </label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio" value="Admin"
						name="role" id="admin" /> <label class="form-check-label ml-4"
						for="admin"> Admin </label>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Signup</button>
			<a href="login.jsp" class="btn btn-primary">Login</a>
		</form>
	</main>
</body>
</html>