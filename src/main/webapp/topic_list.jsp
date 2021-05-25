<%@page import="in.ramkumar.model.Topic"%>
<%@page import="java.util.List"%>
<%@page import="in.ramkumar.service.TopicService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>All Topics</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container-fluid">
		<jsp:include page="message.jsp"></jsp:include>
		<div class="table-responsive">
		<table class="table table-bordered table-hover">
			<thead class="thead-light">
				<th>Topic Id</th>
				<th>Topic</th>
				<th>Action</th>
			</thead>
			<tbody id="topicList">

			</tbody>
		</table>
		</div>
		<a class="btn btn-primary" href="add_topic.jsp">Add Topic</a>
	</main>
	<script>
		function getAllTopics(){
			let url = "ListTopicServlet";
			fetch(url).then(res=> res.json()).then(res=>{
			let topics = res;
			let content = "";
			for(let topic of topics){
				content += "<tr><td>" + topic.topicId + 
				"</td><td>" + topic.topicName + 
				"</td>" + "<td><a class=\"btn btn-primary\" href=\"update_topic.jsp?topicId=" + topic.topicId + "\"" + ">Update</a></td></tr>";
			}
			console.log(content);
			document.querySelector("#topicList").innerHTML= content;
		})
	}
		getAllTopics();
</script>
</body>
</html>
