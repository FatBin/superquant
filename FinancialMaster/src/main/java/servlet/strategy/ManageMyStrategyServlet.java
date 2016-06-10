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

import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;
import ENUM.ManageState;
import PO.StrategyPO;
import VO.StrategyVO;
import VO.UserVO;
import web.bl.StrategyHandle.StrategyHandle;
import web.bl.userImpl.UserManageImpl;
import web.blservice.StrategyHandleService.StrategyHandleService;
import web.blservice.userInfo.UserManageInfo;

/**
 * Servlet implementation class ManageMyStrategyServlet
 */
@WebServlet("/ManageMyStrategyServlet")
public class ManageMyStrategyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageMyStrategyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
	  ManageState result=ManageState.Unlogin;
	  if(request.getSession().getAttribute("User")!=null){
		  UserVO userVO=(UserVO)request.getSession().getAttribute("User"); 
		  
		  String name=request.getParameter("stName");//策略名
          String totalcost=request.getParameter("totalcost");//总成本
		  String perST=request.getParameter("perST");
		  String BuyList=request.getParameter("BuyList");
		  String SoldList=request.getParameter("SoldList");
		  
	      String[] perSTs=perST.split(";");
	      String[] BuyLists=BuyList.split(";");
	      String[] SoldLists=SoldList.split(";");
	      
	      int size=perSTs.length;
		
	      StrategyVO strategyVO=new StrategyVO();
	      strategyVO.setStrategyName(name);
	      ArrayList<UserStrategy> userStrategies=new ArrayList<UserStrategy>();
	      
	      for (int i = 0; i < size; i++) {
				String[] per=perSTs[i].split(",");//股票名称、投资成本、开始日期、结束日期、买卖频率
				UserStrategy userStrategy=new UserStrategy();
				UserStrategyId userStrategyId=new UserStrategyId();
				userStrategyId.setStockId(per[i]);
				userStrategyId.setUserId(userVO.getUsername());
				userStrategyId.setStrategyName(name);
				userStrategy.setId(userStrategyId);
				userStrategy.setStarttime(per[2]);
				userStrategy.setEndtime(per[3]);
				userStrategy.setCost(Integer.parseInt(totalcost));
				userStrategy.setFrequency(Integer.parseInt(per[4]));
				userStrategy.setWeight(Double.parseDouble(per[1]));
				userStrategy.setBuystrategy(BuyLists[i]);
				userStrategy.setSellstrategy(SoldLists[i]);
				userStrategy.setOtherstrategy("");
				userStrategies.add(userStrategy);
					
			}
	      strategyVO.setUserStrategies(userStrategies);
	       UserManageInfo userManageInfo=new UserManageImpl();
	       result=userManageInfo.addStrategy(userVO, strategyVO);
		 }
	    String data="[{'SaveResult':"+result+"}]";
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
		  ManageState result=ManageState.Unlogin;
		  if(request.getSession().getAttribute("User")!=null){
			  UserVO userVO=(UserVO)request.getSession().getAttribute("User"); 
			  
			  String name=request.getParameter("stName");//策略名
	        
		       UserManageInfo userManageInfo=new UserManageImpl();
		       result=userManageInfo.deleteStrategy(userVO, name);
			 }
		    String data="[{'DeleteResult':"+result+"}]";
			JSONArray json = new JSONArray(data);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();	
	}

}
