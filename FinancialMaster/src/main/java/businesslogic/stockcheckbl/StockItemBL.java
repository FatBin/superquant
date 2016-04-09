package businesslogic.stockcheckbl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

import PO.stockStatisticPO;
import VO.StockItemVO;
import businesslogicservice.stockcheckblservice.StockItemRankBLService;
import data.manageStockData.ManageStockData;
import data.stockcheckdata.StockData;
import dataservice.manageStockService.manageStockDataService;
import dataservice.stockcheckdataservice.StockDataService;

public class StockItemBL implements StockItemRankBLService{

	public static void main(String[] args) {
		StockItemBL stockItemBL=new StockItemBL();
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(calendar.MONTH, -1);
		String startDay = simpleDateFormat.format(calendar.getTime());
		System.out.println(startDay);
		ArrayList<StockItemVO> arrayList=stockItemBL.getRank("high");
		for (StockItemVO stockItemVO : arrayList) {
			System.out.println(stockItemVO.getStockname()+";"+stockItemVO.getCode()+";"+stockItemVO.getItem());
		}
	}
	//获得相应排好序的StockItemVO,返回
	@Override
	public ArrayList<StockItemVO> getRank(String item) {
		// TODO Auto-generated method stub
		 manageStockDataService manageStockData=new ManageStockData();
		 StockDataService stockData=new StockData();
		 ArrayList<String> codeOfStock=manageStockData.getCodeOfStock();
		 //一年内
		 Calendar calendar=Calendar.getInstance();
		 SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		 String end= simpleDateFormat.format(calendar.getTime());
		 calendar.add(calendar.MONTH, -1);
		 String start= simpleDateFormat.format(calendar.getTime());
		 
		 //对应的最近交易日
		 Map<String, stockStatisticPO> hashMap=new HashMap<String, stockStatisticPO>();
		 for (String string : codeOfStock) {
			ArrayList<stockStatisticPO> stockStatisticPOs=stockData.getStatisitcOfStock(string, start, end);
			//如果没有就传一个空的stockStatisticPO
			try {
				hashMap.put(string, stockStatisticPOs.get(stockStatisticPOs.size()-1));
			} catch (Exception e) {
				stockStatisticPO po=new stockStatisticPO("一个月没交易", "", 0, 0, 0, 0, 0, 0, 0, 0, 0);
				hashMap.put(string, po);
			}
		}
		 
		 ArrayList<StockItemVO> arrayList= getCorrespondingItem(item,hashMap);
		 
		 arrayList=rank(arrayList);
		return arrayList;
	}
	
	//排序
	private ArrayList<StockItemVO> rank(ArrayList<StockItemVO> arrayList) {
		// TODO Auto-generated method stub
		for(int i=0;i<arrayList.size();i++){
			for(int j=0;j<arrayList.size()-1;j++){
				StockItemVO s1=arrayList.get(j);
				StockItemVO s2=arrayList.get(j+1);
				if(Double.parseDouble(s1.getItem())<Double.parseDouble(s2.getItem())){
					arrayList.set(j, s2);
					arrayList.set(j+1, s1);
				}
			}
		}
		return arrayList;
	}
	//获得相应的item值的stockItemPO
	private ArrayList<StockItemVO> getCorrespondingItem(String item, Map<String, stockStatisticPO> hashMap) {
		// TODO Auto-generated method stub
		ArrayList<StockItemVO> arrayList=new ArrayList<>();
		Set set=hashMap.entrySet();
		Iterator iterator=set.iterator();
		if (item.equals("open")) {
			while (iterator.hasNext()) {
				Map.Entry entry=(Map.Entry) iterator.next();
				String code=(String) entry.getKey();
				stockStatisticPO s=(stockStatisticPO) entry.getValue();
				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getOpen());
				arrayList.add(stockItemVO);
			}
		}else if (item.equals("high")) {
			while (iterator.hasNext()) {
				Map.Entry entry=(Map.Entry) iterator.next();
				String code=(String) entry.getKey();
				stockStatisticPO s=(stockStatisticPO) entry.getValue();
				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getHigh());
				arrayList.add(stockItemVO);
			}
		}else if (item.equals("low")) {
			while (iterator.hasNext()) {
				Map.Entry entry=(Map.Entry) iterator.next();
				String code=(String) entry.getKey();
				stockStatisticPO s=(stockStatisticPO) entry.getValue();
				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getOpen());
				arrayList.add(stockItemVO);
			}
		}else if (item.equals("close")) {
			while (iterator.hasNext()) {
				Map.Entry entry=(Map.Entry) iterator.next();
				String code=(String) entry.getKey();
				stockStatisticPO s=(stockStatisticPO) entry.getValue();
				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getClose());
				arrayList.add(stockItemVO);
			}
		}else if (item.equals("adj_price")) {
			while (iterator.hasNext()) {
				Map.Entry entry=(Map.Entry) iterator.next();
				String code=(String) entry.getKey();
				stockStatisticPO s=(stockStatisticPO) entry.getValue();
				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getAdj_price());
				arrayList.add(stockItemVO);
			}
		}else if (item.equals("volume")) {
			while (iterator.hasNext()) {
				Map.Entry entry=(Map.Entry) iterator.next();
				String code=(String) entry.getKey();
				stockStatisticPO s=(stockStatisticPO) entry.getValue();
				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getVolume());
				arrayList.add(stockItemVO);
			}
		}else if (item.equals("turnover")) {
			while (iterator.hasNext()) {
				Map.Entry entry=(Map.Entry) iterator.next();
				String code=(String) entry.getKey();
				stockStatisticPO s=(stockStatisticPO) entry.getValue();
				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getTurnover());
				arrayList.add(stockItemVO);
			}
		}else if (item.equals("pe")) {
			while (iterator.hasNext()) {
				Map.Entry entry=(Map.Entry) iterator.next();
				String code=(String) entry.getKey();
				stockStatisticPO s=(stockStatisticPO) entry.getValue();
				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getPe());
				arrayList.add(stockItemVO);
			}
		}else if (item.equals("pb")) {
			while (iterator.hasNext()) {
				Map.Entry entry=(Map.Entry) iterator.next();
				String code=(String) entry.getKey();
				stockStatisticPO s=(stockStatisticPO) entry.getValue();
				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getPb());
				arrayList.add(stockItemVO);
			}
		}
		return arrayList;
	}

}
