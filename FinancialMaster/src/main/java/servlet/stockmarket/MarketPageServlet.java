package servlet.stockmarket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import VO.BenchListVO;
import VO.BenchVO;
import web.bl.benchImpl.BenchImpl;
import web.blservice.benchInfo.BenchInfo;

/**
 * Servlet implementation class MarketServlet
 */
@WebServlet("/MarketServlet")
public class MarketPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BenchVO sv; 
	BenchInfo bench;
	BenchListVO benchListVO;
	String benchName;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init() throws ServletException {
		super.init();
		bench=new BenchImpl();
		benchListVO=bench.getBenchCode();
		String[] benchlist=benchListVO.getBenchList();
		benchName=benchlist[0];
		sv=bench.getStockMarket(benchName);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getSession().setAttribute("BenchList", benchListVO);
		request.getSession().setAttribute("BenchMarket", sv);
		request.getSession().setAttribute("BenchMarketName", benchName);
		response.sendRedirect(request.getContextPath()+"/Web_Pages/MarketPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("benchName");
		if(benchName.equals(name)){
			return;
		}
		benchName=name;
		request.getSession().setAttribute("BenchMarketName", benchName);
		sv=bench.getStockMarket(benchName);
		request.getSession().setAttribute("BenchMarket", sv);
	}

}
