package in.ramkumar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.service.TopicService;

/**
 * Servlet implementation class AddUserInterstedTopicsServlet
 */
@WebServlet("/AddUserInterstedTopicsServlet")
public class AddUserInterstedTopicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] userInterestedTopics = request.getParameterValues("userInterestedTopics");
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("Logged_In_UserId");
		TopicService topicService = new TopicService();
		try {
			topicService.addUserInterestedTopics(userInterestedTopics, userId);
			response.sendRedirect("index.jsp?infoMessage=Topics successfully added");
		} catch (ServiceException e) {
			response.sendRedirect("choose_interested_toics.jsp?errorMessage="+e.getMessage());
		}
	}

}
