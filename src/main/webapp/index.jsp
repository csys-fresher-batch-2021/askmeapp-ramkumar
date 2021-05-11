<!DOCTYPE html>
<html lang="en">
<head>
<title>MyApp</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
	<h3>Welcome To AskMe Project</h3>
	<jsp:include page="message.jsp"></jsp:include>
	<form action="question_action.jsp">
		<label for="question">Enter your question</label> <br>
		<input type="text" name="question" id="question" required placeholder="Enter question"
			autofocus> <br/> 
		<label for="description">Enter your description</label> <br>
		<input type="text" name="description" id="description" required placeholder="Enter description"> <br />
		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="reset" class="btn btn-info">Reset</button>
	</form>
	</main>
</body>
</html>
