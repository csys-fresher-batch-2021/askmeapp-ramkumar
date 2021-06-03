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
	<nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
		<%
		if (role != null && role.equals("Admin")) {
		%>
		<a class="navbar-brand" href="topic_list.jsp">AskMe Admin</a>
		<%
		} else {
		%>
		<div class="container-fluid">
			<a class="navbar-brand" href="index.jsp">AskMe</a>
			<%
			}
			%>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mb-2 mb-lg-0">
					<%
					if (role != null && role.equals("Admin")) {
					%>
					<li class="nav-item"><a class="nav-link active"
						href="topic_list.jsp">Home</a></li>
					<%
					} else {
					%>
					<li class="nav-item"><a class="nav-link active"
						href="index.jsp">Home</a></li>
					<%
					}
					%>
					<%
					if (userName == null && role == null) {
					%>
					<li class="nav-item"><a class="nav-link active"
						href="login.jsp">Login</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="registration.jsp">Register</a></li>
					<%
					} else if (userName != null && role.equals("User")) {
					%>
					<li class="nav-item"><a class="nav-link ml-5"
						href="add_question.jsp">Add Question</a></li>
					<li class="nav-item dropdown ml-5"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown"> <%=userName%></a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="update_user_profile.jsp">Update
									Profile</a></li>
							<li><a class="dropdown-item" href="LogoutServlet">Logout</a></li>
						</ul></li>
					<li class="nav-item ml-5"><a class="nav-link"
						href="LogoutServlet">Logout</a> 
					<% } else if (role.equals("Admin") && userName == null) {%>
					<li class="nav-item"><a class="nav-link ml-5" href="user_list.jsp">List
							Users</a></li>
					<li class="nav-item"><a class="nav-link ml-5" href="topic_list.jsp">List
							Topics</a></li>
					<li class="nav-item"><a class="nav-link ml-5" href="LogoutServlet">Logout</a>
					</li>
					<%
					}
					%>
				</ul>
				<%
				if (userName != null && role.equals("User")) {
				%>
				<form class="d-flex w-50" action="ListQuestionServlet" method="get">
					<input class="form-control ml-5 mt-3" type="search"
						placeholder="Search question" name="questionSearch" required>
					<button class="btn btn-success ml-3 mt-3" type="submit">Search</button>
				</form>
				<%
				}
				%>
			</div>
		</div>
	</nav>
</header>