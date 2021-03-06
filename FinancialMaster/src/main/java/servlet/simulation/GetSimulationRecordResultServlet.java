package servlet.simulation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import VO.UserVO;
import web.bl.simulationImpl.HistoryRecordImpl;
import web.bl.simulationImpl.SimulationStockImpl;
import web.blservice.simulationInfo.HistoryRecordInfo;
import web.blservice.simulationInfo.SimulationStockInfo;

/**
 * Servlet implementation class GetSimulationRecordResultServlet
 */
@WebServlet("/GetSimulationRecordResultServlet")
public class GetSimulationRecordResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetSimulationRecordResultServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HistoryRecordInfo historyRecordInfo = new HistoryRecordImpl();
		UserVO userVO = (UserVO) request.getSession().getAttribute("User");
		String userID = userVO.getUsername();
		Double result=historyRecordInfo.getCalculateResult(userID);
		String data = "[{'CalculateResult':" + result + "}]";

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
