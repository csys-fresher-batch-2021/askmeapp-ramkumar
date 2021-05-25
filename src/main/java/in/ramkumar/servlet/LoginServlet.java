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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		UserService userService = new UserService();

		if (role.equals("User")) {
			try {
				User user = userService.getUser(email);
				boolean validUser = userService.login(email, password);
				if (validUser) {
					HttpSession session = request.getSession();
					session.setAttribute("Logged_In_UserName", user.getName());
					session.setAttribute("Logged_In_UserRole", role);
					session.setAttribute("Logged_In_UserEmail", user.getEmail());
					response.sendRedirect("index.jsp?infoMessage=Login successful");
				}
			} catch (ServiceException e) {
				response.sendRedirect("login.jsp?errorMessage=" + e.getMessage());
			}
		} else if (role.equals("Admin")) {
			try {
				boolean validAdmin = userService.adminLogin(email, password);
				if (validAdmin) {
					HttpSession session = request.getSession();
					session.setAttribute("Logged_In_UserRole", role);
					session.setAttribute("Logged_In_AdminEmail", email);
					response.sendRedirect("topic_list.jsp?infoMessage=Login successful");
				}
			} catch (ServiceException e) {
				response.sendRedirect("login.jsp?errorMessage=" + e.getMessage());
			}
		}

	}

}
