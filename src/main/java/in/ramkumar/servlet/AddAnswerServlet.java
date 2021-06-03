package in.ramkumar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.model.Answer;
import in.ramkumar.service.AnswerService;

/**
 * Servlet implementation class AddAnswerServlet
 */
@WebServlet("/AddAnswerServlet")
public class AddAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String answer = request.getParameter("answer");
		Answer answerObject = new Answer();
		answerObject.setAnswerName(answer);
		AnswerService answerService = new AnswerService();
		HttpSession session = request.getSession();
		Integer userId =  (Integer) session.getAttribute("Logged_In_UserId");
		try {
			Integer questionId =Integer.parseInt(request.getParameter("questionId"));
			answerService.addAnswer(questionId, userId, answerObject);
			response.sendRedirect("index.jsp?infoMessage=Answer Added");
		} catch (ServiceException e) {
			response.sendRedirect("answer.jsp?errorMessage=" + e.getMessage());
		} catch (NumberFormatException e) {
			response.sendRedirect("index.jsp?errorMessage=Invalid Question Id");
		}
	}

}
