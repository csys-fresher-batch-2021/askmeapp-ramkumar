<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<jsp:include page="message.jsp"></jsp:include>
		<form action="LoginServlet" method="get">
			<label for="email">Enter your Email</label> <br /> <input
				type="email" name="email" id="email" autofocus required
				placeholder="Email" /> <br /> <label for="password">Enter
				your Password</label> <br /> <input type="password" name="password"
				id="password" required placeholder="Password" /> <br />
			<button type="submit" class="btn btn-primary">Login</button>
			<a href="registration.jsp" class="btn btn-primary">Signup</a>
			<input type="radio" name="role" id="admin" value="Admin" required />
			<label for="admin">Admin</label>
			<input type="radio" name="role" id="user" value="User" required checked />
			<label for="user">User</label>
		</form>
	</main>
</body>
</html>