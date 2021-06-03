<%@page import="java.util.List"%>
<%@page import="in.ramkumar.service.UserService"%>
<%@page import="in.ramkumar.service.TopicService"%>
<%@page import="in.ramkumar.model.Topic"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Interested topics</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
</head>
<body>

	<%
	String role = (String) session.getAttribute("Logged_In_UserRole");
	if (role == null) {
		response.sendRedirect("login.jsp");
	} else{
	TopicService topicService = new TopicService();
	List<Topic> userInterestedTopics = topicService.getUserInterestedTopics();
	%>
	<main class="container">
	<jsp:include page="message.jsp"></jsp:include>
		<div class="d-flex justify-content-center align-items-center my-5">
			<div class="card w-75">
				<form action="AddUserInterstedTopicsServlet" method="post">
					<div class="card-header text-center">Topics</div>
					<div class="card-body">
						<h5 class="card-title">Choose 5 or more interested topics</h5>
						<%
						for (Topic topic : userInterestedTopics) {
						%>
						<div class="form-check my-3" onchange=check()>
							<input class="form-check-input" type="checkbox"
								value="<%=topic.getTopicName()%>" name="userInterestedTopics" id="<%=topic.getTopicId()%>">
							<label class="form-check-label ml-5"
								for="<%=topic.getTopicId()%>"> <%=topic.getTopicName()%><span
								class="text-muted"> (<%=topic.getFollowersCount()%>
									Followers)
							</span>
							</label>
						</div>
						<%
						}
						%>
					</div>
					<div class="card-footer text-center">
						<button type="submit" id="addInterestedTopicsBtn"
							class="btn btn-primary" disabled>Add 5 or more topics</button>
					</div>
				</form>
			</div>
		</div>
	</main>
	<%} %>
	<script>
		function check() {
			var checkboxes = document
					.querySelectorAll('input[type="checkbox"]:checked');
			if (checkboxes.length >= 5) {
			document.getElementById("addInterestedTopicsBtn").disabled = false;				
			} else {
				document.getElementById("addInterestedTopicsBtn").disabled = true;				
			}
		}
	</script>
</body>
</html>