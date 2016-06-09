package servlet.stockmarket;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import VO.BenchVO;
import web.bl.benchImpl.BenchImpl;
import web.blservice.benchInfo.BenchUpdateInfo;

/**
 * Servlet implementation class UpdateBenchVOServlet
 */
@WebServlet("/UpdateBenchVOServlet")
public class UpdateBenchVOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBenchVOServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *更新大盘最新信息时调用
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String benchName=(String) request.getSession().getAttribute("BenchMarketName");
		BenchVO sv=(BenchVO)request.getSession().getAttribute("BenchMarket");
		BenchUpdateInfo benchUpdateInfo=new BenchImpl();
		benchUpdateInfo.update(sv, benchName);
		
		response.setContentType("text/html;charset=utf-8");
		String data="[{'status':'"+sv.getStatus()+
				"','time':'"+sv.getTime()+
				"','now':"+sv.getNow()+
				",'rise_fall_price':"+sv.getRise_fall_price()+
				",'rise_fall_percent':"+sv.getRise_fall_percent()+
				",'high':"+sv.getHigh()+
				",'low':"+sv.getLow()+
				",'open':"+sv.getOpen()+
				",'yesterday_close':"+sv.getYesterday_close()+
				",'price':"+sv.getPrice()+
				",'volume':'"+sv.getVolume()+
				"','rise_company':'"+sv.getRise_company()+
				"','fall_company':'"+sv.getFall_company()+
				"','company':'"+sv.getCompany()+ "'}]";

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
		response.setContentType("text/html;charset=utf-8");
		BenchVO sv=(BenchVO)request.getSession().getAttribute("BenchMarket");
		 
			String data="[{'status':'"+sv.getStatus()+
					"','time':'"+sv.getTime()+
					"','now':"+sv.getNow()+
					",'rise_fall_price':"+sv.getRise_fall_price()+
					",'rise_fall_percent':"+sv.getRise_fall_percent()+
					",'high':"+sv.getHigh()+
					",'low':"+sv.getLow()+
					",'open':"+sv.getOpen()+
					",'yesterday_close':"+sv.getYesterday_close()+
					",'price':"+sv.getPrice()+
					",'volume':'"+sv.getVolume()+
					"','rise_company':'"+sv.getRise_company()+
					"','fall_company':'"+sv.getFall_company()+
					"','company':'"+sv.getCompany()+ "'}]";

			JSONArray json = new JSONArray(data);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
	}

}
