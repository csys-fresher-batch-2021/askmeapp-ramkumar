package in.ramkumar.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.service.TopicService;

/**
 * Servlet implementation class UpdateTopicServlet
 */
@WebServlet("/UpdateTopicServlet")
public class UpdateTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer topicId = Integer.parseInt(request.getParameter("topicId"));
		String newTopicName = request.getParameter("newTopic");
		TopicService topicService = new TopicService();
		try {
			topicService.changeTopicName(topicId, newTopicName);
			response.sendRedirect("topic_list.jsp?infoMessage=Topic updated successfully");
		}catch (ServiceException e) {
			response.sendRedirect("topic_list.jsp?errorMessage=" + e.getMessage());
		}
	}
}
