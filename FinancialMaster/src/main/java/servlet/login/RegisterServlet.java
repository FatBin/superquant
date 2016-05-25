package servlet.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import ENUM.ManageState;
import VO.UserVO;
import web.bl.userImpl.LoginImpl;
import web.blservice.userInfo.LoginInfo;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("register contact success");

		UserVO user = new UserVO();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		user.setUsername(username);
		user.setPassword(password);
		LoginInfo loginbl = new LoginImpl();
		ManageState RegistResult = loginbl.add(user);
		if (RegistResult == ManageState.Succeed) {
			request.getSession().setAttribute("User", user);
			
//			request.getRequestDispatcher(request.getContextPath()+"/Web_Pages/HomePage.jsp").forward(request, response);
		}
		String result="[{'RegistResult':'"+RegistResult+"'}]";
		JSONArray json = new JSONArray(result);
		System.out.println(result);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
