package servlet.stockmarket;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import ENUM.date_enum;
import VO.StockMarketVO;
import businesslogic.factory.InitFactory;
import businesslogic.stockmarketbl.StockMarketBL;
import businesslogic.stockmarketbl.StockMarketInfo;
import businesslogicservice.stockmarketblservice.StockMarketBLService;

/**
 * Servlet implementation class MarketServlet
 */
@WebServlet("/MarketServlet")
public class MarketPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StockMarketVO sv;   
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
		StockMarketBLService sb=new StockMarketBL();
		sv=sb.getStockMarket("hs300", date_enum.TenYear);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getSession().setAttribute("BenchMarket", sv);
		response.sendRedirect(request.getContextPath()+"/Web_Pages/MarketPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("id"));
		if(request.getParameter("id").equals("hs300")){
			String historydata[][]=sv.getData();
			String data="[";
			for (int i = historydata.length-1; i >=0 ; i--) {
				data=data+"{'date':'"+historydata[i][0]+"','value':["+
						historydata[i][1]+","+historydata[i][4]+","+
						historydata[i][3]+","+historydata[i][2]+"],'volume':"+
						historydata[i][5]+"},";
			}
			data+="]";

			JSONArray json = new JSONArray(data);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		}
	}

}
