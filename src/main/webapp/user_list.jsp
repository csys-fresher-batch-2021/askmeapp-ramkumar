<%@page import="in.ramkumar.model.User"%>
<%@page import="in.ramkumar.service.UserService"%>
<%@page import="in.ramkumar.model.Topic"%>
<%@page import="java.util.List"%>
<%@page import="in.ramkumar.service.TopicService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>All Users</title>
</head>
<body>
	<%
	String role = (String) session.getAttribute("Logged_In_UserRole");
	if (role == null) {
		response.sendRedirect("login.jsp");
	} else if (role.equals("Admin")) {
		UserService userService = new UserService();
		List<User> userList = userService.getAllUsers();
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<jsp:include page="message.jsp"></jsp:include>
		<h4>Total Users: <%=userList.size()%></h4>
		<table class="table table-bordered table-hover caption-top">
			<caption>List of Users</caption>
			<thead>
				<th scope="col">User Id</th>
				<th scope="col">User Name</th>
				<th scope="col">Email</th>
			</thead>
			<tbody>
				<%
				for (User userObject : userList) {
				%>
				<tr>
					<td><%=userObject.getUserId()%></td>
					<td><%=userObject.getName()%></td>
					<td><%=userObject.getEmail()%></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</main>
	<%
	}
	%>
</body>
</html>
