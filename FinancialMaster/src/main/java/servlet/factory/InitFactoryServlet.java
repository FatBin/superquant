package servlet.factory;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import DAO.connection.DBconnection;
import DAO.pojo.Stock;
import data.Initialize.Init;
import web.bl.serchInfo.IdListImpl;
import web.blservice.searchInfo.IdListInfo;


/**
 * Servlet implementation class InitFactory
 */
@WebServlet("/InitFactory")
public class InitFactoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String path="src/main/resources/";
    private static ArrayList<Stock> allIdList;   
	
    public InitFactoryServlet() {
        super();
    }

	@Override
	public void init() throws ServletException {
//		path=this.getServletContext().getRealPath("/")+"WEB-INF/classes/";
        //初始化数据
		Init initData=new Init();
		//初始化所有股票id列表
		IdListInfo idListInfo=new IdListImpl();
		allIdList=idListInfo.getIdList();
	}
	
	public static String getPath(){
		return path;
	}

	public static ArrayList<Stock> getSerchList(String key) {
		ArrayList<Stock> filterList = new ArrayList<Stock>();
		
		for (Stock stock : allIdList) {
			if(stock.getStockId().contains(key)||stock.getStockName().contains(key)){
				filterList.add(stock);
			}
		}
		return filterList;		
	}
	public static Stock getStock(String id){
		Stock result=null;
		for (Stock stock : allIdList) {
			if(stock.getStockId().equals(id)){
				result=stock;
				break;
			}
		}
		return result;
	}
	
	public static boolean isExist(String id){
		for (Stock stock : allIdList) {
			if(stock.getStockId().equals(id)){
				return true;
			}
		}
		return false;
	}
}
