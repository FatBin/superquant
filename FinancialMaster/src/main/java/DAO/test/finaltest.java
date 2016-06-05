package DAO.test;

import java.util.ArrayList;

import DAO.connection.DBconnection;
import DAO.pojo.Benchdata;
import DAO.pojo.Industries;
import PO.benchCurrentDataPO;
import PO.industriesPO;
import data.BenchData.BenchRecord;
import data.BenchData.BenchRecordUpdate;
import data.Database.BenchDataUpdate;
import data.Database.IndustriesUpdate;
import data.Database.TradeRecordUpdate;
import data.IndustryData.IndustryData;

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
		
		DBconnection dBconnection=new DBconnection();
//		try {
//			IndustriesUpdate update=new IndustriesUpdate();
//			update.IndustriesUpdate();
//			System.out.println("end");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		BenchDataUpdate benchDataUpdate=new BenchDataUpdate();
//		ArrayList<Benchdata> arrayList=benchdata
		benchDataUpdate.benchUpdate();
	}
}
