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
import in.ramkumar.model.Answer;
import in.ramkumar.service.AnswerService;

/**
 * Servlet implementation class ListAnswerServlet
 */
@WebServlet("/ListAnswerServlet")
public class ListAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AnswerService answerService = new AnswerService();
		try {
			Integer questionId = Integer.parseInt(request.getParameter("questionId"));
			List<Answer> answerList = answerService.getAllAnswers(questionId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("question.jsp");
			request.setAttribute("answerList", answerList);
			request.setAttribute("questionId", questionId);		
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			response.sendRedirect("index.jsp?errorMessage="+e.getMessage());
		} catch (NumberFormatException e) {
			response.sendRedirect("index.jsp?errorMessage=Invalid Question Id");
		}
	}

}
