package servlet.strategy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import PO.profitPO;
import web.bl.StrategyHandle.PerfectStrategyHandle;
import web.blservice.StrategyHandleService.perfectStrategyService;

/**
 * Servlet implementation class PerfectStrategyServlet
 */
@WebServlet("/PerfectStrategyServlet")
public class PerfectStrategyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerfectStrategyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		perfectStrategyService psService=new PerfectStrategyHandle();
		String stockId=request.getParameter("stockId");
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		String cost=request.getParameter("cost");
		ArrayList<profitPO> result=psService.getProfit(stockId, startTime, endTime, Double.parseDouble(cost));
	    int size=result.size();
	    profitPO po=result.get(size-1);
	    double profit=po.getProfit();
	    
	    String data = "[{'profit':" +  profit + "}]";
		
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
