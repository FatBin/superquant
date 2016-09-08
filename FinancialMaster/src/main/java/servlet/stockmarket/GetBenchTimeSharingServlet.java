package servlet.stockmarket;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import web.bl.benchImpl.BenchImpl;
import web.blservice.benchInfo.BenchInfo;

/**
 * Servlet implementation class GetBenchTimeSharingServlet
 */
@WebServlet("/GetBenchTimeSharingServlet")
public class GetBenchTimeSharingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBenchTimeSharingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String benchName=(String) request.getSession().getAttribute("BenchMarketName");
		BenchInfo benchInfo=new BenchImpl();
		String[][] datas=benchInfo.getTimeSharingData(benchName);
				
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
