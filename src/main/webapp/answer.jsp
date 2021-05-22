<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Answer</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<jsp:include page="message.jsp"></jsp:include>
		<%
		String questionName = request.getParameter("question");
		%>
		<h5><%=questionName%></h5>

		<form action="AddAnswerServlet" method="post">
			<input type="hidden" name="question" value="<%=questionName%>"
				readonly /><br /> <label for="answer">Enter your answer</label> <br>
			<input type="text" name="answer" id="answer" autofocus required
				placeholder="Answer" /> <br />
			<button type="submit" class="btn btn-primary">Submit</button>
			<a href="question_list.jsp" class="btn btn-danger">Cancel</a>
		</form>
	</main>
</body>
</html>