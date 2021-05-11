<%@page import="java.util.List"%>
<%@page import="in.ramkumar.model.Question"%>
<%@page import="in.ramkumar.service.QuestionService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Questions</title>
</head>
<body>
		<%
			String question = request.getParameter("question");
			String description = request.getParameter("description");
			Question questionObject = new Question();
			questionObject.setQuestion(question);
			questionObject.setDescription(description);
			boolean validQuestion = QuestionService.addQuestion(questionObject);
			if(validQuestion){
				response.sendRedirect("index.jsp?infoMessage=Question Added");
			}else{
				response.sendRedirect("index.jsp?errorMessage=Question Added Failed");
			}
		%>	
</body>
</html>