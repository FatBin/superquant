package servlet.managestock;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ENUM.ManageState;
import PO.UpToDateStockPO;
import VO.StockDetailVO;
import VO.UserVO;
import web.bl.userImpl.UserManageImpl;
import web.blservice.userInfo.UserManageInfo;

/**
 * Servlet implementation class ManageMyStockServlet
 */
@WebServlet("/ManageMyStockServlet")
public class ManageMyStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageMyStockServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
		 ManageState result=ManageState.Unlogin;
		 if(request.getSession().getAttribute("User")!=null){
			 UserVO userVO=(UserVO)request.getSession().getAttribute("User");
			 UpToDateStockPO upToDateStockPO=sv.getUpToDateMessage();
			 UserManageInfo userManageInfo=new UserManageImpl();
			 result=userManageInfo.addStock(userVO, upToDateStockPO.getStockId());			 
		 }
		 request.getSession().setAttribute("AddResult",result);		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
		 ManageState result=ManageState.Unlogin;
		 if(request.getSession().getAttribute("User")!=null){
			 UserVO userVO=(UserVO)request.getSession().getAttribute("User");
			 UpToDateStockPO upToDateStockPO=sv.getUpToDateMessage();
			 UserManageInfo userManageInfo=new UserManageImpl();
			 result=userManageInfo.deleteStock(userVO, upToDateStockPO.getStockId());			 
		 }
		 request.getSession().setAttribute("AddResult",result);	
	}

}
