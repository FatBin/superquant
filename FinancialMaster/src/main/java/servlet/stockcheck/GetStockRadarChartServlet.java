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
import VO.Analyze_BasicItemsVO;
import VO.StockDetailVO;

/**
 * Servlet implementation class GetStockRadarChartServlet
 */
@WebServlet("/GetStockRadarChartServlet")
public class GetStockRadarChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStockRadarChartServlet() {
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
		Analyze_BasicItemsVO analyze_BasicItemsVO=sv.getAnalyze_BasicItemsVO();
		
		String data="[{'value':"+analyze_BasicItemsVO.getQuantity_relative_ratio()+
					"},{'value':"+analyze_BasicItemsVO.getPriceStability()+
					"},{'value':"+analyze_BasicItemsVO.getTurnOver()+
					"},{'value':"+analyze_BasicItemsVO.getUps_and_downs()+
					"},{'value':"+analyze_BasicItemsVO.getPe()+
					"},{'value':"+analyze_BasicItemsVO.getPb()+"}]";
		
		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
