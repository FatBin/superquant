package DAO.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxy.BenchDaoProxy;
import DAO.DaoProxy.BenchdataDaoProxy;
import DAO.DaoProxyService.StockDaoProxyService;
import DAO.connection.DBconnection;
import DAO.dao.BenchdataDao;
import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;
import DAO.pojo.Stock;
import PO.industriesPO;
import PO.industryPO;
import data.IO.HttpRequest;
import data.IndustryData.IndustryData;
import data.stockcheckdata.StockData;

public class update {
	public static void main(String[] args) {
		/*
		 * insert stock
		 */
		// ArrayList<String> arrayList=new ArrayList<>();
		// DBconnection dBconnection=new DBconnection();
		// for(int year=1990;year<2017;year++){
		// for(int quarter=1;quarter<5;quarter++){
		// Document document=null;
		// try {
		// document=Jsoup.connect("http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/000300/type/S.phtml?year="+year+"&jidu="+quarter).get();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// try {
		// Elements elements=document.select("table[id=FundHoldSharesTable]");
		// if(elements.size()!=0){
		// Element element=elements.first().select("tbody").first();
		// Elements tr=element.select("tr");
		// tr.remove(0);
		// BenchdataDaoProxy benchdataDao=DaoFactory.getBenchdataDaoProxy();
		// for(int i=tr.size()-1;i>=0;i--){
		// String[] temp=tr.get(i).text().split(" ");
		// BenchdataId benchdataId=new BenchdataId("sh000300",temp[0]);
		// Benchdata benchdata=new Benchdata(benchdataId,
		// Double.parseDouble(temp[1]),
		// Double.parseDouble(temp[3]), Double.parseDouble(temp[2]),
		// Double.parseDouble(temp[4]),
		// Double.parseDouble(temp[6]),Long.parseLong(temp[5]));
		// try {
		// benchdataDao.insert(benchdata);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//// arrayList.add(tr.get(i).text());
		//// System.out.println(tr.get(i).text());
		//
		// }
		//
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		//
		// }
		// }
		// System.out.println("yes");
		// DBconnection dBconnection=new DBconnection();
		// StockDao stockDao=DaoFactory.getStockDaoProxy();
		// for (Element element : elements) {
		// String[] text=element.text().split(" ");
		// Stock stock=new Stock("sh"+text[1],text[2],text[4]);
		// try {
		// stockDao.insert(stock);
		// } catch (Exception e) {
		// e.printStackTrace( );
		// }
		// }
		// System.out.println("completed");
		// System.out.println(id.size());

		/*
		 * insert stockdrecord
		 */

		// DBconnection dBconnection=new DBconnection();
		Document document;

		// try {
		// document=Jsoup.connect("http://www.aigaogao.com/tools/history.html?s=600000").get();
		// Element element=document.select("table[style=width:100%;
		// border-collapse: collapse;]").get(0);
		// Elements elements=element.select("tr");
		// for(Element element2:elements)
		// System.out.println(element2.text());
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// String d1 = "11/12/1212";
		// SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		// Date date = null;
		// try {
		// date = format.parse(d1);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }//有异常要捕获
		// format = new SimpleDateFormat("yyyy-MM-dd");
		// String newD = format.format(date);
		// System.out.println(newD);

		/*
		 * update the old stock information
		 */
		DBconnection dBconnection=new DBconnection();
		StockData stockData = new StockData();
		IndustryData industryData = new IndustryData();
		try {

			ArrayList<industriesPO> arrayList = industryData.getIndustryData();
			int count=0;
			for (int i = 0; i < arrayList.size(); i++) {
				ArrayList<industryPO> arrayList2 = industryData.getIndustry(arrayList.get(i).getIndustry());
				for (int j = 0; j < arrayList2.size(); j++) {
					count++;
					Stock stock = new Stock(arrayList2.get(j).getStockId(), "", arrayList.get(i).getIndustry());
					try {
						String stockId = stock.getStockId();
						String name = "";
						String string=new String(sendGet("http://hq.sinajs.cn/list="+stockId+",", "").getBytes("gbk"));
						name=string.split("\"")[1].split(",")[0];
						stock.setStockName(name);
						stock.setStockId(stockId);
						if (!name.equals("")) {
							try {
								StockDaoProxyService service = DaoFactory.getStockDaoProxy();
								service.insert(stock);
								System.out.println(stockId+" "+name+" 成功"+" "+count);
							} catch (Exception e) {
								System.out.println("已存在");
								e.printStackTrace();
							}
						}else{
							System.out.println("无此股票名:"+stockId);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
//		try {
//			Session session=dBconnection.getSession();
//			Transaction tx=session.beginTransaction();
//			String hqlString="update TradeRecord u set u.id.stockId='sh'||u.id.stockId "
//					+ "where u.id.stockId>='6'";
//			Query query=session.createQuery(hqlString);
//			query.executeUpdate();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("yes");
//		
//		try {
//			Session session=dBconnection.getSession();
//			Transaction tx=session.beginTransaction();
//			String hqlString="update TradeRecord u set u.id.stockId='sz'||u.id.stockId "
//					+ "where u.id.stockId<'6'";
//			Query query=session.createQuery(hqlString);
//			query.executeUpdate();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println("no");
	}

	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			// connection.setRequestProperty("accept", "*/*");
			// connection.setRequestProperty("connection", "Keep-Alive");
			// connection.setRequestProperty("user-agent",
			// "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("X-Auth-Code", "5fab0242ef7df2cc6dd91195224eb73f");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gbk"));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line;
			}
			// Gson gson=new Gson();
			// java.lang.reflect.Type type = new TypeToken<Bean1>()
			// {}.getType();
			// Bean1 bean1= gson.fromJson(result, Bean1.class);
			// List<statistic> resultList=bean1.getList();
			// for(int i=0;i<resultList.size();i++)
			// System.out.println(resultList.get(i).getname());
			// JSONObject jsonObject=new JSONObject(result);
			// JSONObject jsonObject2=jsonObject.getJSONObject("data");
			// JSONArray jsonArray=jsonObject2.getJSONArray("trading_info");
			// for(int i=0;i<jsonArray.length();i++){
			// System.out.println(jsonArray.getJSONObject(i).getString("date"));
			// }
			// String
			// a="{\"data\":{\"bidirection\":true},\"msg\":\"成功\",\"success\":true,\"code\":0,\"array\":[{\"a\":true},{\"a\":false}]}";
			// JSONObject jso=new JSONObject(a);
			// System.out.println(jso.get("data").toString());
			// JSONObject jso2=jso.getJSONObject("data");
			// System.out.println(jso.get("success"));
			// System.out.println(jso2.getBoolean("bidirection"));
			// JSONArray jso3=jso.getJSONArray("array");
			// System.out.println(jso3.get(0));
			// JSONObject jsonObject=new JSONObject(result);
			// JSONArray jsonObject2=jsonObject.getJSONArray("data");
			// System.out.print(jsonObject2.length());
			// for (int i = 0; i < jsonObject2.length(); i++) {
			// JSONObject jsonObject3=jsonObject2.getJSONObject(i);
			// System.out.println(jsonObject3.getString("name"));
			// }
		} catch (Exception e) {
			// System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
