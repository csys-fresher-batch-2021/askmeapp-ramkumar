<%@page import="in.ramkumar.model.Topic"%>
<%@page import="in.ramkumar.service.TopicService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Update Topic</title>
</head>
<body>
	<%
	Integer topicId = Integer.parseInt(request.getParameter("topicId"));
	TopicService topicService = new TopicService();
	Topic topicObject = topicService.getTopicById(topicId);
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<jsp:include page="message.jsp"></jsp:include>
		<form action="UpdateTopicServlet" method="post">
		<input type="hidden" name="topicId" value="<%=topicId%>"
				readonly /><br />
			<input type="hidden" name="topic" id="topic" readonly
				value="<%=topicObject.getTopicName()%>" /> <label for="name">Enter
				Topic</label> <br /> <input type="text" name="newTopic" id="newTopic"
				autofocus required value="<%=topicObject.getTopicName()%>" /> <br />
			<button type="submit" class="btn btn-primary">Save</button>
			<a type="button" class="btn btn-danger" href="topic_list.jsp">Cancel</a>
			
		</form>
	</main>

</body>
</html>