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

import DAO.pojo.Industries;
import PO.UpToDateStockPO;
import PO.industriesPO;
import PO.industryPO;
import VO.BusinessItemVO;
import VO.BusinessVO;
import VO.StockDetailVO;

/**
 * Servlet implementation class GetBusinessContrastServlet
 */
@WebServlet("/GetBusinessContrastServlet")
public class GetBusinessContrastServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBusinessContrastServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
		UpToDateStockPO upToDateStockPO=sv.getUpToDateMessage();
		String id=upToDateStockPO.getStockId();
		
		BusinessVO businessVO=sv.getBusinessVO();
		ArrayList<BusinessItemVO> businessItemVOs=businessVO.getBusinessItemVOs();
		int size=businessItemVOs.size();
		String data="[";
		for (int i = 0; i < 3; i++) {
			BusinessItemVO businessItemVO=businessItemVOs.get(i);
			String name=businessItemVO.getStockName()+"  "+(i+1)+"";
			data=data+"{'name':'"+name+"','up_and_down':"+
					businessItemVO.getRise_fall_percent()+"},";
		}
		
		int index=0;
		for (BusinessItemVO businessItemVO : businessItemVOs) {
			index++;			
			if(businessItemVO.getStockId().equals(id)){
				String name=businessItemVO.getStockName()+"  "+(index)+"";
				data=data+"{'name':'"+name+"','up_and_down':"+
						businessItemVO.getRise_fall_percent()+"}";
				break;
			}
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
		StockDetailVO sv=(StockDetailVO)request.getSession().getAttribute("StockDetail");
		
		double[] rise_fallList=sv.getRise_fallList();
		
		BusinessVO businessVO=sv.getBusinessVO();
		ArrayList<Industries> industrieslist=businessVO.getHistoryData();
		int size=industrieslist.size();
		String data="[";
		for(int i=size-1;i>=0;i--){
			Industries industries=industrieslist.get(i);
			data=data+"{'date':'"+industries.getId().getDate()+
					"','rise_fall':"+rise_fallList[i]+
					",'business_rise_fall':"+industries.getRiseFall()+"},";
		}
		data+="]";
		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
