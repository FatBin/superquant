package servlet.stockcheck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import VO.Analyze_ResultVO;
import VO.StockDetailVO;

/**
 * Servlet implementation class GetStockDashboardServlet
 */
@WebServlet("/GetStockDashboardServlet")
public class GetStockDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStockDashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
	     //		日期、开盘价、收盘价、最高价、最低价、后复权价、成交量、换手率、市盈率、市净率
		 Analyze_ResultVO analyze_ResultVO=sv.getAnalyze_ResultVO();

		 
		 int score[]=new int[5];
		 score[0]=(int) analyze_ResultVO.getScore_of_technical_analyze();
		 score[1]=(int) analyze_ResultVO.getScore_of_bench_analyze();
		 score[2]=(int) analyze_ResultVO.getScore_of_business_analyze();
		 score[3]=(int) analyze_ResultVO.getScore_of_basic_analyze();
		 score[4]=(int) analyze_ResultVO.getScore_of_inflows_analyze();
		 
		 String data="[";
		 for (int i = 0; i < 5; i++) {
			data+="{'value':"+score[i]+
				",'auxiliary':"+(20-score[i])+
				"},";
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
		 Analyze_ResultVO analyze_ResultVO=sv.getAnalyze_ResultVO();

			String data="[{'score':"+(int)analyze_ResultVO.getScore_of_comprehensive_analyze()+"}]";
			JSONArray json = new JSONArray(data);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();	
	}

}
