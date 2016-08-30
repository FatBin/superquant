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
 * Servlet implementation class CCIservlet
 */
@WebServlet("/CCIservlet")
public class CCIservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CCIservlet() {
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
	
	 length-=6;
	 double cci[]=new double[length];
	 double tp,ma,md;
	 for (int i = 0; i < length; i++) {
		tp=(close[i]+high[i]+low[i])/3;
		ma=0;
		for (int j = 0; j < 7; j++) {
			ma+=close[i+j];			
		}
		ma/=7;
		md=0;
		for (int j = 0; j < 7; j++) {
			md+=Math.abs(close[i+j]-ma);			
		}
		md/=7;
		
		cci[i]=(tp-ma)/md/0.015;
	}
	 
	length-=5;
	double macci[]=new double[length];
	
	for (int i = 0; i < length; i++) {
		macci[i]=0;
		for (int j = 0; j < 6; j++) {
			macci[i]+=cci[i+j];
		}
		macci[i]/=6;
	}
	 
	String data="[";
	for (int i = length-1; i >=0; i--) {
		data=data+"{'date':"+historyData[i][0]+
				",'CCI':"+cci[i]+
				",'CCIMA':"+macci[i]+"},";
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
