package servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	request.getSession().setAttribute("User", null);
			
	String result="[{'UnLoginResult':'"+ManageState.Succeed+"'}]";
	JSONArray json = new JSONArray(result);
	PrintWriter out = response.getWriter();
	out.println(json);
	out.flush();
	out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("login contact success");
		
		UserVO user = new UserVO();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		user.setUsername(username);
		user.setPassword(password);
		LoginInfo loginbl = new LoginImpl();
		ManageState LoginResult = loginbl.login(user);
		if (LoginResult == ManageState.Succeed) {
			request.getSession().setAttribute("User", user);
		}		
		String result="[{'LoginResult':'"+LoginResult+"'}]";
		JSONArray json = new JSONArray(result);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
