package servlet.stockcheck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import PO.UpToDateStockPO;
import VO.Analyze_BasicItemsVO;
import VO.StockDetailVO;

/**
 * Servlet implementation class GetStockRadarChartServlet
 */
@WebServlet("/GetStockRadarChartServlet")
public class GetStockRadarChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStockRadarChartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
		Analyze_BasicItemsVO analyze_BasicItemsVO=sv.getAnalyze_BasicItemsVO();
		double[] maxs={2,1,7,5,40,10};
		double[] values=new double[6];
		values[0]=analyze_BasicItemsVO.getQuantity_relative_ratio();
		values[1]=analyze_BasicItemsVO.getPriceStability();
		values[2]=analyze_BasicItemsVO.getTurnOver();
		values[3]=analyze_BasicItemsVO.getUps_and_downs();
		values[4]=analyze_BasicItemsVO.getPe();
		values[5]=analyze_BasicItemsVO.getPb();
		
		for (int i = 0; i < 6; i++) {
			if(values[i]>maxs[i]){
				maxs[i]=values[i];
			}
		}
		if(values[3]<0){
			maxs[3]=100;
		}
		String data="[{'max':"+maxs[0]+",'value':"+values[0]+
					"},{'max':"+maxs[1]+",'value':"+values[1]+
					"},{'max':"+maxs[2]+",'value':"+values[2]+
					"},{'max':"+maxs[3]+",'value':"+values[3]+
					"},{'max':"+maxs[4]+",'value':"+values[4]+
					"},{'max':"+maxs[5]+",'value':"+values[5]+"}]";
		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
