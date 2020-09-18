package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.jdi.request.DuplicateRequestException;

import BEAN.Users;
import DAO.UserDAO;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DuplicateRequestException {

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");

		Users users = new Users();
		users.setId(Integer.parseInt(id));
		users.setName(name);
		users.setEmail(email);
		users.setPhoneNumber(phoneNumber);
		String msg1 = "";
		try {
			boolean rowDeleted = UserDAO.updateUser(users);
			List<Users> listUser = UserDAO.findAllUser();
			if (rowDeleted) {
				msg1 = "Upload success";
				request.setAttribute("msg1", msg1);
				request.setAttribute("listUser", listUser);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/View/Home.jsp");
				rd.forward(request, response);
			} else {
				msg1 = "Upload failed";
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
