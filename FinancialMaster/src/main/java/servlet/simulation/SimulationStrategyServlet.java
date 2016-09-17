package servlet.simulation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import DAO.pojo.Stock;
import ENUM.ManageState;
import VO.SimulationStockVO;
import VO.SimulationStrategyVO;
import VO.UserVO;
import servlet.factory.InitFactoryServlet;
import web.bl.simulationImpl.SimulationStockImpl;
import web.bl.simulationImpl.SimulationStrategyImpl;
import web.blservice.simulationInfo.SimulationStockInfo;
import web.blservice.simulationInfo.SimulationStrategyInfo;

/**
 * Servlet implementation class SimulationStrategyServlet
 */
@WebServlet("/SimulationStrategyServlet")
public class SimulationStrategyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimulationStrategyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		UserVO userVO = (UserVO) request.getSession().getAttribute("User");
		String userID = userVO.getUsername();
		SimulationStrategyInfo simulationStrategyInfo=new SimulationStrategyImpl();
		ArrayList<SimulationStrategyVO> simulationStrategyVOs=simulationStrategyInfo.getAllSimulationStrategy(userID);
		String data = "[";
		for (SimulationStrategyVO simulationStrategyVO : simulationStrategyVOs) {
			String stockID=simulationStrategyVO.getStockID();
			Stock stock=InitFactoryServlet.getStock(stockID);
			data += "{'id':'" + simulationStrategyVO.getId() + 
					"','stockID':'" + stockID + 
					"','stockName':'" + stock.getStockName() + 
					"','time':'"+ simulationStrategyVO.getStartTime() +
					"','strategyName':'" + simulationStrategyVO.getStrategyName() + 
					"','now':'"+ simulationStrategyVO.getNow() + 
					"','profitability':" + simulationStrategyVO.getProfit()
					+ "},";
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
		String order=request.getParameter("Order");
		SimulationStrategyInfo simulationStrategyInfo=new SimulationStrategyImpl();
		String data="[]";
		if(order.equals("Start")){
			UserVO userVO = (UserVO) request.getSession().getAttribute("User");
			String userID = userVO.getUsername();
			String stockID = request.getParameter("StockID");
			String strategyName = request.getParameter("StrategyName");
			
			SimulationStrategyVO simulationStrategyVO=new SimulationStrategyVO();
            simulationStrategyVO.setUserID(userID);
            simulationStrategyVO.setStockID(stockID);
            simulationStrategyVO.setStrategyName(strategyName);
			int result=simulationStrategyInfo.addSimulationStrategy(simulationStrategyVO);
			data="[{'StartStrategyResult':"+result+"}]";
				
		}else{
			String id=request.getParameter("id");
			ManageState result=simulationStrategyInfo.deleteSimulationStrategy(id);
			data="[{'EndStrategyResult':"+result+"}]";
		}
		
		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();	
	}

}
