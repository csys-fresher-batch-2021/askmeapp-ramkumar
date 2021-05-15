package in.ramkumar.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ramkumar.model.Answer;
import in.ramkumar.model.Question;
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
		String question = request.getParameter("question");
		Answer answerObject = new Answer();
		answerObject.setAnswerName(answer);
		try {
			AnswerService.addAnswer(question, answerObject);
			response.sendRedirect("question_list.jsp");
		} catch (IllegalArgumentException e) {
			response.sendRedirect("answer.jsp");
		}
	}

}
