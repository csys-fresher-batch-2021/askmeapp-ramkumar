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
 * Servlet implementation class AddQuestionRelatedTopics
 */
@WebServlet("/AddQuestionRelatedTopics")
public class AddQuestionRelatedTopics extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] questionRelatedTopics = request.getParameterValues("questionRelatedTopics");
		TopicService topicService = new TopicService();
		try {
			Integer questionId = Integer.parseInt(request.getParameter("questionId"));
			topicService.addQuestionRelatedTopics(questionId, questionRelatedTopics);
			response.sendRedirect("index.jsp?infoMessage=Question asked successfully&questionId="+questionId);
		} catch (ServiceException e) {
			response.sendRedirect("relatedTopics.jsp?errorMessage="+e.getMessage());
		} catch (NumberFormatException e) {
			response.sendRedirect("relatedTopics.jsp?errorMessage=Invalid Question Id");
		}
	}

}
