package servlet.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import DAO.pojo.Industries;
import PO.industriesPO;
import VO.BusinessListVO;
import VO.BusinessVO;
import web.bl.businessImpl.BusinessImpl;
import web.blservice.businessInfo.BusinessInfo;

/**
 * Servlet implementation class BusinessDetailPageServlet
 */
@WebServlet("/BusinessDetailPageServlet")
public class BusinessDetailPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BusinessVO businessVO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessDetailPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String businessname=request.getParameter("BusinessName");
		BusinessInfo businessInfo=new BusinessImpl();
		businessVO=businessInfo.getBusiness(businessname);
		
		BusinessListVO businessListVO=(BusinessListVO) request.getSession().getAttribute("BusinessList") ;
		ArrayList<industriesPO> industriesPOs=businessListVO.getBusinessList();
		for (industriesPO i : industriesPOs) {
			if(i.getIndustry().equals(businessname)){
				businessVO.setUptodate_message(i);
				break;
			}
		}
		request.getSession().setAttribute("BusinessDetail",businessVO);
		
		response.sendRedirect(request.getContextPath()+"/Web_Pages/BusinessPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ArrayList<Industries> historyData=businessVO.getHistoryData();
		
		String data="[";
		
//		for (Industries industry : historyData) {
//			
//			data=data+"{'average_price':'"+industry.getAveragePrice()+
//					"','date':"+industry.getId().getDate()+
//					"','rise_fall':"+industry.getRiseFall()+
//					"','volume':"+industry.getVolume()+
//					"','turnover':"+industry.getTurnover()+
//					"','inflows':"+industry.getInflows()+"},";
//		}
		
		for (int i = 0; i < 10; i++) {
			data=data+"{'average_price':"+Math.random()+
					",'date':"+i+
					",'rise_fall':"+Math.random()+
					",'volume':"+Math.random()+
					",'turnover':"+Math.random()+
					",'inflows':"+(Math.random()-0.5)+"},";
		}
//		double average_price;//平均价格
//		private double rise_fall;//涨跌率
//		private double volume;//成交量
//		private double turnover;//换手率
//		private double inflows;//流入资金量
		
		data+="]";
		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
