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
import VO.TechnicalChart.ROC_VO;
import web.bl.stockImpl.TechnicalChartImpl;
import web.blservice.stockInfo.TechnicalChartInfo;

/**
 * Servlet implementation class ROCservlet
 */
@WebServlet("/ROCservlet")
public class ROCservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ROCservlet() {
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
	 ROC_VO roc_Vo=technicalChartInfo.getROC(close);
	 double roc12[]=roc_Vo.getRoc12();
	 double rocMA[]=roc_Vo.getRocMA();
	 double rocEMA[]=roc_Vo.getRocEMA();		 
	 
	String data="[";
	for (int i = rocEMA.length-1; i >=0; i--) {
		data=data+"{'date':"+historyData[i][0]+
				",'ROC12':"+roc12[i]+
				",'ROCMA':"+rocMA[i]+
				",'ROCEMA':"+rocEMA[i]+"},";
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
