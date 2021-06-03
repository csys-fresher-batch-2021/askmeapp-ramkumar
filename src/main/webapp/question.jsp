<%@page import="in.ramkumar.model.Question"%>
<%@page import="in.ramkumar.service.QuestionService"%>
<%@page import="in.ramkumar.model.User"%>
<%@page import="in.ramkumar.service.UserService"%>
<%@page import="in.ramkumar.model.Answer"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Question</title>
</head>
<body>
	<%
	String role = (String) session.getAttribute("Logged_In_UserRole");
	List<Answer> answerList = (List<Answer>) request.getAttribute("answerList");
	Integer questionId = (Integer) request.getAttribute("questionId");
	QuestionService questionService = new QuestionService();
	Question question = questionService.getQuestionById(questionId);
	if (role == null) {
		response.sendRedirect("login.jsp");
	}
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container well">
		<div class="card my-1">
			<div class="card-body">
				<div class="row ml-1">
					<h4 style="font-weight: bold"><%=question.getQuestionName()%></h4>
				</div>
				<div class="row ml-1 mt-4">
					<a
						href="add_answer.jsp?questionId=<%=question.getQuestionId()%>&question=<%=question.getQuestionName()%>"
						class="link-success">Answer</a>
				</div>
			</div>
		</div>
		<%
		if (answerList.size() >= 1) {
		%>
		<h4 class="text-muted mt-5"><%=answerList.size()%>
			Answers
		</h4>
		<%
		for (Answer answer : answerList) {
			UserService userService = new UserService();
			User user = userService.getUserById(answer.getUserId());
		%>
		<div class="card my-3">
			<div class="card-body">
				<p class="text-muted">
					Answered by <span style="font-weight: bold"><%=user.getName()%></span>
				</p>
				<div class="row ml-1">
					<h4><%=answer.getAnswerName()%></h4>
				</div>
				<div class="row ml-1 mt-4">
					<button class="btn btn-outline-info">Upvote</button>
					<button class="btn btn-outline-info">Downvote</button>
				</div>
			</div>
		</div>
		<%
		}
		}
		%>
	</main>
</body>
</html>