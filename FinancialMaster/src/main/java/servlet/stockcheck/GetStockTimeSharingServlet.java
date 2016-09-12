package servlet.stockcheck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import web.bl.stockImpl.StockImpl;
import web.blservice.stockInfo.StockInfo;

/**
 * Servlet implementation class GetStockTimeSharingServlet
 */
@WebServlet("/GetStockTimeSharingServlet")
public class GetStockTimeSharingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStockTimeSharingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id=request.getParameter("stockId");
		if(id.equals("null")){
			id=(String) request.getSession().getAttribute("Stockid");
		}
		StockInfo stockInfo=new StockImpl();
		String[][] datas=stockInfo.getTimeSharingData(id);
				
		String data="[";
		for(int i=datas.length-1;i>=0;i--){
			data=data+"{'date':'"+datas[i][0]+
					"','data':"+datas[i][1]+
					"},";
		}
		data+="]";

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
