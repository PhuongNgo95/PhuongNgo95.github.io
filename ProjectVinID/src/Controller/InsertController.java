package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Users;
import DAO.UserDAO;

/**
 * Servlet implementation class InsertController
 */
@WebServlet("/InsertController")
public class InsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/Insert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");

		Users users = new Users();
		users.setName(name);
		users.setEmail(email);
		users.setPhoneNumber(phoneNumber);
		String msg1 = "";

		try {
			boolean rowDeleted = UserDAO.insertUser(users);
			if (rowDeleted) {
				msg1 = "insert success";
				List<Users> listUser = UserDAO.findAllUser();
				request.setAttribute("msg1", msg1);
				request.setAttribute("listUser", listUser);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/Home.jsp");
				rd.forward(request, response);
			}else {
				msg1 = "insert failed";
				List<Users> listUser = UserDAO.findAllUser();
				request.setAttribute("msg1", msg1);
				request.setAttribute("listUser", listUser);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/Home.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			msg1 = "Name is unique!";
			try {
				List<Users> listUser = UserDAO.findAllUser();
				request.setAttribute("msg1", msg1);
				request.setAttribute("listUser", listUser);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/Home.jsp");
				rd.forward(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
