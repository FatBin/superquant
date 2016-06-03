package servlet.stockcheck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import PO.UpToDateStockPO;
import VO.StockDetailVO;

/**
 * Servlet implementation class GetStcokInflowPieChartServlet
 */
@WebServlet("/GetStcokInflowPieChartServlet")
public class GetStockInflowPieChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStockInflowPieChartServlet() {
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
		StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
		UpToDateStockPO upToDateStockPO=sv.getUpToDateMessage();
		
		String data="[{'inflow':["+
					upToDateStockPO.getExtraLargePurchase()+","+
					upToDateStockPO.getLargePurchase()+","+
					upToDateStockPO.getMediumPurchase()+","+
					upToDateStockPO.getSmallPurchase()+","+
					upToDateStockPO.getExtraLargeSell()+","+
					upToDateStockPO.getLargeSell()+","+
					upToDateStockPO.getMediumSell()+","+
					upToDateStockPO.getSmallSell()+"]}]";
		
		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
