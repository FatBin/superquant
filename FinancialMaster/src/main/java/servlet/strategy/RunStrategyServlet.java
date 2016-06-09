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

import PO.StrategyPO;
import PO.profitPO;
import web.bl.StrategyHandle.StrategyHandle;
import web.blservice.StrategyHandleService.StrategyHandleService;;

/**
 * Servlet implementation class RunStrategyServlet
 */
@WebServlet("/RunStrategyServlet")
public class RunStrategyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<profitPO> profitPOs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RunStrategyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data = "[";
		for (profitPO profitPO : profitPOs) {
			data = data + "{'date':'" + profitPO.getDate() + "','profit':" + profitPO.getProfit() + "},";
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
		// String name=request.getParameter("stName");//策略名
		// String totalcost=request.getParameter("totalcost");//总成本
		String perST = request.getParameter("perST");
		String BuyList = request.getParameter("BuyList");
		String SoldList = request.getParameter("SoldList");

		String[] perSTs = perST.split("|");
		String[] BuyLists = BuyList.split("|");
		String[] SoldLists = SoldList.split("|");

		int size = perSTs.length;

		ArrayList<StrategyPO> arrayList_buy = new ArrayList<StrategyPO>();
		ArrayList<StrategyPO> arrayList_sold = new ArrayList<StrategyPO>();

		for (int i = 0; i < size; i++) {
			String[] per = perSTs[i].split(",");// 股票名称、投资成本、开始日期、结束日期、买卖频率
			String[] buy = BuyLists[i].split(",");// 价格区间、成交量区间、换手率区间、pe区间、pb区间
			String[] sold = SoldLists[i].split(",");
			StrategyPO buyPO = new StrategyPO(per[0], per[2], per[3], Double.parseDouble(buy[0]),
					Double.parseDouble(buy[1]), Double.parseDouble(buy[2]), Double.parseDouble(buy[3]),
					Double.parseDouble(buy[4]), Double.parseDouble(buy[5]), Double.parseDouble(buy[6]),
					Double.parseDouble(buy[7]), Double.parseDouble(buy[8]), Double.parseDouble(buy[9]),
					Integer.parseInt(per[4]), Double.parseDouble(per[1]));
			arrayList_buy.add(buyPO);
			StrategyPO soldPO = new StrategyPO(per[0], per[2], per[3], Double.parseDouble(sold[0]),
					Double.parseDouble(sold[1]), Double.parseDouble(sold[2]), Double.parseDouble(sold[3]),
					Double.parseDouble(sold[4]), Double.parseDouble(sold[5]), Double.parseDouble(sold[6]),
					Double.parseDouble(sold[7]), Double.parseDouble(sold[8]), Double.parseDouble(sold[9]),
					Integer.parseInt(per[4]), Double.parseDouble(per[1]));
			arrayList_sold.add(soldPO);
		}

		StrategyHandleService strategyHandleService = new StrategyHandle();

		profitPOs = strategyHandleService.handle(arrayList_buy, arrayList_sold);
		String data = "[]";

		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();

	}

}
