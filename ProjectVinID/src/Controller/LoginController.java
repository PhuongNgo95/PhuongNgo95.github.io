package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.ADmin;
import BEAN.Users;
import DAO.AccountAdminDAO;
import DAO.UserDAO;
import DB.DBconnection;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("username");
		String pass = request.getParameter("password");

		ADmin admin = new ADmin();

		admin.setUsername(name);
		admin.setPass(pass);
		String msg1 = "";
		try {
			boolean login = AccountAdminDAO.findAcccount(admin);
			if (login) {
				msg1 = "Login success";
				List<Users> listUser = UserDAO.findAllUser();
				request.setAttribute("msg1", msg1);
				request.setAttribute("listUser", listUser);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/Home.jsp");
				rd.forward(request, response);
			} else {
				msg1 = "Login incorrect";
				request.setAttribute("msg1", msg1);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/Login.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
