package servlet.stockcheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import VO.BenchVO;
import VO.StockDetailVO;
import VO.StockRecordVO;

/**
 * Servlet implementation class GetStockKLineServlet
 */
@WebServlet("/GetStockKLineServlet")
public class GetStockKLineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStockKLineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
		ArrayList<StockRecordVO> historyData=sv.getHistoryData();
		int size=historyData.size();
		String data="[";
		
		for (StockRecordVO stockRecordVO : historyData) {
			data=data+"{'date':'"+stockRecordVO.getDate()+"','value':["+
					stockRecordVO.getOpen()+","+stockRecordVO.getClose()+","+
					stockRecordVO.getLow()+","+stockRecordVO.getHigh()+"],'volume':"+
					stockRecordVO.getVolume()+"},";
		}

		data+="]";

		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
