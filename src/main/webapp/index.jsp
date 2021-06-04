<!DOCTYPE html>
<%@page import="in.ramkumar.model.User"%>
<%@page import="in.ramkumar.service.UserService"%>
<%@page import="in.ramkumar.model.Topic"%>
<%@page import="java.util.List"%>
<%@page import="in.ramkumar.service.TopicService"%>
<html lang="en">
<head>
<title>AskMe App</title>
</head>
<body>
	<%
	String role = (String) session.getAttribute("Logged_In_UserRole");
	if (role == null) {
		response.sendRedirect("login.jsp");
	} else {
		String questionIdString = request.getParameter("questionId");
		Integer questionId = null;
		if (questionIdString != null) {
			questionId = Integer.parseInt(questionIdString);
		}
		Integer userId = (Integer) session.getAttribute("Logged_In_UserId");
		UserService userService = new UserService();
		User user = userService.getUserById(userId);
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">
		<div class="container-fluid well">
			<div class="row">
				<div class="col">
					<%
					TopicService topicService = new TopicService();
					List<Topic> topicList = topicService.getUserFollowingTopics(userId);
					for (Topic topic : topicList) {
					%>
					<div class="card my-1">
						<div class="card-body">
							<a href="TopicServlet?topicId=<%=topic.getTopicId()%>"><%=topic.getTopicName()%></a>
							<p class="text-muted">
								(<%=topic.getFollowersCount()%>
								Followers)
							</p>
						</div>
					</div>
					<%
					}
					%>
				</div>
				<div class="col-10"></div>
			</div>
		</div>
	</main>
	<%
	}
	%>
</body>
</html>
