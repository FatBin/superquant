package DAO.test;

import java.util.ArrayList;
import java.util.List;

import DAO.DaoProxy.UserStrategyDaoProxy;
import DAO.pojo.Industries;
import DAO.pojo.UserStrategy;
import DAO.pojo.UserStrategyId;
import PO.industryPO;
import data.Database.IndustriesUpdate;
import data.IndustryData.IndustryData;
import data.Initialize.Init;
import data.UserData.UserStrategyData;

public class finaltest {

	public static void main(String[] args) {
		
//		BenchRecordUpdate staticBenchRecord = new BenchRecordUpdate();
//		BenchRecord benchRecord = new BenchRecord();
//		while (true) {
//			try {
//				benchCurrentDataPO po = benchRecord.getLastestRecord("sh000300");
//				System.out.println(po.getStatus());
//				System.out.println(po.getNow());
//				System.out.println(po.getRise_company());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
//		DBconnection dBconnection=new DBconnection();
//		try {
//			IndustriesUpdate update=new IndustriesUpdate();
//			update.IndustriesUpdate();
//			System.out.println("end");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		try {
//			String stockId="sh600000";
//			Document document = Jsoup
//					.connect("http://www.aigaogao.com/tools/history.html?s=" + stockId.substring(2)).get();
//			Elements elements = document.select("table[class=data]").get(0).select("tr");
//			for (int i = 1; i < elements.size() - 1 && i <= 7; i++) {
//				Element element = elements.get(i);
//				String[] temp = element.text().split(" ");
//
//				// 转换日期格式
//				String d = "";
//				SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
//				Date date = format1.parse(temp[0]);
//				SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
//				d = format2.format(date);
//				// 转换成交量格式
//				String volume = temp[5].replace(",", "") + "00";
//
//				TradeRecordId tradeRecordId = new TradeRecordId(stockId, d);
//				TradeRecord tradeRecord = new TradeRecord(tradeRecordId, Double.parseDouble(temp[1]),
//						Double.parseDouble(temp[4]), Double.parseDouble(temp[2]),
//						Double.parseDouble(temp[3]), 0, Long.parseLong(volume), 0, 0, 0);
//				System.out.println(tradeRecord.getId().getStockId());
//				System.out.println(tradeRecord.getId().getDate());
//				System.out.println(tradeRecord.getLow());
//			}
//		} catch (NumberFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
//		Init init=new Init();
//		try {
//			Thread.sleep(20000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		StockData stockData=new StockData();
//		try {
//			UpToDateStockPO po=stockData.getUpToDateStockPO("sz300183");
//			System.out.print(po.getStockName()+" ");
//			System.out.print(po.getStockId()+" ");
//			System.out.print(po.getDdx()+" ");
//			System.out.print(po.getDdy()+" ");
//			System.out.print(po.getDdz()+" ");
//			System.out.print(po.getIndustry()+" ");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		/*
		 * update the industries in the database
		 */
		Init init=new Init();
//		try {
//			Thread.sleep(15000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("t");
//		IndustriesUpdate industriesUpdate=new IndustriesUpdate();
//		industriesUpdate.IndustriesUpdate();
		
		UserStrategyData data=new UserStrategyData();
		UserStrategyId id=new UserStrategyId("pangchao", "sh600000", "jianfeishibai");
		UserStrategy strategy=new UserStrategy(id, "2014-02-12", "2014-09-11", 0, 0, 0, "1234567899123123123123123123123", "123123123", "");
		System.out.print(data.addStrategy(strategy));;
	}
	
}
