<!DOCTYPE html>
<%@page import="in.ramkumar.model.Question"%>
<%@page import="in.ramkumar.service.QuestionService"%>
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
		QuestionService questionService = new QuestionService();
		Integer answersCount = 0;
		Question question = null;
		if (questionIdString != null) {
			questionId = Integer.parseInt(questionIdString);
			question = questionService.getQuestionById(questionId);
			answersCount = questionService.getAnswersCountByQuestionId(questionId);
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
				<div class="col-10">
					<%
					if (questionId != null) {
					%>
					<div class="card my-1">
						<div class="card-body">
						<div class="row ml-1 text-muted" style="font-size: 16px;">Your Question</div>
							<div class="row ml-1 mt-1">
								<a
									href="ListAnswerServlet?questionId=<%=question.getQuestionId()%>"
									class="link-dark" style="font-size: 18px; font-weight: bold"><%=question.getQuestionName()%></a>
							</div>
							<div class="row ml-1 mt-1">
								<a
									href="ListAnswerServlet?questionId=<%=question.getQuestionId()%>"
									class="link-dark"><%=question.getAnswersCount()%> Answers</a>
							</div>
						</div>
					</div>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</main>
	<%
	}
	%>
</body>
</html>
