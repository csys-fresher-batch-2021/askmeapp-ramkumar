package in.ramkumar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.model.Topic;
import in.ramkumar.service.TopicService;

/**
 * Servlet implementation class ListTopicServlet
 */
@WebServlet("/ListTopicServlet")
public class ListTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TopicService topicService = new TopicService();
		try {
			List<Topic> allTopics = topicService.getAllTopics();
			Gson gson = new Gson();
			String topicJson = gson.toJson(allTopics);
			PrintWriter out = response.getWriter();
			out.print(topicJson);
			out.flush();
		} catch (ServiceException e) {
			response.sendRedirect("topic_list.jsp?errorMessage=" + e.getMessage());
		}
	}

}
