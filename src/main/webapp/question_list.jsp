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
	List<Question> questionList = QuestionService.getAllQuestions();
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
	if (questionObj.getAnswerList() != null) {
	%>
	<h5>Answers :</h5>
	<%
	List<Answer> answerList = questionObj.getAnswerList();
	for (Answer answer : answerList) {
		out.println("<h6>" + answer.getAnswerName() + "</h6>");
	}
	out.println("<hr/>");
	}
	}
	%>
</body>
</html>