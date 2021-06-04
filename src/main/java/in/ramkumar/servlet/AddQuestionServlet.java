package in.ramkumar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.model.Question;
import in.ramkumar.service.QuestionService;

/**
 * Servlet implementation class AddQuestionServlet
 */
@WebServlet("/AddQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("Logged_In_UserId");
		String question = request.getParameter("question");
		String description = request.getParameter("description");
		Question addQuestionObject = new Question();
		addQuestionObject.setQuestionName(question);
		addQuestionObject.setDescription(description);
		QuestionService questionService = new QuestionService();
		try {
			Question getQuestionObject = questionService.addQuestion(addQuestionObject, userId);
			if (getQuestionObject != null) {
				response.sendRedirect("ListQuestionServlet?questionSearch="+question);				
			}else {
				Question questionObject = questionService.getQuestion(question);
				response.sendRedirect("related_topics.jsp?infoMessage= Question Added&questionId="+questionObject.getQuestionId());				
			}
		} catch (ServiceException e) {
			String message = e.getMessage();
			response.sendRedirect("add_question.jsp?errorMessage=" + message);
		}
	}

}
