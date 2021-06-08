package in.ramkumar.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ramkumar.dto.TopicReportDTO;
import in.ramkumar.exception.ServiceException;
import in.ramkumar.service.TopicService;

/**
 * Servlet implementation class TopicServlet
 */
@WebServlet("/TopicServlet")
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TopicService topicService = new TopicService();
		try {
			Integer topicId = Integer.parseInt(request.getParameter("topicId"));
			List<TopicReportDTO> topicReports = topicService.getTopicReports(topicId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("topic.jsp");
			request.setAttribute("topicReports", topicReports);
			request.setAttribute("topicId", topicId);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			response.sendRedirect("index.jsp?errorMessage="+e.getMessage());
		} catch (NumberFormatException e) {
			response.sendRedirect("index.jsp?errorMessage=Invalid Topic Id");			
		}
	}

}
