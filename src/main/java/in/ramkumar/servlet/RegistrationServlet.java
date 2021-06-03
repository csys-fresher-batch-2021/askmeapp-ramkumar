package in.ramkumar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.model.User;
import in.ramkumar.service.UserService;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		User addUser = new User(name, email, password);
		UserService userService = new UserService();
		HttpSession session = request.getSession();
		try {
			userService.registerUser(addUser);
			User getUser = userService.getUserByEmail(email);
			session.setAttribute("Logged_In_UserName", name);
			session.setAttribute("Logged_In_UserRole", "User");
			session.setAttribute("Logged_In_UserEmail", email);
			session.setAttribute("Logged_In_UserId", getUser.getUserId());
			response.sendRedirect("choose_interested_toics.jsp?infoMessage=Successfully registered");
		} catch (ServiceException e) {
			response.sendRedirect("registration.jsp?errorMessage=" + e.getMessage());
		}
	}

}
