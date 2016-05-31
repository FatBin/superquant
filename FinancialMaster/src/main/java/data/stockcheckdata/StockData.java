package data.stockcheckdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import PO.codeNamePO;
import PO.stockStatisticPO;
import data.IO.FileManager;
import data.IO.HttpRequest;
import dataservice.stockcheckdataservice.StockDataService;
import servlet.factory.InitFactoryServlet;

public class StockData implements StockDataService {
	// 输入年份及交易所返回所有股票名
	public codeNamePO getCodeName(int year, String exchange) {
		ArrayList<String> arrayList = new ArrayList<String>();//返回的arraylist

		//先判斷是否在文件中
		String path=InitFactoryServlet.getPath();
		File file=new File(path+"Data/LocalDataBuffer/"+year+"_"+exchange+".txt");
		if(!file.exists()||file.isDirectory()){
				try {
					file.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			try {
				//数据处理
				String result = HttpRequest.sendGet("http://121.41.106.89:8010/api/stocks/",
						"year=" + year + "&exchange=" + exchange);
				JSONObject jsonObject = new JSONObject(result);
				JSONArray jsonArray = jsonObject.getJSONArray("data");
	
				for (int i = 0; i < jsonArray.length(); i++) {
					arrayList.add(jsonArray.getJSONObject(i).getString("name"));
				}
				//写入
				FileManager.WriteFile(arrayList, "Data/LocalDataBuffer/"+year+"_"+exchange+".txt", false);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}else{
			Calendar calendar=Calendar.getInstance();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
			String thisYear=simpleDateFormat.format(calendar.getTime());
			//同一年的也要 更新
			if (thisYear.equals(""+year)) {
				try {
					//数据处理
					String result = HttpRequest.sendGet("http://121.41.106.89:8010/api/stocks/",
							"year=" + year + "&exchange=" + exchange);
					JSONObject jsonObject = new JSONObject(result);
					JSONArray jsonArray = jsonObject.getJSONArray("data");
		
					for (int i = 0; i < jsonArray.length(); i++) {
						arrayList.add(jsonArray.getJSONObject(i).getString("name"));
					}
					//写入
					FileManager.WriteFile(arrayList, "Data/LocalDataBuffer/"+year+"_"+exchange+".txt", false);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else{
				arrayList=FileManager.ReadFile("Data/LocalDataBuffer/"+year+"_"+exchange+".txt");
			}
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
//					String s = HttpRequest.sendGet("http://web.juhe.cn:8080/finance/stock/hs",
//							"gid=" + codeName + "&key=9867ab78e2748061825600a8f7c7258b");
//					JSONObject juheshuju = new JSONObject(s);
//					JSONArray newjsonArray = juheshuju.getJSONArray("result");
//					JSONObject newjsonObject = newjsonArray.getJSONObject(0);
//					name = newjsonObject.getJSONObject("dapandata").getString("name");
					String string=new String(data.stockcheckdata.StockData.sendGet("http://hq.sinajs.cn/list="+codeName+",", ""));
					name=string.split("\"")[1].split(",")[0];
				} catch (Exception e) {
					e.printStackTrace();
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
		File target=new File(InitFactoryServlet.getPath()+"Data/LocalDataBuffer/"+codeName+".txt");
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
			calendar.add(Calendar.DATE, 1);
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
			FileManager.WriteFile(DataBuffer, "Data/LocalDataBuffer/"+codeName+".txt", false);
			return arrayList;
		}else{
			//當原來有緩存時
			ArrayList<String> dataBuffer=FileManager.ReadFile("Data/LocalDataBuffer/"+codeName+".txt");
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
				calendar.add(Calendar.DATE, 1);
				calendar.add(Calendar.MONTH, -3);
				String start=simpleDateFormat.format(calendar.getTime());
				arrayList=getStatisitcOfStock(codeName, start, end);
			}else{
				Calendar calendar=Calendar.getInstance();
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String today=simpleDateFormat.format(calendar.getTime());//當天
				calendar.add(Calendar.DATE, 1);
				String end=simpleDateFormat.format(calendar.getTime());//當天+1
				String lastday=dataBuffer.get(dataBuffer.size()-1).split(";")[1];//緩存中的最新數據
				//緩存數據日期加一
						Date lastDate;
						try {
							lastDate = simpleDateFormat.parse(lastday);
							calendar.setTime(lastDate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				calendar.add(Calendar.DATE,1);
				String lastday1=simpleDateFormat.format(calendar.getTime());//
				//如果已經是最新的一天，不再請求
				if (!lastday.equals(today)) {
					ArrayList<stockStatisticPO> otherStatisticPOs=getStatisitcOfStock(codeName, lastday1, end);
					arrayList.addAll(otherStatisticPOs);
					for (stockStatisticPO stockStatisticPO : otherStatisticPOs) {
						String result=stockStatisticPO.getName()+";"+stockStatisticPO.getDate()+";"+
									  stockStatisticPO.getOpen()+";"+stockStatisticPO.getHigh()+";"+
									  stockStatisticPO.getLow()+";"+stockStatisticPO.getClose()+";"+
									  stockStatisticPO.getAdj_price()+";"+stockStatisticPO.getVolume()+";"+
									  stockStatisticPO.getTurnover()+";"+stockStatisticPO.getPe()+";"+
									  stockStatisticPO.getPb();
						dataBuffer.add(result);
					}
					FileManager.WriteFile(dataBuffer, "Data/LocalDataBuffer/"+codeName+".txt", false);
				}
			}
			return arrayList;
		}

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
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gbk"));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
