package data.IndustryData;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import PO.industriesPO;
import data.IO.HttpRequest;

public class IndustriesUpdate implements Runnable {
	public static final String[] industries = { "http://q.10jqka.com.cn/interface/stock/thshy/zdf/desc/",
			"/quote/quote" };
	public static final int[] numbers = { 1, 2 };
	
	public static ArrayList<industriesPO> arrayList;
	public IndustriesUpdate(){
		arrayList=new ArrayList<>(); 
		Thread thread=new Thread(this);
		thread.start();
	}

	public ArrayList<industriesPO> getIndustryData(){
		ArrayList<industriesPO> arrayList = new ArrayList<>();
		try {
			for (int i = 0; i < numbers.length; i++) {
				String url = industries[0] + numbers[i] + industries[1];
				String temp = HttpRequest.sendGet(url, "");
				JSONObject jsonObject = new JSONObject(temp);

				JSONArray jsonArray = jsonObject.getJSONArray("data");
				for (int j = 0; j < jsonArray.length(); j++) {
					JSONObject jObject = jsonArray.getJSONObject(j);
					industriesPO industriesPO = new industriesPO(jObject.getString("platename"),
							Integer.parseInt(jObject.getString("num")), Double.parseDouble(jObject.getString("jj")),
							Double.parseDouble(jObject.getString("zdf")), Double.parseDouble(jObject.getString("cjl")),
							Double.parseDouble(jObject.getString("cje")), Double.parseDouble(jObject.getString("jlr")),
							jObject.getString("gainername"), Double.parseDouble(jObject.getString("gainerzxj")),
							Double.parseDouble(jObject.getString("gainerzdf")));
					arrayList.add(industriesPO);

				}
			}
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;

	}
	
	
	public static ArrayList<industriesPO> getIndustries() {
		return arrayList;
	}


	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10000);
				arrayList = getIndustryData();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}

}
