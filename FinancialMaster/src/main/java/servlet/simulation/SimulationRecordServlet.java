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
import VO.SimulationRecordVO;
import VO.SimulationStockVO;
import VO.UserVO;
import servlet.factory.InitFactoryServlet;
import web.bl.simulationImpl.HistoryRecordImpl;
import web.bl.simulationImpl.SimulationStockImpl;
import web.blservice.simulationInfo.HistoryRecordInfo;
import web.blservice.simulationInfo.SimulationStockInfo;

/**
 * Servlet implementation class SimulationRecordServlet
 */
@WebServlet("/SimulationRecordServlet")
public class SimulationRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimulationRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserVO userVO = (UserVO) request.getSession().getAttribute("User");
		String userID = userVO.getUsername();
		HistoryRecordInfo historyRecordInfo=new HistoryRecordImpl();
		ArrayList<SimulationRecordVO> simulationRecordVOs = historyRecordInfo.getHistoryRecord(userID);
		String data = "[";
		for (SimulationRecordVO simulationRecordVO : simulationRecordVOs) {
			String stockID=simulationRecordVO.getStockID();
			Stock stock=InitFactoryServlet.getStock(stockID);
			data += "{'id':'" + simulationRecordVO.getId() + 
					"','stockID':'" + stockID + 
					"','stockName':'" + stock.getStockName() + 
					"','time':'"+ simulationRecordVO.getTime() +
					"','deal':'" + simulationRecordVO.getDeal() + 
					"','money':" + simulationRecordVO.getMoney()
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
		HistoryRecordInfo historyRecordInfo=new HistoryRecordImpl();
		String id=request.getParameter("id");
		String data="[]";
		if(order.equals("Hide")){
			ManageState result=historyRecordInfo.hideRecord(id);
			data="[{'HideResult':"+result+"}]";				
		}else{
			ManageState result=historyRecordInfo.deleteRecord(id);
			data="[{'DeleteResult':"+result+"}]";
		}
		
		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();	
	}

}
