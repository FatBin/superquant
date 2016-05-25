package servlet.factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import DAO.connection.DBconnection;


/**
 * Servlet implementation class InitFactory
 */
@WebServlet("/InitFactory")
public class InitFactoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String path="src/main/resources/";
       
    public InitFactoryServlet() {
        super();
    }

	@Override
	public void init() throws ServletException {
//		path=this.getServletContext().getRealPath("/")+"WEB-INF/classes/";
//		System.out.println("new data");
//		StockDataService sds=new StockData();
//		System.out.println("new factory");
//	    InitFactory.getFactory();		
//		System.out.println("≥ı ºªØΩ· ¯");
		DBconnection connection =new DBconnection();
	}
	
	public static String getPath(){
		return path;
	}

}
