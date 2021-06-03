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
		<%
		String role = (String) session.getAttribute("Logged_In_UserRole");
		if (role == null) {
			response.sendRedirect("login.jsp");
		} else {
			String questionName = request.getParameter("question");
			Integer questionId = Integer.parseInt(request.getParameter("questionId"));
		%>
		<div class="d-flex justify-content-center align-items-center my-5">
			<div class="card w-75">
				<div class="card-header text-center">
					<p style="font-size: 22px"><%=questionName%></p>
				</div>
				<div class="card-body">
					<form action="AddAnswerServlet" method="post">
						<input type="hidden" name="questionId" value="<%=questionId%>"
							readonly /> <label for="answer" class="form-label">Enter
							your answer<span style="color: red"> * </span>
						</label>
						<textarea class="form-control" name="answer" id="answer" required
							placeholder="Type your answer here..." autofocus rows="10"> </textarea>
						<div class="d-flex flex-row justify-content-between my-4">
							<div>
								<button type="reset" class="btn btn-info">Reset</button>
								<a href="index.jsp" class="btn btn-danger">Cancel</a>
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
	<%
	}
	%>
</body>
</html>