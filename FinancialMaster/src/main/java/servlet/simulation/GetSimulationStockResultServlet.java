package servlet.simulation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import PO.profitPO;
import web.bl.simulationImpl.SimulationStockImpl;
import web.blservice.simulationInfo.SimulationStockInfo;

/**
 * Servlet implementation class GetSimulationStockChartServlet
 */
@WebServlet("/GetSimulationStockChartServlet")
public class GetSimulationStockResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSimulationStockResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SimulationStockInfo simulationStockInfo=new SimulationStockImpl();
		String id=request.getParameter("id");
		String result[][]=simulationStockInfo.getResuleDetail(id);
		
		String data = "[";
		for (int i=0;i<result.length;i++) {
			data = data + "{'date':'" + result[i][0]+ "','profit':" + result[i][1] + "},";
		}
		data += "]";

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
