package businesslogic.stockcheckbl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;

import PO.stockStatisticPO;
import VO.StockItemVO;
import businesslogicservice.stockcheckblservice.StockItemRankBLService;
import data.manageStockData.ManageStockData;
import data.stockcheckdata.StockData;
import dataservice.manageStockService.manageStockDataService;
import dataservice.stockcheckdataservice.StockDataService;

public class StockItemBL implements StockItemRankBLService{

	private Map<String, ArrayList<StockItemVO>> Data_MAP = new HashMap<String, ArrayList<StockItemVO>>();
	
	public StockItemBL(){
		StockDataService sds = new StockData();
		manageStockDataService msds = new ManageStockData();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String startDay = format.format(cal.getTime());
		cal.add(Calendar.DATE, 1);
		String endDay = format.format(cal.getTime());

		ArrayList<stockStatisticPO> ssPOlist;
		stockStatisticPO ssPO;
		ArrayList<String> stockList;
		stockList = msds.getCodeOfStock();

		int size = stockList.size();
		do {
			cal.add(Calendar.DATE, -1);
			startDay = format.format(cal.getTime());
			ssPOlist = sds.getStatisitcOfStock(stockList.get(0), startDay, endDay);
		} while (ssPOlist.isEmpty());

		cal.add(Calendar.DATE, -1);
		String yesStartDay = format.format(cal.getTime());
		
		Double close[][] = new Double[size][2];
		String[] key={"id","股票名","开盘价","最高价","最低价","收盘价",
				"后复权价","成交量","换手率","市盈率","市净率","涨跌幅"};
		String data[][]=new String[size][12];		
		DecimalFormat df = new DecimalFormat( "0.0000");
        double ups_and_downs;
		
		for (int i = 0; i < size; i++) {
			data[i][0] = stockList.get(i);
			ssPOlist = sds.getStatisitcOfStock(data[i][0], startDay, endDay);
			ssPO = ssPOlist.get(0);
			data[i][1]=ssPO.getName();
			data[i][2]=ssPO.getOpen()+"";
			data[i][3]=ssPO.getHigh()+"";			
			data[i][4]=ssPO.getLow()+"";
			data[i][5]=ssPO.getClose()+"";
			data[i][6]=ssPO.getAdj_price()+"";
			data[i][7]=ssPO.getVolume()+"";
			data[i][8]=ssPO.getTurnover()+"";
			data[i][9]=ssPO.getPe()+"";
			data[i][10]=ssPO.getPb()+"";			
			close[i][1] = ssPO.getClose();
			ssPOlist = sds.getStatisitcOfStock(data[i][0], yesStartDay, startDay);
			ssPO = ssPOlist.get(0);
			close[i][0] = ssPO.getClose();
			ups_and_downs=(close[i][1] - close[i][0]) / close[i][0];
			data[i][11] = df.format(ups_and_downs);
		}
		
		for (int i = 2; i < 12; i++) {
			ArrayList<StockItemVO> VOlist=new ArrayList<StockItemVO>(); 
			for (int j = 0; j < size; j++) {
				StockItemVO sv=new StockItemVO(data[j][1],data[j][0],data[j][i]);
				VOlist.add(sv);				
			}
			Data_MAP.put(key[i], rank(VOlist));
		}
		
		
	}
	
	private ArrayList<StockItemVO> rank(ArrayList<StockItemVO> oldList) {
		ArrayList<StockItemVO> newlist=new ArrayList<StockItemVO>();
		int size=oldList.size();
		for (int i = 0; i < size; i++) {
			StockItemVO max=oldList.get(0);
			for (StockItemVO stockItemVO : oldList) {
				if(Double.parseDouble(max.getItem())<Double.parseDouble(stockItemVO.getItem())){
					max=stockItemVO;
				}
			}
			oldList.remove(max);
			newlist.add(max);			
		}						
		return newlist;
	}
	
	
	@Override
	public ArrayList<StockItemVO> getRank(String item) {
		ArrayList<StockItemVO> result=new ArrayList<StockItemVO>();
		for (Entry<String, ArrayList<StockItemVO>> entry : Data_MAP.entrySet()) {
			String number = entry.getKey();
			if (item.equals(number))
				result = entry.getValue();
		}
		return result;
	}

//	public static void main(String[] args) {
//		StockItemBL stockItemBL=new StockItemBL();
//		Calendar calendar=Calendar.getInstance();
//		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//		calendar.add(calendar.MONTH, -1);
//		String startDay = simpleDateFormat.format(calendar.getTime());
//		System.out.println(startDay);
//		ArrayList<StockItemVO> arrayList=stockItemBL.getRank("high");
//		for (StockItemVO stockItemVO : arrayList) {
//			System.out.println(stockItemVO.getStockname()+";"+stockItemVO.getCode()+";"+stockItemVO.getItem());
//		}
//	}
//	//获得相应排好序的StockItemVO,返回
//	@Override
//	public ArrayList<StockItemVO> getRank(String item) {
//		// TODO Auto-generated method stub
//		 manageStockDataService manageStockData=new ManageStockData();
//		 StockDataService stockData=new StockData();
//		 ArrayList<String> codeOfStock=manageStockData.getCodeOfStock();
//		 //一年内
//		 Calendar calendar=Calendar.getInstance();
//		 SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//		 String end= simpleDateFormat.format(calendar.getTime());
//		 calendar.add(calendar.MONTH, -1);
//		 String start= simpleDateFormat.format(calendar.getTime());
//		 
//		 //对应的最近交易日
//		 Map<String, stockStatisticPO> hashMap=new HashMap<String, stockStatisticPO>();
//		 for (String string : codeOfStock) {
//			ArrayList<stockStatisticPO> stockStatisticPOs=stockData.getStatisitcOfStock(string, start, end);
//			//如果没有就传一个空的stockStatisticPO
//			try {
//				hashMap.put(string, stockStatisticPOs.get(stockStatisticPOs.size()-1));
//			} catch (Exception e) {
//				stockStatisticPO po=new stockStatisticPO("一个月没交易", "", 0, 0, 0, 0, 0, 0, 0, 0, 0);
//				hashMap.put(string, po);
//			}
//		}
//		 
//		 ArrayList<StockItemVO> arrayList= getCorrespondingItem(item,hashMap);
//		 
//		 arrayList=rank(arrayList);
//		return arrayList;
//	}
//	
//	//排序
//	private ArrayList<StockItemVO> rank(ArrayList<StockItemVO> arrayList) {
//		// TODO Auto-generated method stub
//		for(int i=0;i<arrayList.size();i++){
//			for(int j=0;j<arrayList.size()-1;j++){
//				StockItemVO s1=arrayList.get(j);
//				StockItemVO s2=arrayList.get(j+1);
//				if(Double.parseDouble(s1.getItem())<Double.parseDouble(s2.getItem())){
//					arrayList.set(j, s2);
//					arrayList.set(j+1, s1);
//				}
//			}
//		}
//		return arrayList;
//	}
//	//获得相应的item值的stockItemPO
//	private ArrayList<StockItemVO> getCorrespondingItem(String item, Map<String, stockStatisticPO> hashMap) {
//		// TODO Auto-generated method stub
//		ArrayList<StockItemVO> arrayList=new ArrayList<>();
//		Set set=hashMap.entrySet();
//		Iterator iterator=set.iterator();
//		if (item.equals("开盘价")) {
//			while (iterator.hasNext()) {
//				Map.Entry entry=(Map.Entry) iterator.next();
//				String code=(String) entry.getKey();
//				stockStatisticPO s=(stockStatisticPO) entry.getValue();
//				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getOpen());
//				arrayList.add(stockItemVO);
//			}
//		}else if (item.equals("最高价")) {
//			while (iterator.hasNext()) {
//				Map.Entry entry=(Map.Entry) iterator.next();
//				String code=(String) entry.getKey();
//				stockStatisticPO s=(stockStatisticPO) entry.getValue();
//				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getHigh());
//				arrayList.add(stockItemVO);
//			}
//		}else if (item.equals("最低价")) {
//			while (iterator.hasNext()) {
//				Map.Entry entry=(Map.Entry) iterator.next();
//				String code=(String) entry.getKey();
//				stockStatisticPO s=(stockStatisticPO) entry.getValue();
//				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getOpen());
//				arrayList.add(stockItemVO);
//			}
//		}else if (item.equals("收盘价")) {
//			while (iterator.hasNext()) {
//				Map.Entry entry=(Map.Entry) iterator.next();
//				String code=(String) entry.getKey();
//				stockStatisticPO s=(stockStatisticPO) entry.getValue();
//				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getClose());
//				arrayList.add(stockItemVO);
//			}
//		}else if (item.equals("后复权价")) {
//			while (iterator.hasNext()) {
//				Map.Entry entry=(Map.Entry) iterator.next();
//				String code=(String) entry.getKey();
//				stockStatisticPO s=(stockStatisticPO) entry.getValue();
//				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getAdj_price());
//				arrayList.add(stockItemVO);
//			}
//		}else if (item.equals("成交量")) {
//			while (iterator.hasNext()) {
//				Map.Entry entry=(Map.Entry) iterator.next();
//				String code=(String) entry.getKey();
//				stockStatisticPO s=(stockStatisticPO) entry.getValue();
//				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getVolume());
//				arrayList.add(stockItemVO);
//			}
//		}else if (item.equals("换手率")) {
//			while (iterator.hasNext()) {
//				Map.Entry entry=(Map.Entry) iterator.next();
//				String code=(String) entry.getKey();
//				stockStatisticPO s=(stockStatisticPO) entry.getValue();
//				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getTurnover());
//				arrayList.add(stockItemVO);
//			}
//		}else if (item.equals("市盈率")) {
//			while (iterator.hasNext()) {
//				Map.Entry entry=(Map.Entry) iterator.next();
//				String code=(String) entry.getKey();
//				stockStatisticPO s=(stockStatisticPO) entry.getValue();
//				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getPe());
//				arrayList.add(stockItemVO);
//			}
//		}else if (item.equals("市净率")) {
//			while (iterator.hasNext()) {
//				Map.Entry entry=(Map.Entry) iterator.next();
//				String code=(String) entry.getKey();
//				stockStatisticPO s=(stockStatisticPO) entry.getValue();
//				StockItemVO stockItemVO=new StockItemVO(s.getName(), code, ""+s.getPb());
//				arrayList.add(stockItemVO);
//			}
//		}
//		return arrayList;
//	}

	
	
}
