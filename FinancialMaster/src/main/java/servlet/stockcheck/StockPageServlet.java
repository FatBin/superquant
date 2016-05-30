package servlet.stockcheck;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.pojo.Stock;
import VO.StockListVO;
import VO.UserVO;
import servlet.factory.InitFactoryServlet;
import web.bl.stockImpl.StockListImpl;
import web.blservice.stockInfo.StockListInfo;

/**
 * Servlet implementation class StockPageServlet
 */
@WebServlet("/StockPageServlet")
public class StockPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StockListVO stockListVO = new StockListVO();
		StockListInfo stockListInfo=new StockListImpl();	
		stockListVO=stockListInfo.getStockList();
		request.getSession().setAttribute("StockList", stockListVO);		
		response.sendRedirect(request.getContextPath()+"/Web_Pages/StockPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
