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

import PO.industriesPO;
import VO.BusinessListVO;
import web.bl.businessImpl.BusinessImpl;
import web.blservice.businessInfo.BusinessInfo;

/**
 * Servlet implementation class BusinessPageServlet
 */
@WebServlet("/BusinessPageServlet")
public class BusinessPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BusinessListVO businessListVO;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BusinessPageServlet() {
        super();
    }

    
	@Override
	public void init() throws ServletException {
		super.init();
		BusinessInfo businessInfo=new BusinessImpl();
		businessListVO=businessInfo.getBusinessList();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//返回所有行业的最新信息列表，类型BusinessListVO
		request.getSession().setAttribute("BusinessList", businessListVO);
		response.sendRedirect(request.getContextPath()+"/Web_Pages/BusinessPage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ArrayList<industriesPO> industryPOs=businessListVO.getBusinessList();
		int size=industryPOs.size();
		double[] ups_and_downs=new double[size];
		String[] name=new String[size];
		int index=0;
		for (industriesPO po : industryPOs) {
			ups_and_downs[index]=po.getRise_fall();
			name[index]=po.getIndustry();
			index++;
		}
		String data="[";
		for (int i = 0; i <10 ; i++) {
			data=data+"{'name':'"+name[i]+"','value':"+
					ups_and_downs[i]+"},";
		}
		for (int i = size-10; i < size; i++) {
			data=data+"{'name':'"+name[i]+"','value':"+
					ups_and_downs[i]+"},";
		}
		data+="]";
		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
