package servlet.stockcheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import VO.BenchVO;
import VO.StockDetailVO;
import VO.StockRecordVO;

/**
 * Servlet implementation class GetStockKLineServlet
 */
@WebServlet("/GetStockKLineServlet")
public class GetStockKLineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStockKLineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
	     //		日期、开盘价、收盘价、最高价、最低价、后复权价、成交量、换手率、市盈率、市净率
			String[][] historyData=sv.getHistoryData();
			double[] rise_falls=sv.getRise_fallList();
			int size=historyData.length;
			String data="[";
			for(int i=size-2;i>=0;i--){
				data=data+"{'date':'"+historyData[i][0]+
						"','rise_fall':"+rise_falls[i]+
						",'turnover':"+historyData[i][7]+
						",'pe':"+historyData[i][8]+
						",'pb':"+historyData[i][9]+"},";
			}
			data+="]";

			JSONArray json = new JSONArray(data);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     	StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
     //		日期、开盘价、收盘价、最高价、最低价、后复权价、成交量、换手率、市盈率、市净率
		String[][] historyData=sv.getHistoryData();
		int size=historyData.length;
		String data="[";
		for(int i=size-1;i>=0;i--){
			data=data+"{'date':'"+historyData[i][0]+"','value':["+
					historyData[i][1]+","+historyData[i][2]+","+
					historyData[i][4]+","+historyData[i][3]+"],'volume':"+
					historyData[i][6]+"},";
		}
		data+="]";

		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
