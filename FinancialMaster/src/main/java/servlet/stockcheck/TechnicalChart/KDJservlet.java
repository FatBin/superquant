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
	 int result_length=length-8;
	 double rsv[]=new double[result_length];
	 double k[]=new double[result_length];
	 double d[]=new double[result_length];
	 double j[]=new double[result_length];
	 
	 for (int i = 0; i < result_length; i++) {
		double h9=Double.MIN_VALUE;
		double l9=Double.MAX_VALUE;
		for (int l = 0; l < 9; l++) {
			if(high[i+l]>h9){
				h9=high[i+l];
			}
			if(low[i+l]<l9){
				l9=low[i+l];
			}
		}
		
		rsv[i]=(close[i]-l9)/(h9-l9)*100;			
		
	}	 
	 
    k[result_length-1]=50.0*2/3+rsv[result_length-1]/3;
    d[result_length-1]=50.0*2/3+k[result_length-1]/3;
    j[result_length-1]=3*k[result_length-1]-2*d[result_length-1];
    
	for (int i = result_length-2; i >=0; i--) {
		k[i]=k[i+1]*2/3+rsv[i]/3;
	    d[i]=d[i+1]*2/3+k[i]/3;
	    j[i]=3*k[i]-2*d[i];
		
	}
    
 	 
	String data="[";
	for (int i = result_length-1; i >=0; i--) {
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
