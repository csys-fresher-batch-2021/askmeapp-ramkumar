package in.ramkumar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String question = request.getParameter("question");
		String description = request.getParameter("description");
		Question questionObject = new Question();
		questionObject.setQuestionName(question);
		questionObject.setDescription(description);
		QuestionService questionService = new QuestionService();
		try {
			questionService.addQuestion(questionObject);
			response.sendRedirect("question_list.jsp?infoMessage=Question Added");
		} catch (ServiceException e) {
			String message = e.getMessage();
			response.sendRedirect("index.jsp?errorMessage=" + message);
		}

	}

}
