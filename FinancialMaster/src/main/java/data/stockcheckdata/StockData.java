package data.stockcheckdata;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import PO.codeNamePO;
import PO.stockStatisticPO;
import data.IO.FileManager;
import data.IO.HttpRequest;
import dataservice.stockcheckdataservice.StockDataService;

public class StockData implements StockDataService {
	// 输入年份及交易所返回所有股票名
	public codeNamePO getCodeName(int year, String exchange) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			String result = HttpRequest.sendGet("http://121.41.106.89:8010/api/stocks/",
					"year=" + year + "&exchange=" + exchange);
			JSONObject jsonObject = new JSONObject(result);
			JSONArray jsonArray = jsonObject.getJSONArray("data");

			for (int i = 0; i < jsonArray.length(); i++) {
				arrayList.add(jsonArray.getJSONObject(i).getString("name"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		codeNamePO codeNamePO = new codeNamePO(arrayList);
		return codeNamePO;
	}

	// 输入股票代号及起始和终止时间，返回股票具体信息
	@Override
	public ArrayList<stockStatisticPO> getStatisitcOfStock(String codeName, String start, String end) {
		// open;high;low;close;adj_price;volume;turnover;pe_ttm;pb;
		ArrayList<stockStatisticPO> arrayList = new ArrayList<stockStatisticPO>();
		try {
			String result = HttpRequest.sendGet("http://121.41.106.89:8010/api/stock/" + codeName, "start=" + start
					+ "&end=" + end + "&fields=open+high+low+close+adj_price+volume+turnover+pe_ttm+pb");
			JSONObject jsonObject = new JSONObject(result);
			JSONObject jsonObject2 = jsonObject.getJSONObject("data");
			JSONArray jsonArray = jsonObject2.getJSONArray("trading_info");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject3 = jsonArray.getJSONObject(i);
				// 这三个数据有些为空值
				Double turnover = -1.0, pe_ttm = -1.0, pb = -1.0;
				try {
					turnover = jsonObject3.getDouble("turnover");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				try {
					pe_ttm = jsonObject3.getDouble("pe_ttm");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				try {
					pb = jsonObject3.getDouble("pb");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				// 获取股票名
				String name = "";
				try {
					String s = HttpRequest.sendGet("http://web.juhe.cn:8080/finance/stock/hs",
							"gid=" + codeName + "&key=9867ab78e2748061825600a8f7c7258b");
					JSONObject juheshuju = new JSONObject(s);
					JSONArray newjsonArray = juheshuju.getJSONArray("result");
					JSONObject newjsonObject = newjsonArray.getJSONObject(0);
					name = newjsonObject.getJSONObject("dapandata").getString("name");
				} catch (Exception e) {
					// TODO: handle exception
				}
				////// 分割线//////
				stockStatisticPO stockStatisticPO = new stockStatisticPO(name, jsonObject3.getString("date"),
						jsonObject3.getDouble("open"), jsonObject3.getDouble("high"), jsonObject3.getDouble("low"),
						jsonObject3.getDouble("close"), jsonObject3.getDouble("adj_price"),
						jsonObject3.getInt("volume"), turnover, pe_ttm, pb);
				arrayList.add(stockStatisticPO);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return arrayList;
	}

	// 初始化时调用这个方法
	public ArrayList<stockStatisticPO> getStatisitcOfStock(String codeName){
		File target=new File("src/main/resources/Data/LocalDataBuffer"+codeName+".txt");
		//如果該緩存不存在,新建一個
		if (!target.exists() || target.isDirectory()) {
			try {
				target.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar calendar=Calendar.getInstance();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String end=simpleDateFormat.format(calendar.getTime());
			calendar.add(Calendar.MONTH, -3);
			String start=simpleDateFormat.format(calendar.getTime());
			ArrayList<stockStatisticPO> arrayList=getStatisitcOfStock(codeName, start, end);
			ArrayList<String> DataBuffer=new ArrayList<>();
			for (stockStatisticPO stockStatisticPO : arrayList) {
				String result=stockStatisticPO.getName()+";"+stockStatisticPO.getDate()+";"+
							  stockStatisticPO.getOpen()+";"+stockStatisticPO.getHigh()+";"+
							  stockStatisticPO.getLow()+";"+stockStatisticPO.getClose()+";"+
							  stockStatisticPO.getAdj_price()+";"+stockStatisticPO.getVolume()+";"+
							  stockStatisticPO.getTurnover()+";"+stockStatisticPO.getPe()+";"+
							  stockStatisticPO.getPb();
				DataBuffer.add(result);
			}
			FileManager.WriteFile(DataBuffer, "src/main/resources/Data/LocalDataBuffer"+codeName+".txt", false);
			return arrayList;
		}else{
			//當原來有緩存時
			ArrayList<String> dataBuffer=FileManager.ReadFile("src/main/resources/Data/LocalDataBuffer"+codeName+".txt");
			ArrayList<stockStatisticPO> arrayList=new ArrayList<>();
			for (String string : dataBuffer) {
				String[] s=string.split(";");
				stockStatisticPO stockStatisticPO=new stockStatisticPO(s[0], s[1], 
						Double.parseDouble(s[2]), Double.parseDouble(s[3]),
						Double.parseDouble(s[4]), Double.parseDouble(s[5]),
						Double.parseDouble(s[6]), Integer.parseInt(s[7]), 
						Double.parseDouble(s[8]), Double.parseDouble(s[9]), 
						Double.parseDouble(s[10]));
				arrayList.add(stockStatisticPO);
			}
			
			//防止原來獲取的數據為空
			if(arrayList.size()==0){
				Calendar calendar=Calendar.getInstance();
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String end=simpleDateFormat.format(calendar.getTime());
				calendar.add(Calendar.MONTH, -3);
				String start=simpleDateFormat.format(calendar.getTime());
				arrayList=getStatisitcOfStock(codeName, start, end);
			}else{
				Calendar calendar=Calendar.getInstance();
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String end=simpleDateFormat.format(calendar.getTime());
				String lastday=dataBuffer.get(dataBuffer.size()-1);
				ArrayList<stockStatisticPO> otherStatisticPOs=getStatisitcOfStock(codeName, lastday.split(";")[0], end);
				arrayList.addAll(otherStatisticPOs);
			}
			return arrayList;
		}

	}
}
