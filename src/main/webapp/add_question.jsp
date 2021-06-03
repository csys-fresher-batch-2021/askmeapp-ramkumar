<!DOCTYPE html>
<%@page import="in.ramkumar.model.Question"%>
<%@page import="in.ramkumar.service.QuestionService"%>
<%@page import="in.ramkumar.model.Topic"%>
<%@page import="java.util.List"%>
<%@page import="in.ramkumar.service.TopicService"%>
<html lang="en">
<head>
<title>Add Question</title>
</head>
<body>
	<%
	String role = (String) session.getAttribute("Logged_In_UserRole");
	if (role == null) {
		response.sendRedirect("login.jsp");
	}else{
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container">
		<h3>Ask your questions...</h3>
		<jsp:include page="message.jsp"></jsp:include>
		<form action="AddQuestionServlet">
			<div class="mb-3">
				<label for="question" class="form-label">Enter your question<span
					style="color: red"> * </span></label>
				<textarea class="form-control" name="question" id="question"
					required placeholder="Type your question here..." autofocus
					rows="3"> </textarea>
			</div>
			<div class="mb-3">
				<label for="description" class="form-label">Enter your
					description (optional)</label>

				<textarea rows="5" class="form-control" name="description"
					id="description" placeholder="Descripe your question (optional)"></textarea>
			</div>
			<div class="d-flex flex-row justify-content-between">
				<button type="reset" class="btn btn-info">Reset</button>
				<button type="submit" class="btn btn-primary">Next</button>
			</div>
		</form>
	</main>
	<%} %>
</body>
</html>
