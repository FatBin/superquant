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
import DAO.DaoProxyService.StockDaoProxyService;
import DAO.DaoProxyService.TradeRecordDaoProxyService;
import DAO.connection.DBconnection;
import DAO.pojo.Stock;
import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;
import data.IO.HttpRequest;

public class TradeRecordUpdate {
	public static final String[] tradeRecord = { "http://121.41.106.89:8010/api/stock/", "start=", "&end=",
			"&fields=open+high+low+close+adj_price+volume+turnover+pe_ttm+pb" };
	public static final String MinorityStock = "http://www.aigaogao.com/tools/history.html?s=";

	public ArrayList<TradeRecord> getTradeRecord() {
		ArrayList<TradeRecord> arrayList = new ArrayList<>();
		try {
			StockDaoProxyService stockDaoProxyService = DaoFactory.getStockDaoProxy();
			List list = stockDaoProxyService.findAll();// get all the stock

			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String now = simpleDateFormat.format(calendar.getTime());// today
			calendar.add(Calendar.WEEK_OF_YEAR, -1);
			String lastweek = simpleDateFormat.format(calendar.getTime());// lastweek

			for (Object object : list) {
				try {
					Stock stock = (Stock) object;
					String stockId = stock.getStockId();
					String result = HttpRequest.sendGet(tradeRecord[0] + stockId,
							tradeRecord[1] + lastweek + tradeRecord[2] + now + tradeRecord[3]);
					JSONObject jsonObject = new JSONObject(result);
					JSONObject jsonObject2 = jsonObject.getJSONObject("data");
					JSONArray jsonArray = jsonObject2.getJSONArray("trading_info");
					System.out.println(result);
					if (jsonArray.length() == 0) {
						// 当助教api中无该股票数据
						Document document = Jsoup
								.connect(MinorityStock + stockId.substring(2)).get();
						Elements elements = document.select("table[class=data]").get(0).select("tr");
						for (int i = 1; i < elements.size() - 1 && i <= 7; i++) {
							Element element = elements.get(i);
							String[] temp = element.text().split(" ");

							// 转换日期格式
							String d = "";
							SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
							Date date = format1.parse(temp[0]);
							SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
							d = format2.format(date);
							// 转换成交量格式
							String volume = temp[5].replace(",", "") + "00";

							TradeRecordId tradeRecordId = new TradeRecordId(stockId, d);
							TradeRecord tradeRecord = new TradeRecord(tradeRecordId, Double.parseDouble(temp[1]),
									Double.parseDouble(temp[4]), Double.parseDouble(temp[2]),
									Double.parseDouble(temp[3]), 0, Long.parseLong(volume), 0, 0, 0);
							arrayList.add(tradeRecord);
						}
					} else {
						for (Object object2 : jsonArray) {
							JSONObject jsonObject3 = (JSONObject) object2;
							TradeRecordId id = new TradeRecordId(stockId, jsonObject3.getString("date"));
							TradeRecord tradeRecord = new TradeRecord(id, jsonObject3.getDouble("open"),
									jsonObject3.getDouble("close"), jsonObject3.getDouble("high"),
									jsonObject3.getDouble("low"), jsonObject3.getDouble("adj_price"),
									jsonObject3.getLong("volume"), jsonObject3.getDouble("turnover"),
									jsonObject3.getDouble("pe_ttm"), jsonObject3.getDouble("pb"));
							arrayList.add(tradeRecord);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	public void TradeRecordUpdate() {
		TradeRecordDaoProxyService tradeRecordDaoProxyService = DaoFactory.getTradeRecordDaoProxy();
		ArrayList<TradeRecord> arrayList = getTradeRecord();
		for (TradeRecord tradeRecord : arrayList) {
			try {
				tradeRecordDaoProxyService.insert(tradeRecord);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
