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
import VO.TechnicalChart.DMI_VO;
import web.bl.stockImpl.TechnicalChartImpl;
import web.blservice.stockInfo.TechnicalChartInfo;

/**
 * Servlet implementation class DMIservlet
 */
@WebServlet("/DMIservlet")
public class DMIservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DMIservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
//			日期、开盘价、收盘价、最高价、最低价、后复权价、成交量、换手率、市盈率、市净率
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
		DMI_VO dmi_vo=technicalChartInfo.getDMI(close, high, low);
		 		 
		double PDI12[]=dmi_vo.getPDI12();
		double MDI12[]=dmi_vo.getMDI12();	 
		double ADX12[]=dmi_vo.getADX12();
		double ADXR[]=dmi_vo.getADXR();
		String data="[";
		for (int i = ADXR.length-1; i >=0; i--) {
			data=data+"{'date':"+historyData[i][0]+
					",'PDI':"+PDI12[i]+
					",'MDI':"+MDI12[i]+
					",'ADX':"+ADX12[i]+
					",'ADXR':"+ADXR[i]+"},";
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
