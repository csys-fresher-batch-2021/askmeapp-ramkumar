<!DOCTYPE html>
<html lang="en">
<head>
<title>AskMe App</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<h3>Welcome To AskMe</h3>
		<jsp:include page="message.jsp"></jsp:include>
		<form action="AddQuestionServlet">
			<label for="question">Enter your question</label> <br> <input
				type="text" name="question" id="question" required
				placeholder="Enter question" autofocus> <br /> <label
				for="description">Enter your description (optional)</label> <br>
			<input type="text" name="description" id="description"
				placeholder="Description (optional)"> <br />
			<button type="submit" class="btn btn-primary">Submit</button>
			<button type="reset" class="btn btn-info">Reset</button>
		</form>
	</main>
</body>
</html>
