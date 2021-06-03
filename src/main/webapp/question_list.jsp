<%@page import="in.ramkumar.service.AnswerService"%>
<%@page import="in.ramkumar.model.Answer"%>
<%@page import="in.ramkumar.service.QuestionService"%>
<%@page import="in.ramkumar.model.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Questions</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container">
		<%
		String role = (String) session.getAttribute("Logged_In_UserRole");
		if (role == null) {
			response.sendRedirect("login.jsp");
		} else {
			List<Question> questionList = (List<Question>) request.getAttribute("questionList");
			if (questionList.size() <= 0) {
		%>
		<h2>No search results</h2>
		<%
		} else {
		%>
		<div class="container-fluid well">
			<h4 class="text-muted">Search Results</h4>
			<%
			for (Question question : questionList) {
				QuestionService questionService = new QuestionService();
				Integer answersCount = questionService.getAnswersCountByQuestionId(question.getQuestionId());
				question.setAnswersCount(answersCount);
			%>
			<div class="row">
				<div class="col">
					<div class="card my-1">
						<div class="card-body">
							<div class="row ml-1">
								<a
									href="ListAnswerServlet?questionId=<%=question.getQuestionId()%>"
									class="link-dark" style="font-size: 18px; font-weight: bold"><%=question.getQuestionName()%></a>
							</div>
							<div class="row ml-1 mt-1">
								<a
									href="ListAnswerServlet?questionId=<%=question.getQuestionId()%>"
									class="link-dark"><%=question.getAnswersCount()%> Answers</a>
							</div>
							<div class="row ml-1 mt-4">
								<a
									href="add_answer.jsp?questionId=<%=question.getQuestionId()%>&question=<%=question.getQuestionName()%>"
									class="link-success">Answer</a>
							</div>
						</div>
					</div>
					<%
					}
					}
					%>
				</div>
			</div>
		</div>
		<%
		}
		%>

	</main>
</body>
</html>