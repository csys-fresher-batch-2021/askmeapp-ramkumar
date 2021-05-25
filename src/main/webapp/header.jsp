<%@page import="in.ramkumar.model.User"%>
<%@page import="java.util.List"%>
<%@page import="in.ramkumar.service.UserService"%>
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/fontawesome.min.css">
<link rel="stylesheet" href="assets/css/style.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<%
String userName = (String) session.getAttribute("Logged_In_UserName");
String role = (String) session.getAttribute("Logged_In_UserRole");
%>

<header>
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
		<%
		if (role != null && role.equals("Admin")) {
		%>
		<a class="navbar-brand"
			href="topic_list.jsp">AskMe Admin</a>
		<%
		}else{
		%>
		<a class="navbar-brand" href="index.jsp">AskMe</a><%} %>
		<button class="navbar-toggler d-lg-none" type="button"
			data-toggle="collapse" data-target="#collapsibleNavId"
			aria-controls="collapsibleNavId" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavId">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<%
				if (role != null && role.equals("Admin")) {
				%>
				<li class="nav-item active"><a class="nav-link"
					href="topic_list.jsp">Home <span class="sr-only">(current)</span></a></li>
				<%
		}else{
		%>
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home <span class="sr-only">(current)</span></a></li><%} %>
			</ul>
			<ul class="navbar-nav ml-auto mt-2 mt-lg-0">
				<%
				if (userName == null && role == null) {
				%>
				<li class="nav-item active"><a class="nav-link"
					href="login.jsp">Login</a></li>
				<li class="nav-item"><a class="nav-link"
					href="registration.jsp">Register</a></li>
				<%
				} else if (userName != null && role.equals("User")) {
				%>
				<li class="nav-item"><a class="nav-link"
					href="question_list.jsp">List Questions</a></li>
				<div class="dropdown">
					<button type="button" class="btn btn-secondary dropdown-toggle"
						role="button" id="dropdownMenuLink" data-toggle="dropdown">
						<%=userName%>
					</button>

					<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
						<li><a class="dropdown-item" href="update_user_profile.jsp">Update
								Profile</a></li>
						<li><a class="dropdown-item" href="LogoutServlet">Logout</a></li>
					</ul>
				</div>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a>
				</li>
				<%
				} else if (role.equals("Admin") && userName == null) {
				%>
				<li class="nav-item"><a class="nav-link" href="user_list.jsp">List
						Users</a></li>
				<li class="nav-item"><a class="nav-link" href="add_topic.jsp">Add
						Topic</a></li>
				<li class="nav-item"><a class="nav-link" href="topic_list.jsp">List
						Topics</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a>
				</li>
				<%
				}
				%>
			</ul>
		</div>
	</nav>
</header>