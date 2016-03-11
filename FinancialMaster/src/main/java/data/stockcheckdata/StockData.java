package data.stockcheckdata;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import PO.codeNamePO;
import PO.stockStatisticPO;
import dataservice.stockcheckdataservice.StockDataService;

public class StockData implements StockDataService {
	public codeNamePO getCodeName(int year, String exchange) {
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			String result = HttpRequest.sendGet(
					"http://121.41.106.89:8010/api/stocks/", "year=" + year
							+ "&exchange=" + exchange);
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

	@Override
	public ArrayList<stockStatisticPO> getStatisitcOfStock(String codeName,
			String start, String end) {
		// open;high;low;close;adj_price;volume;turnover;pe_ttm;pb;
		ArrayList<stockStatisticPO> arrayList = new ArrayList<stockStatisticPO>();
		try {
			String result = HttpRequest
					.sendGet("http://121.41.106.89:8010/api/stock/" + codeName,
							"start=" + start + "&end=" + end
									+ "&fields=open+high+low+close+adj_price+volume+turnover+pe_ttm+pb");
			JSONObject jsonObject = new JSONObject(result);
			JSONObject jsonObject2 = jsonObject.getJSONObject("data");
			JSONArray jsonArray = jsonObject2.getJSONArray("trading_info");
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject3 = jsonArray.getJSONObject(i);
				//这三个数据有些为空值
				 Double turnover=-1.0,pe_ttm=-1.0,pb=-1.0;
				 try {
					turnover=jsonObject3.getDouble("turnover");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				 try {
					 pe_ttm=jsonObject3.getDouble("pe_ttm");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				 try {
					 pb=jsonObject3.getDouble("pb");
				} catch (JSONException e) {
					e.printStackTrace();
				}			 
				 //////分割线//////
				 stockStatisticPO stockStatisticPO=new
				 stockStatisticPO(jsonObject3.getString("date"),
				 jsonObject3.getDouble("open"),
				 jsonObject3.getDouble("high"),				 
				 jsonObject3.getDouble("low"),
				 jsonObject3.getDouble("close"),
				 jsonObject3.getDouble("adj_price"),
				 jsonObject3.getInt("volume"),
				 turnover,
				 pe_ttm,				 
				 pb);
				 arrayList.add(stockStatisticPO);
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
		return arrayList;
	}
}
