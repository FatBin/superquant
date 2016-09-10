package servlet.stockcheck.TechnicalChart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import VO.StockDetailVO;
import VO.TechnicalChart.KDJ_VO;
import web.bl.stockImpl.TechnicalChartImpl;
import web.blservice.stockInfo.TechnicalChartInfo;

/**
 * Servlet implementation class KDJservlet
 */
@WebServlet("/KDJservlet")
public class KDJservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KDJservlet() {
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
	 int length=historyData.length;
	 double close[]=new double[length];
	 double high[]=new double[length];
	 double low[]=new double[length];
	 for(int i=0;i<length;i++){	
		 close[i]=Double.parseDouble(historyData[i][2]);
         high[i]=Double.parseDouble(historyData[i][3]);
         low[i]=Double.parseDouble(historyData[i][4]);
	 }

	 TechnicalChartInfo technicalChartInfo=new TechnicalChartImpl();
	 KDJ_VO kdj_VO=technicalChartInfo.getKDJ(close, high, low);
	 
	double k[]=kdj_VO.getK();
	double d[]=kdj_VO.getD();
	double j[]=kdj_VO.getJ();
	 
	String data="[";
	for (int i = length-9; i >=0; i--) {
		data=data+"{'date':"+historyData[i][0]+
				",'K':"+k[i]+
				",'D':"+d[i]+
				",'J':"+j[i]+"},";
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
