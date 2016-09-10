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
import VO.TechnicalChart.BIAS_VO;
import web.bl.stockImpl.TechnicalChartImpl;
import web.blservice.stockInfo.TechnicalChartInfo;

/**
 * Servlet implementation class BIASservlet
 */
@WebServlet("/BIASservlet")
public class BIASservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BIASservlet() {
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
	 for(int i=0;i<length;i++){	
		 close[i]=Double.parseDouble(historyData[i][2]);
	 }
	 
	 TechnicalChartInfo technicalChartInfo=new TechnicalChartImpl();
	 BIAS_VO bias_VO=technicalChartInfo.getBIAS(close);
	 
	 double BIAS6[]=bias_VO.getBIAS6();
	 double BIAS12[]=bias_VO.getBIAS12();
	 double BIAS24[]=bias_VO.getBIAS24();
	 
	 
	String data="[";
	for (int i = BIAS24.length-1; i >=0; i--) {
		data=data+"{'date':"+historyData[i][0]+
				",'BIAS6':"+BIAS6[i]+
				",'BIAS12':"+BIAS12[i]+
				",'BIAS24':"+BIAS24[i]+"},";
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
