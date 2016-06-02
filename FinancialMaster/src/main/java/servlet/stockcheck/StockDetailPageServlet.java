package servlet.stockcheck;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import VO.StockDetailVO;
import web.bl.stockImpl.StockImpl;
import web.blservice.stockInfo.StockInfo;

/**
 * Servlet implementation class StockDetailPageServlet
 */
@WebServlet("/StockDetailPageServlet")
public class StockDetailPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockDetailPageServlet() {
        super();        
    }

    
	@Override
	public void init() throws ServletException {
		super.init();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          response.sendRedirect(request.getContextPath()+"/Web_Pages/StockDetailPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("Stockid");
		StockInfo stockInfo=new StockImpl();
		StockDetailVO detailVO=stockInfo.getStock(id);
		request.getSession().setAttribute("StockDetail", detailVO);
	}

}
