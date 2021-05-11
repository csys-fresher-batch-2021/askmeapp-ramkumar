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
	<h3>Question list</h3>
	<%
	List<Question> questionList = QuestionService.getAllQuestions();
	for (Question questionObj : questionList) {
	%>

	<h4><%=questionObj.getQuestion()%></h4>
	<h4><%=questionObj.getDescription()%></h4>
	<%
	}
	%>
</body>
</html>