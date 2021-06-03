package in.ramkumar.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.service.TopicService;

/**
 * Servlet implementation class FollowTopicServlet
 */
@WebServlet("/FollowTopicServlet")
public class FollowTopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("Logged_In_UserId");
		TopicService topicService = new TopicService();
		String status = request.getParameter("status");
		try {
			Integer topicId = Integer.parseInt(request.getParameter("topicId"));
			if (status.equalsIgnoreCase("follow")) {
				boolean followTopic = topicService.followTopic(topicId, userId);
				Gson gson = new Gson();
				String json = gson.toJson(followTopic);
				PrintWriter out = response.getWriter();
				out.write(json);
				out.flush();
			} else if (status.equalsIgnoreCase("unFollow")) {
				boolean unFollowTopic = topicService.unFollowTopic(topicId, userId);
				Gson gson = new Gson();
				String json = gson.toJson(unFollowTopic);
				PrintWriter out = response.getWriter();
				out.write(json);
				out.flush();
			}
		} catch (ServiceException e) {
			response.sendRedirect("topic.jsp?errorMessage=" + e.getMessage());
		} catch (NumberFormatException e) {
			response.sendRedirect("topic.jsp?errorMessage=Invalid Topic Id");
		}
	}
}
