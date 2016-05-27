package data.IndustryData;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import PO.industriesPO;
import PO.industryPO;
import data.IO.HttpRequest;
import dataservice.IndustryDataService.IndustryDataService;

public class IndustryData implements IndustryDataService{
	public static final String[] industries={"http://q.10jqka.com.cn/interface/stock/thshy/zdf/desc/","/quote/quote"};
	public static final int[] numbers={1,2};
	public static final String[] industry={"http://q.10jqka.com.cn/interface/stock/detail/zdf/desc/","/1/"};
	public static final int[] number={1,2,3,4};
	@Override
	public ArrayList<industriesPO> getIndustryData() throws Exception{
		ArrayList<industriesPO> arrayList=new ArrayList<>();
		try {
			for(int i=0;i<numbers.length;i++){
				String url=industries[0]+numbers[i]+industries[1];
				String temp=HttpRequest.sendGet(url, "");
				System.out.println(temp);
				JSONObject jsonObject=new JSONObject(temp);
				
				JSONArray jsonArray=jsonObject.getJSONArray("data");
				for(int j=0;j<jsonArray.length();j++){
					JSONObject jObject=jsonArray.getJSONObject(j);
					industriesPO industriesPO=new industriesPO(
							jObject.getString("platename"),
							Integer.parseInt(jObject.getString("num")),
							Double.parseDouble(jObject.getString("jj")), 
							Double.parseDouble(jObject.getString("zdf")), 
							Double.parseDouble(jObject.getString("cjl")), 
							Double.parseDouble(jObject.getString("cje")), 
							Double.parseDouble(jObject.getString("jlr")), 
							jObject.getString("gainername"), 
							Double.parseDouble(jObject.getString("gainerzxj")), 
							Double.parseDouble(jObject.getString("gainerzdf")));
					arrayList.add(industriesPO);

				}
			}
			return arrayList;
		} catch (Exception e) {
				throw e;
		}


	}
	
	
	
	@Override
	public ArrayList<industryPO> getIndustry(String industryName) {
		ArrayList<industryPO> arrayList=new ArrayList<>();
		try {
			for(int i=0;i<numbers.length;i++){
				String url=industries[0]+numbers[i]+industries[1];
				String temp=HttpRequest.sendGet(url, "");
				System.out.println(temp);
				JSONObject jsonObject=new JSONObject(temp);
				JSONArray jsonArray=jsonObject.getJSONArray("data");
				for(int j=0;j<jsonArray.length();j++){
					JSONObject jObject=jsonArray.getJSONObject(j);
					industryPO industryPO=new industryPO(
							jObject.getString("platename"),
							Double.parseDouble(jObject.getString("stockname")),
							Double.parseDouble(jObject.getString("zxj")), 
							Double.parseDouble(jObject.getString("zdf")), 
							Double.parseDouble(jObject.getString("cjl")), 
							Double.parseDouble(jObject.getString("cje")), 
							Double.parseDouble(jObject.getString("jlr")), 
							Double.parseDouble(""), 
							Double.parseDouble(jObject.getString("gainerzxj")), 
							Double.parseDouble(jObject.getString("gainerzdf")),
							Double.parseDouble(jObject.getString("gainerzdf")),
							Double.parseDouble(jObject.getString("gainerzdf"))
							);
					arrayList.add(industryPO);

				}
			}
			return arrayList;
		} catch (Exception e) {
				throw e;
		}
	}

}
