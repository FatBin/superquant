package servlet.stockmarket;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

/**
 * Servlet implementation class GetKLineDataServlet
 */
@WebServlet("/GetKLineDataServlet")
public class GetKLineDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetKLineDataServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("dddd");
		String data = "[{'date':'2013/01/24','value':[2320.26,2320.26,2287.3,2362.94],'volume':300},"
				+ "{'date':'2013/01/25','value':[ 2300,2291.3,2288.26,2308.38],'volume':400},"
				+ "{'date':'2013/01/28','value':[ 2295.35,2346.5,2295.35,2346.92],'volume':500},"
				+ "{'date':'2013/01/29','value':[ 2347.22,2358.98,2337.35,2363.8],'volume':400},"
				+ "{'date':'2013/01/30','value':[2360.75,2382.48,2347.89,2383.76],'volume':300},"
				+ "{'date':'2013/01/31','value':[ 2383.43,2385.42,2371.23,2391.82],'volume':600},"
				+ "{'date':'2013/02/1','value':[ 2377.41,2419.02,2369.57,2421.15],'volume':200},"
				+ "{'date':'2013/02/4','value':[2425.92,2428.15,2417.58,2440.38],'volume':300},"
				+ "{'date':'2013/02/5','value':[ 2411,2433.13,2403.3,2437.42],'volume':500},"
				+ "{'date':'2013/02/6','value':[ 2432.68,2434.48,2427.7,2441.73],'volume':255},"
				+ "{'date':'2013/2/7','value':[ 2430.69,2418.53,2394.22,2433.89],'volume':288},"
				+ "{'date':'2013/2/8', 'value':[2416.62,2432.4,2414.4,2443.03],'volume':399},"
				+ "{'date':'2013/2/18','value':[ 2441.91,2421.56,2415.43,2444.8],'volume':340},"
				+ "{'date':'2013/2/19','value':[ 2420.26,2382.91,2373.53,2427.07],'volume':360},"
				+ "{'date':'2013/2/20','value':[ 2383.49,2397.18,2370.61,2397.94],'volume':386},"
				+ "{'date':'2013/2/21', 'value':[2378.82,2325.95,2309.17,2378.82],'volume':152},"
				+ "{'date':'2013/2/22','value':[ 2322.94,2314.16,2308.76,2330.88],'volume':500},"
				+ "{'date':'2013/2/25', 'value':[2320.62,2325.82,2315.01,2338.78],'volume':390},"
				+ "{'date':'2013/2/26', 'value':[2313.74,2293.34,2289.89,2340.71],'volume':560},"
				+ "{'date':'2013/2/27','value':[ 2297.77,2313.22,2292.03,2324.63],'volume':300},"
				+ "{'date':'2013/2/28', 'value':[2322.32,2365.59,2308.92,2366.16],'volume':300},"
				+ "{'date':'2013/3/1', 'value':[2364.54,2359.51,2330.86,2369.65],'volume':366},"
				+ "{'date':'2013/3/4', 'value':[2332.08,2273.4,2259.25,2333.54],'volume':450},"
				+ "{'date':'2013/3/5', 'value':[2274.81,2326.31,2270.1,2328.14],'volume':440},"
				+ "{'date':'2013/3/6','value':[ 2333.61,2347.18,2321.6,2351.44],'volume':320},"
				+ "{'date':'2013/3/7', 'value':[2340.44,2324.29,2304.27,2352.02],'volume':200},"
				+ "{'date':'2013/3/8', 'value':[2326.42,2318.61,2314.59,2333.67],'volume':190},"
				+ "{'date':'2013/3/11', 'value':[2314.68,2310.59,2296.58,2320.96],'volume':300}]";

		JSONArray json = new JSONArray(data);
		PrintWriter out = response.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}

}
