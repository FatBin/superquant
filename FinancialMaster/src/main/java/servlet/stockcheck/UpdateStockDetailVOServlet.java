package servlet.stockcheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import PO.UpToDateStockPO;
import VO.StockDetailVO;
import web.bl.benchImpl.BenchImpl;
import web.bl.stockImpl.StockImpl;
import web.blservice.benchInfo.BenchUpdateInfo;
import web.blservice.stockInfo.StockUpdateInfo;

/**
 * Servlet implementation class UpdateStockDetailVOServlet
 */
@WebServlet("/UpdateStockDetailVOServlet")
public class UpdateStockDetailVOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStockDetailVOServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 *  
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
		UpToDateStockPO up=sv.getUpToDateMessage();
		StockUpdateInfo stockUpdateInfo =new StockImpl();
		UpToDateStockPO newMessage=stockUpdateInfo.update(up);
		sv.setUpToDateMessage(newMessage);

	}

	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
		UpToDateStockPO up=sv.getUpToDateMessage();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		BenchUpdateInfo benchUpdateInfo =new BenchImpl();
		String status=benchUpdateInfo.getState();
		 
			String data="[{'status':'"+status+
					"','time':'"+df.format(new Date())+
					"','now':"+up.getNow()+
					",'rise_fall':"+up.getRise_fall()+
					",'positive':"+up.getPositive()+
					",'tongchilv':"+up.getTongchilv()+
					",'turnover':"+up.getTurnover()+
					",'quantity_relative_ratio':"+up.getQuantity_relative_ratio()+"'}]";

			JSONArray json = new JSONArray(data);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
	}

}
