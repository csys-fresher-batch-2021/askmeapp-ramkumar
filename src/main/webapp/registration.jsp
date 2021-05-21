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
	<main class="container-fluid">
		<jsp:include page="message.jsp"></jsp:include>
		<form action="RegistrationServlet" method="post">
			<label for="name">Enter your Name</label> <br /> <input type="text"
				name="name" id="name" autofocus required placeholder="Your Name" /> <br /> <label
				for="email">Enter your Email</label> <br /> <input type="email"
				name="email" id="email" required placeholder="Your Email" /> <br /> <label
				for="password">Enter your Password</label> <br /> <input
				type="password" name="password" id="password" required
				placeholder="Your Password" /> <br />
			<button type="submit" class="btn btn-primary">Signup</button>
		</form>
	</main>

</body>
</html>