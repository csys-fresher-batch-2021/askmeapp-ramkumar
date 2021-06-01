<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add Topic</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<jsp:include page="message.jsp"></jsp:include>
		<h2>Add Topic</h2>
		<br />
		<form action="AddTopicServlet" method="post">
			<label for="topic">Enter topic : </label> <input type="text"
				name="topic" id="topic" autofocus required placeholder="Topic" /> <br />
			<button type="submit" class="btn btn-primary">Submit</button>
			<a href="topic_list.jsp" class="btn btn-danger">Cancel</a>
		</form>
	</main>
</body>
</html>