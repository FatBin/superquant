package servlet.factory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businesslogic.factory.InitFactory;
import data.IO.FileManager;
import data.stockcheckdata.StockData;
import dataservice.stockcheckdataservice.StockDataService;

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
		path=this.getServletContext().getRealPath("/")+"WEB-INF/classes/";
		System.out.println("new data");
		StockDataService sds=new StockData();
		System.out.println("new factory");
	    InitFactory.getFactory();		
		System.out.println("≥ı ºªØΩ· ¯");
	}
	
	public static String getPath(){
		return path;
	}

}
