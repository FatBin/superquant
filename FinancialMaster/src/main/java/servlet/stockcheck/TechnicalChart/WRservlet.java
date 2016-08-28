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

/**
 * Servlet implementation class WRservlet
 */
@WebServlet("/WRservlet")
public class WRservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WRservlet() {
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
	 double wr9[]=getWR(9, close, high, low);
	 double wr14[]=getWR(14, close, high, low);
	 double wr20[]=getWR(20, close, high, low);
	 
    
 	 
	String data="[";
	for (int i = wr20.length-1; i >=0; i--) {
		data=data+"{'date':"+historyData[i][0]+
				",'WR9':"+wr9[i]+
				",'WR14':"+wr14[i]+
				",'WR20':"+wr20[i]+"},";
	}
	data+="]";
	JSONArray json = new JSONArray(data);
	PrintWriter out = response.getWriter();
	out.println(json);
	out.flush();
	out.close();
	}

	
	private double[] getWR(int N,double close[],double high[],double low[]) {
		int length=close.length-N+1;
		double[] result=new double[length];
		
		 for (int i = 0; i < length; i++) {
				double hn=Double.MIN_VALUE;
				double ln=Double.MAX_VALUE;
				for (int l = 0; l < N; l++) {
					if(high[i+l]>hn){
						hn=high[i+l];
					}
					if(low[i+l]<ln){
						ln=low[i+l];
					}
				}
				
				result[i]=100-(close[i]-ln)/(hn-ln)*100;			
				
			}	
				
		return result;
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
