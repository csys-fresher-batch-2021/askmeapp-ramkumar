<%@page import="in.ramkumar.model.Question"%>
<%@page import="in.ramkumar.service.QuestionService"%>
<%@page import="in.ramkumar.model.Topic"%>
<%@page import="java.util.List"%>
<%@page import="in.ramkumar.service.TopicService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Add question related topics</title>
</head>
<body>
	<script>
		localStorage.clear();
	</script>
	<jsp:include page="header.jsp"></jsp:include>
	<main class="container">
		<%
		String role = (String) session.getAttribute("Logged_In_UserRole");
		if (role == null) {
			response.sendRedirect("login.jsp");
		} else {
			Integer questionId = Integer.parseInt(request.getParameter("questionId"));
			QuestionService questionService = new QuestionService();
			Question question = questionService.getQuestionById(questionId);
			String description = request.getParameter("description");
			TopicService topicService = new TopicService();
			List<Topic> allTopics = topicService.getAllTopics();
			List<Topic> relatedTopics = topicService.getRelatedTopics(question.getQuestionName());
		%>
		<div class="d-flex justify-content-center align-items-center my-5">
			<div class="card w-75">
				<form action="AddQuestionRelatedTopics" method="post">
					<div class="card-header text-center">
						<h3><%=question.getQuestionName()%></h3>
					</div>
					<div class="card-body">
						<h4>Choose topics that are describe your question(add atleast
							1 topic)</h4>
						<div class="mb-3">
							<input type="hidden" value="<%=questionId%>" readonly
								name="questionId"> <label for="relatedTopicList"
								class="form-label"> You can add some more topics that
								are also describe your question</label>
							<div class="row w-75 ml-5" id="addMoreTopics">
								<input onchange="check()" class="form-control"
									list="relatedTopics" id="relatedTopicList"
									placeholder="Search topics here...">
								<button type="button" class="btn btn-primary"
									onclick="addTopic(event)">Add</button>
							</div>
							<datalist id="relatedTopics">
								<%
								for (Topic topic : allTopics) {
								%>
								<option value="<%=topic.getTopicName()%>">
									<%
									}
									%>
								
							</datalist>
						</div>
						<div id="userSelectedTopics"></div>
						<%
						if (relatedTopics.size() > 0) {
						%>
						<h4>Are these topics describe your question?</h4>
						<%
						for (Topic topic : relatedTopics) {
						%>
						<div class="form-check my-3">
							<input class="form-check-input" type="checkbox"
								onchange="check()" value="<%=topic.getTopicName()%>"
								id="<%=topic.getTopicName()%>" checked
								name="questionRelatedTopics"> <label
								class="form-check-label ml-5" for="<%=topic.getTopicName()%>">
								<%=topic.getTopicName()%></label>
							<script>{
				let suggestedTopics = JSON.parse(localStorage.getItem("SuggestedTopics")) || [];
				suggestedTopics.push("<%=topic.getTopicName()%>");
				localStorage.setItem("SuggestedTopics", JSON.stringify(suggestedTopics));}
				</script>
						</div>
						<%
						}
						}
						}
						%>

					</div>
					<div class="card-footer text-center">
						<button type="submit" class="btn btn-primary"
							id="addQuestionRelatedTopicsBtn" disabled>Submit</button>
					</div>
				</form>
			</div>
		</div>
	</main>
	<script>
		function addTopic(event) {
			event.preventDefault();
			
			let url = "ListTopicServlet";
			fetch(url).then(res=> res.json()).then(res=>{
			let allTopics = [];
			for(let topic of res){
				allTopics.push(topic.topicName);
			}
			let suggestedTopics = JSON.parse(localStorage.getItem("SuggestedTopics")) || [];
			let userTopics = JSON.parse(localStorage.getItem("UserTopics")) || [];
			let topicValue = document.getElementById("relatedTopicList").value;
			let userSelectedTopics = document.getElementById("userSelectedTopics");
			if(!suggestedTopics.includes(topicValue) && !userTopics.includes(topicValue) && topicValue.trim().length > 0 && allTopics.includes(topicValue)){
				userTopics.push(topicValue);
			}
			localStorage.setItem("UserTopics", JSON.stringify(userTopics));
			let content = "";
			for(let topic of userTopics){
				content += "<div class=\"form-check my-3\"> <input type=\"checkbox\" value=\""+topic+"\"onchange=\"check()\" checked name=\"questionRelatedTopics\" id=\""+topic+
				"\"><label for=\""+topic+"\" class=\"form-check-label ml-5\">"
						+ topic + "</label></div>";
			}
			userSelectedTopics.innerHTML = content;
			var checkboxes = document
			.querySelectorAll('input[type="checkbox"]:checked');
			if(checkboxes.length >= 10){
				document.getElementById("addMoreTopics").style.display = "none";
			}
			if (content.length > 0) {
				document.getElementById("addQuestionRelatedTopicsBtn").disabled = false;								
			}
			});			
		}
		function check() {
			var checkboxes = document
					.querySelectorAll('input[type="checkbox"]:checked');
			if (checkboxes.length < 1) {
				document.getElementById("addQuestionRelatedTopicsBtn").disabled = true;				
			} else {
				document.getElementById("addQuestionRelatedTopicsBtn").disabled = false;				
			}
		}
		check();
	</script>
</body>
</html>