package servlet.stockmarket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		BenchVO benchVO=(BenchVO)request.getSession().getAttribute("BenchMarket");
		BenchUpdateInfo benchUpdateInfo=new BenchImpl();
		benchUpdateInfo.update(benchVO, benchName);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
