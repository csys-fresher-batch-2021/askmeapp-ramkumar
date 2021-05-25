package in.ramkumar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.model.Topic;
import in.ramkumar.service.TopicService;

/**
 * Servlet implementation class AddTopicServlet
 */
@WebServlet("/AddTopicServlet")
public class AddTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String topicName = request.getParameter("topic");
		Topic topic = new Topic();
		topic.setTopicName(topicName);
		TopicService topicService = new TopicService();
		try {
			topicService.addTopic(topic);
			response.sendRedirect("topic_list.jsp?infoMessage=Topic added successfully");
		} catch (ServiceException e) {
			response.sendRedirect("add_topic.jsp?errorMesage=" + e.getMessage());
		}
	}

}
