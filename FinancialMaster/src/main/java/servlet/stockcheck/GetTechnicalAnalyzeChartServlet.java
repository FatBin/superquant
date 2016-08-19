package servlet.stockcheck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.loader.DefaultEntityAliases;
import org.json.JSONArray;

import VO.Analyze_TechnicalVO;
import VO.StockDetailVO;

/**
 * Servlet implementation class GetTechnicalAnalyzeChartServlet
 */
@WebServlet("/GetTechnicalAnalyzeChartServlet")
public class GetTechnicalAnalyzeChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTechnicalAnalyzeChartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	
		
		 
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
	     Analyze_TechnicalVO analyze_TechnicalVO=sv.getAnalyze_TechnicalVO();
	     double[] price_MA=analyze_TechnicalVO.getPrice_MA();
	 	 double[] volume_MA=analyze_TechnicalVO.getVolume_MA();
	 	 double[] RSI=analyze_TechnicalVO.getRSI();
	     
	     

			String data="[";
			for(int i=0;i<3;i++){
				data=data+"{'price_MA':"+price_MA[i]+
						",'volume_MA':"+volume_MA[i]+
						",'RSI':"+RSI[i]+"},";
			}
			data+="]";
			JSONArray json = new JSONArray(data);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();	
	}

	
}
