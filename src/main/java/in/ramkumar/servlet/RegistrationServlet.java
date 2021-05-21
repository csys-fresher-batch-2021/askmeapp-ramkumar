package in.ramkumar.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
			response.sendRedirect("registration.jsp?infoMessage=Registration successful");
		} catch (ServiceException e) {
			response.sendRedirect("registration.jsp?errorMessage=" + e.getMessage());
		}
	}

}
