package data.Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxy.TradeRecordDaoProxy;
import DAO.DaoProxyService.StockDaoProxyService;
import DAO.DaoProxyService.TradeRecordDaoProxyService;
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
}
