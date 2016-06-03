package data.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DAOfactory.DaoFactory;
import DAO.DAOimpl.StockDaoImpl;
import DAO.DAOimpl.TradeRecordDaoImpl;
import DAO.DaoProxy.TradeRecordDaoProxy;
import DAO.DaoProxyService.StockDaoProxyService;
import DAO.DaoProxyService.TradeRecordDaoProxyService;
import DAO.connection.DBconnection;
import DAO.pojo.Stock;
import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;
import PO.stockStatisticPO;
import data.IO.HttpRequest;

public class TradeRecordUpdate {
	public static final String[] tradeRecord={
			"http://121.41.106.89:8010/api/stock/",
			"start=",
			"&end=",
			"&fields=open+high+low+close+adj_price+volume+turnover+pe_ttm+pb"};
	public static final String MinorityStock="http://www.aigaogao.com/tools/history.html?s=";
	public ArrayList<TradeRecord> getTradeRecord(){
		ArrayList<TradeRecord> arrayList=new ArrayList<>();
		try {
			StockDaoProxyService stockDaoProxyService=DaoFactory.getStockDaoProxy();
			List list=stockDaoProxyService.findAll();//get all the stock
			
			Calendar calendar=Calendar.getInstance();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String now=simpleDateFormat.format(calendar.getTime());//today
			calendar.add(Calendar.MONDAY, -1);
			String lastMonth=simpleDateFormat.format(calendar.getTime());//last month
			
			
			for (Object object : list) {
				try {
					Stock stock=(Stock)object;
					String stockId=stock.getStockId();
					String result=HttpRequest.sendGet(tradeRecord[0]+stockId,
							tradeRecord[1]+lastMonth+tradeRecord[2]+now+tradeRecord[3]);
					JSONObject jsonObject=new JSONObject(result);
					JSONObject jsonObject2=jsonObject.getJSONObject("data");
					JSONArray jsonArray=jsonObject2.getJSONArray("trading_info");
					for (Object object2 : jsonArray) {
						JSONObject jsonObject3=(JSONObject) object2;
						TradeRecordId id=new TradeRecordId(stockId, jsonObject3.getString("date"));
						TradeRecord tradeRecord=new TradeRecord(
								id, 
								jsonObject3.getDouble("open"), 
								jsonObject3.getDouble("close"), 
								jsonObject3.getDouble("high"), 
								jsonObject3.getDouble("low"), 
								jsonObject3.getDouble("adj_price"), 
								jsonObject3.getLong("volume"), 
								jsonObject3.getDouble("turnover"), 
								jsonObject3.getDouble("pe_ttm"), 
								jsonObject3.getDouble("pb"));
						arrayList.add(tradeRecord);
					}
				} catch (Exception e) {
					System.out.println("无此股票数据");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	
	public void TradeRecordUpdate(ArrayList<TradeRecord> arrayList){
		TradeRecordDaoProxyService tradeRecordDaoProxyService=DaoFactory.getTradeRecordDaoProxy();
		try {
			for (TradeRecord tradeRecord : arrayList) {
				tradeRecordDaoProxyService.insert(tradeRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String[]> minorityUpdate(){
		
		return null;
	}
	
	//用于补充某些助教数据库中不存在的历史数据(待修改)
	public void MinorityUpdate(){
		String url = "jdbc:mysql://115.159.122.196:3306/superquant";
		String userName = "root";
		String password = "141250089";
		DBconnection dBconnection = new DBconnection();
		TradeRecordDaoImpl tradeRecordDao = new TradeRecordDaoImpl();
		StockDaoImpl stockDaoImpl = new StockDaoImpl();
		String hql = "select s.stockId from Stock s where s.stockId not in "
				+ "(select distinct t.id.stockId from TradeRecord t)";
		try {
			List list = stockDaoImpl.getStockData(hql);
			System.out.println("已经获取所有未存股票");
			int count=0;
			for (Object object : list) {
				count++;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection(
							url + "?useSSL=false&useServerPrepStmts=false&rewriteBatchedStatements=true", userName,
							password);
					conn.setAutoCommit(false);
					String sql = "insert into trade_record"
							+ "(stockID,date,open,close,high,low,adj_price,volume,turnover,pe,pb) "
							+ "values(?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement prest = conn.prepareStatement(sql);
					String stockId = (String) object;
					try {
						Document document = Jsoup
								.connect("http://www.aigaogao.com/tools/history.html?s=" + stockId.substring(2)).get();
						Elements elements = document.select("table[class=data]").get(0).select("tr");
						for (int i = 1; i < elements.size() - 1; i++) {
							Element element = elements.get(i);
							String[] temp = element.text().split(" ");

							
							//转换日期格式
							String d = "";
							SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
							Date date = format1.parse(temp[0]);
							SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
							d = format2.format(date);
							//转换成交量格式
							String volume=temp[5].replace(",", "")+"00";
							
							prest.setString(1, stockId);
							prest.setString(2, d);
							prest.setDouble(3, Double.parseDouble(temp[1]));
							prest.setDouble(4, Double.parseDouble(temp[4]));
							prest.setDouble(5, Double.parseDouble(temp[2]));
							prest.setDouble(6, Double.parseDouble(temp[3]));
							prest.setDouble(7, 0);
							prest.setLong(8, Long.parseLong(volume));
							prest.setDouble(9, 0);
							prest.setDouble(10, 0);
							prest.setDouble(11, 0);
							
							prest.addBatch();
//							System.out.print(stockId+" ");
//							System.out.print(d+" ");
//							System.out.print(Double.parseDouble(temp[1])+" ");
//							System.out.print(Double.parseDouble(temp[4])+" ");
//							System.out.print(Double.parseDouble(temp[2])+" ");
//							System.out.print(Double.parseDouble(temp[3])+" ");
//							System.out.print(0+" ");
//							System.out.print(Long.parseLong(volume)+" ");
//							System.out.print(0+" ");
//							System.out.print(0+" ");
//							System.out.println(0+" ");
						}
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("无此股票数据");
					}

					prest.executeBatch();
					prest.clearBatch();
					conn.commit();
					System.out.println(stockId + "存储成功 "+count);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println((String) object + "存储失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
