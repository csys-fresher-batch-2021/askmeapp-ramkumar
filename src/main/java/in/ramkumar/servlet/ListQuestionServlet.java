package in.ramkumar.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.model.Question;
import in.ramkumar.service.QuestionService;

/**
 * Servlet implementation class ListQuestionServlet
 */
@WebServlet("/ListQuestionServlet")
public class ListQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String question = request.getParameter("questionSearch");
		QuestionService questionService = new QuestionService();
		try {
			List<Question> questionList = questionService.getRelatedQuestions(question);
			RequestDispatcher dispatcher = request.getRequestDispatcher("question_list.jsp");
			request.setAttribute("questionList", questionList);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			response.sendRedirect("index.jsp?errorMessage="+e.getMessage());
		}
	}

}
