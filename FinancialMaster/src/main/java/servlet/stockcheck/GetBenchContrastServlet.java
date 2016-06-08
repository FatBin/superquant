package servlet.stockcheck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import VO.StockDetailVO;

/**
 * Servlet implementation class GetBenchContrastServlet
 */
@WebServlet("/GetBenchContrastServlet")
public class GetBenchContrastServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBenchContrastServlet() {
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
	     //		日期、开盘价、收盘价、最高价、最低价、后复权价、成交量、换手率、市盈率、市净率
			String[][] historyData=sv.getHistoryData();
			double[] rise_falls=sv.getRise_fallList();
			double[] bench_rise_falls=sv.getBench_rise_fallList();
			int size=historyData.length;
			String data="[";
			for(int i=size-2;i>=0;i--){
				data=data+"{'date':'"+historyData[i][0]+
						"','rise_fall':"+rise_falls[i]+
						",'bench_rise_fall':"+bench_rise_falls[i]+"},";
			}
			data+="]";

			JSONArray json = new JSONArray(data);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();	
		
		
		
	}

}
