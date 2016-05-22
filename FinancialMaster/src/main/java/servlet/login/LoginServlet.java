package servlet.login;

import java.io.IOException;
import VO.UserVO;
import businesslogic.loginbl.LoginBL;
import businesslogicservice.loginblservice.LoginBLService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ENUM.ManageState;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO  user=new UserVO();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		user.setUsername(username);
		user.setPassword(password);
		LoginBLService loginbl=new LoginBL();
		ManageState LoginResule =loginbl.login(user);
		request.getSession().setAttribute("LoginResult", LoginResule);
		if(LoginResule==ManageState.Succeed){			 
		    request.getSession().setAttribute("User", user);
		}
				
		doGet(request, response);
	}

}
