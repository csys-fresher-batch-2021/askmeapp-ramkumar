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
	<%
	QuestionService questionService = new QuestionService();
	AnswerService answerService = new AnswerService();
	List<Question> questionList = questionService.getAllQuestions();
	for (Question questionObj : questionList) {
	%>
	<h5><%=questionObj.getQuestionName()%></h5>
	<%
	if (questionObj.getDescription() != null) {
	%>
	<span><%=questionObj.getDescription()%></span>
	<%
	}
	%>
	<a href="answer.jsp?question=<%=questionObj.getQuestionName()%>"
		class="btn btn-secondary">Answer</a>
	<%
	List<Answer> answerList = answerService.getAllAnswers(questionObj.getQuestionName());
	if (answerList.size() > 0) {
	%>
	<h5>Answers :</h5>
	<%
	for (Answer answer : answerList) {
		out.println("<h6>" + answer.getAnswerName() + "</h6>");
	}
	out.println("<hr/>");
	}
	}
	%>
</body>
</html>