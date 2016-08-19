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
		 double PDM[]=new double[length-1];
		 double MDM[]=new double[length-1];
		 double TR[]=new double[length-1];
		 
		 double A[]=new double[3];
		 for (int i = 0; i < length-1; i++) {
			PDM[i]=high[i]>high[i+1]?high[i]-high[i+1]:0;
			MDM[i]=low[i]>low[i+1]?low[i]-low[i+1]:0;
			if(PDM[i]!=0&&PDM[i]==MDM[i]){
				PDM[i]=0;
				MDM[i]=0;
			}
			TR[i]=0;
			A[0]=Math.abs(high[i]-low[i+1]);
			A[1]=Math.abs(high[i]-close[i+1]);
			A[2]=Math.abs(low[i]-close[i+1]);
			for (int j = 0; j < 3; j++) {
				if(A[j]>TR[i]){
					TR[i]=A[j];
				}
			}
			
			
		}	 
		 
		 double Psum=0;
		 double Msum=0;
		 double Tsum=0;
		 for (int i = 0; i < 12; i++) {
			 Psum+=PDM[i];
			 Msum+=MDM[i];
		}
		 double PDI12[]=new double[length-12];
		 double MDI12[]=new double[length-12];
		 PDI12[0]=Tsum==0?0:Psum/(12*Tsum);
		 MDI12[0]=Tsum==0?0:Msum/(12*Tsum);
		 for (int i = 1; i < length-12; i++) {
			 Tsum=-TR[i-1]+TR[i+11];
			PDI12[i]=Tsum==0?0:(Psum-PDM[i-1]+PDM[i+11])/(12*Tsum);
			MDI12[i]=Tsum==0?0:(Msum-MDM[i-1]+MDM[i+11])/(12*Tsum);
		}
		 
		 double ADX12[]=new double[length-12];
		 for (int i = 0; i < length-12; i++) {
			ADX12[i]=PDI12[i]==0?0:(Math.abs(PDI12[i]-MDI12[i])/(PDI12[i]+MDI12[i]))*100;
		}

		 double ADXR[]=new double[length-13];
		 for (int i = 0; i < length-13; i++) {
			 ADXR[i]=(ADX12[i]+ADX12[i+1])/2;
		 }
		 
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
