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
import VO.TechnicalChart.MACD_VO;
import web.bl.stockImpl.TechnicalChartImpl;
import web.blservice.stockInfo.TechnicalChartInfo;

/**
 * Servlet implementation class MACDservlet
 */
@WebServlet("/MACDservlet")
public class MACDservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MACDservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
		 String[][] historyData=sv.getHistoryData();
		 int length=historyData.length;
		 double close[]=new double[length];
		 for(int i=0;i<length;i++){	
			 close[i]=Double.parseDouble(historyData[i][2]);
		 }
		 
		 TechnicalChartInfo technicalChartInfo=new TechnicalChartImpl();
		 MACD_VO macd_Vo=technicalChartInfo.getMACD(close);
		 
		 double DIFs[]=macd_Vo.getDIFs();
		 double DEAs[]=macd_Vo.getDEAs();
		 double DIFFs[]=macd_Vo.getDIFFs();
		 
		String data="[";
		for (int i = DIFFs.length-1; i >=0; i--) {
			data=data+"{'date':"+historyData[i][0]+
					",'DIF':"+DIFs[i]+
					",'DEA':"+DEAs[i]+
					",'DIFF':"+DIFFs[i]+"},";
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
