<%@page import="in.ramkumar.dto.TopicReportDTO"%>
<%@page import="in.ramkumar.model.Topic"%>
<%@page import="java.util.List"%>
<%@page import="in.ramkumar.service.TopicService"%>
<%@page import="in.ramkumar.model.User"%>
<%@page import="in.ramkumar.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Topic</title>
</head>
<body>
	<%
	String role = (String) session.getAttribute("Logged_In_UserRole");
	if (role == null) {
		response.sendRedirect("login.jsp");
	} else {
		Integer userId = (Integer) session.getAttribute("Logged_In_UserId");
		Integer topicId = (Integer) (request.getAttribute("topicId"));
		List<TopicReportDTO> topicReports = (List<TopicReportDTO>) request.getAttribute("topicReports");
		TopicService topicService = new TopicService();
		Topic topicObject = topicService.getTopicById(topicId);
		boolean isUserFollowing = topicService.isUserFollowingTopic(topicId, userId);
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="message.jsp"></jsp:include>
	<main class="container-fluid">
		<div class="container-fluid well">
			<div class="row">
				<div class="col">
					<%
					List<Topic> topicList = topicService.getUserFollowingTopics(userId);					for (Topic topic : topicList) {
					%>
					<div class="card my-1">
						<div class="card-body">
							<a href="TopicServlet?topicId=<%=topic.getTopicId()%>"><%=topic.getTopicName()%></a>
							<p class="text-muted">
								(<%=topic.getFollowersCount()%>
								Followers)
							</p>
						</div>
					</div>
					<%
					}
					%>
				</div>
				<div class="col-10">
					<div class="row-12">
						<div class="card my-1">
							<div class="card-body">
								<h3><%=topicObject.getTopicName()%>
									<span class="text-muted" style="font-size: 30px">.</span> <span
										class="text-muted" style="font-size: 16px"><%=topicObject.getFollowersCount()%>
										Followers</span>
								</h3>
								<div id="userFollowBtns">
									<%
									if (isUserFollowing) {
									%>
									<button class="btn btn-outline-secondary"
										onclick="unFollow(event)" type="button" id="followingBtn">Following</button>
									<%
									} else {
									%><button class="btn btn-secondary" onclick="follow(event)"
										type="button" id="followBtn">Follow</button>
									<%
									}
									%>
								</div>
							</div>
						</div>
						<h4>Questions</h4>
						<%
						for (TopicReportDTO topicReport : topicReports) {
						%>
						<div class="row">
							<div class="col">
								<div class="card my-1">
									<div class="card-body">
										<div class="row ml-1">
											<a
												href="ListAnswerServlet?questionId=<%=topicReport.getQuestionId()%>"
												class="link-dark" style="font-size: 18px; font-weight: bold"><%=topicReport.getQuestionName()%></a>
										</div>
										<div class="row ml-1 mt-1">
											<a
												href="ListAnswerServlet?questionId=<%=topicReport.getQuestionId()%>"
												class="link-dark"><%=topicReport.getAnswersCount()%>
												Answers</a>
										</div>
										<div class="row ml-1 mt-4">
											<a
												href="add_answer.jsp?questionId=<%=topicReport.getQuestionId()%>&question=<%=topicReport.getQuestionName()%>"
												class="link-success">Answer</a>
										</div>
									</div>
								</div>
								<%
								}
								%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>

	<script>
		function follow(event) {
			event.preventDefault();
			let url = "FollowTopicServlet?topicId=<%=topicObject.getTopicId()%>&status=follow";
			fetch(url).then(res => res.json()).then(res =>{
				let following = document.getElementById('followingBtn');
				let userFollowBtns = document.getElementById('userFollowBtns');
				let content = ""
				let follow = document.getElementById('followBtn');
				if(res == true){
					follow.style.display = 'none';
					content += "<button class=\'btn btn-outline-secondary\' onclick=\"unFollow(event)\" type=\"button\" id=\"followingBtn\">Following</button>";
				}
				userFollowBtns.innerHTML = content;
			})
		}
		function unFollow(event) {
			event.preventDefault();
			let url = "FollowTopicServlet?topicId=<%=topicObject.getTopicId()%>&status=unFollow";
			fetch(url).then(res => res.json()).then(res =>{
				let userFollowBtns = document.getElementById('userFollowBtns');
				let content = ""
				let following = document.getElementById('followingBtn');
				if(res == true){
					following.style.display = 'none';
					content += "<button class=\'btn btn-secondary\' onclick=\"follow(event)\" type=\"button\" id=\"followBtn\">Follow</button>";
				}
				userFollowBtns.innerHTML = content;
			})
		}
	</script>
	<%
	}
	%>
</body>
</html>