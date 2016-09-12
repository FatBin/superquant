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
import PO.profitPO;
import VO.SimulationStockVO;
import VO.UserVO;
import servlet.factory.InitFactoryServlet;
import web.bl.simulationImpl.SimulationStockImpl;
import web.blservice.simulationInfo.SimulationStockInfo;

/**
 * Servlet implementation class SimulationStockServlet
 */
@WebServlet("/SimulationStockServlet")
public class SimulationStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SimulationStockServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		UserVO userVO = (UserVO) request.getSession().getAttribute("User");
		String userID = userVO.getUsername();
		SimulationStockInfo simulationStockInfo = new SimulationStockImpl();
		ArrayList<SimulationStockVO> simulationStockVOs = simulationStockInfo.getStockList(userID);
		String data = "[";
		for (SimulationStockVO simulationStockVO : simulationStockVOs) {
			String stockID=simulationStockVO.getStockID();
			Stock stock=InitFactoryServlet.getStock(stockID);
			data += "{'id':'" + simulationStockVO.getId() + 
					"','stockID':'" + stockID + 
					"','stockName':'" + stock.getStockName() + 
					"','time':'"+ simulationStockVO.getTime() +
					"','price':'" + simulationStockVO.getPrice() + 
					"','number':'"+ simulationStockVO.getNumber() + 
					"','now':'"+ 22.5 + 
					"','profitability':" + simulationStockVO.getProfitability()
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String order=request.getParameter("Order");
		SimulationStockInfo simulationStockInfo=new SimulationStockImpl();
		String data="[]";
		if(order.equals("Buy")){
			UserVO userVO = (UserVO) request.getSession().getAttribute("User");
			String userID = userVO.getUsername();
			String stockID = request.getParameter("StockID");
			String number = request.getParameter("Number");
			SimulationStockVO simulationStockVO=new SimulationStockVO();
			simulationStockVO.setStockID(stockID);
			simulationStockVO.setUserID(userID);
			simulationStockVO.setNumber(Long.parseLong(number));
			int result=simulationStockInfo.buyStock(simulationStockVO);
			data="[{'BuyResult':"+result+"}]";
				
		}else{
			String id=request.getParameter("id");
			ManageState result=simulationStockInfo.sellStock(id);
			data="[{'SellResult':"+result+"}]";
		}
		
		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();	
	}

}
