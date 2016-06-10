package servlet.strategy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import DAO.pojo.Industries;
import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;
import VO.MyStrategyVO;
import VO.UserVO;
import web.bl.StrategyHandle.GetStrategyImpl;
import web.blservice.StrategyHandleService.GetStrategyInfo;

/**
 * Servlet implementation class StrategyPageServlet
 */
@WebServlet("/StrategyPageServlet")
public class StrategyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StrategyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("MyStrategy", null);
		response.sendRedirect(request.getContextPath()+"/Web_Pages/StrategyPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strategyName=request.getParameter("StrategyName");
		UserVO userVO=(UserVO)request.getSession().getAttribute("User");
		GetStrategyInfo getStrategyInfo=new GetStrategyImpl();
		List<UserStrategy> strategies=getStrategyInfo.getStrategy(userVO.getUsername(), strategyName);
		UserStrategy userStrategy=strategies.get(0);
		UserStrategyId userStrategyId=userStrategy.getId();
		String name=userStrategyId.getStrategyName();//策略名
	    String totalcost=userStrategy.getCost()+"";//总成本
		String perST="";//股票名称、投资成本、开始日期、结束日期、买卖频率
		String BuyList="";// 价格区间、成交量区间、换手率区间、pe区间、pb区间
		String SoldList="";
		
		for (UserStrategy userStrategy2 : strategies) {
			userStrategyId=userStrategy2.getId();
			perST+=userStrategyId.getStockId()+","+userStrategy2.getWeight()+","+
			       userStrategy2.getStarttime()+","+userStrategy2.getEndtime()+","+
					userStrategy2.getFrequency()+";";
			BuyList+=userStrategy2.getBuystrategy()+";";
			SoldList+=userStrategy2.getSellstrategy()+";";
		}
	
	       String data="[{'stName':'"+name+
					"','totalcost':'"+totalcost+
					"','perST':'"+perST+
					"','BuyList':'"+BuyList+
					"','SoldList':'"+SoldList+"'}]";
		
	       JSONArray json = new JSONArray(data);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
	
	}

}
