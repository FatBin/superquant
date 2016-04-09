package data.IO;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import PO.codeNamePO;
import dataservice.connection.InitDataService;

public class InitData implements InitDataService{

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		//输入年份及交易所返回所有股票名
			ArrayList<String> arrayList = new ArrayList<String>();
//			try {
//				String result = HttpRequest.sendGet(
//						"http://121.41.106.89:8010/api/stocks/", "year=" + year
//								+ "&exchange=" + exchange);
//				JSONObject jsonObject = new JSONObject(result);
//				JSONArray jsonArray = jsonObject.getJSONArray("data");
//
//				for (int i = 0; i < jsonArray.length(); i++) {
//					arrayList.add(jsonArray.getJSONObject(i).getString("name"));
//				}
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			codeNamePO codeNamePO = new codeNamePO(arrayList);
		}
	}