package DAO.test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.connection.DBconnection;
import DAO.pojo.Benchdata;
import DAO.pojo.Industries;
import DAO.pojo.TradeRecord;
import DAO.pojo.TradeRecordId;
import PO.RiseStockPO;
import PO.UpToDateStockPO;
import PO.benchCurrentDataPO;
import PO.industriesPO;
import data.BenchData.BenchRecord;
import data.BenchData.BenchRecordUpdate;
import data.Database.BenchDataUpdate;
import data.Database.IndustriesUpdate;
import data.Database.TradeRecordUpdate;
import data.IndustryData.IndustryData;
import data.Initialize.Init;
import data.StockData.RiseStockUpdate;
import data.StockData.StockData;
import data.StockData.UpToDateStocksUpdate;

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
		Init init=new Init();
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("t");
		IndustriesUpdate industriesUpdate=new IndustriesUpdate();
		industriesUpdate.IndustriesUpdate();
	}
}
