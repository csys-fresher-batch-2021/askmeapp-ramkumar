package in.ramkumar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.service.UserService;

/**
 * Servlet implementation class UpdateUserProfileServlet
 */
@WebServlet("/UpdateUserProfileServlet")
public class UpdateUserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("Logged_In_UserId");
		String newUserEmail = request.getParameter("email");
		String newUserPassword = request.getParameter("password");
		String newUserName = request.getParameter("name");
		UserService userService = new UserService();
		try {
			userService.changeEmail(userId, newUserEmail);
			session.setAttribute("Logged_In_UserEmail", newUserEmail);
			userService.changeUserName(userId, newUserName);
			session.setAttribute("Logged_In_UserName", newUserName);
			userService.changePassword(userId, newUserPassword);
			response.sendRedirect("update_user_profile.jsp?infoMessage=Updated successfully");
		} catch (ServiceException e) {
			response.sendRedirect("update_user_profile.jsp?errorMessage="+ e.getMessage());
		}
	}

}
