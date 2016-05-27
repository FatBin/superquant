package data.IndustryData;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import PO.industriesPO;
import PO.industryPO;
import data.IO.HttpRequest;
import dataservice.IndustryDataService.IndustryDataService;

public class IndustryData implements IndustryDataService {
	public static final String[] industries = { "http://q.10jqka.com.cn/interface/stock/thshy/zdf/desc/",
			"/quote/quote" };
	public static final int[] numbers = { 1, 2 };
	public static final String[] industry = { "http://q.10jqka.com.cn/interface/stock/detail/zdf/desc/", "/1/" };
	public static final int[] number = { 1, 2, 3, 4 };

	@Override
	public ArrayList<industriesPO> getIndustryData() throws Exception {
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
			throw e;
		}

	}

	@Override
	public ArrayList<industryPO> getIndustry(String industryName) throws Exception {
		try {
			ArrayList<industryPO> arrayList = new ArrayList<>();
			for (int page = 0; page < number.length; page++) {
				try {
					String url = industry[0] + number[page] + industry[1]
							+ FirstLetterUtil.getFirstLetter(industryName);
					String temp = HttpRequest.sendGet(url, "");
					JSONObject jsonObject = new JSONObject(temp);
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					for (int j = 0; j < jsonArray.length(); j++) {
						JSONObject jObject = jsonArray.getJSONObject(j);
						industryPO industryPO = new industryPO(jObject.getString("stockcode"),
								Double.parseDouble(jObject.getString("zxj")),
								Double.parseDouble(jObject.getString("zde")),
								Double.parseDouble(jObject.getString("zdf")),
								Double.parseDouble(jObject.getString("zs")),
								Double.parseDouble(jObject.getString("jk")),
								Double.parseDouble(jObject.getString("zgj")),
								Double.parseDouble(jObject.getString("zdj")),
								Double.parseDouble(jObject.getString("jlr")),
								Double.parseDouble(jObject.getString("cjl")),
								Double.parseDouble(jObject.getString("cje")),
								Double.parseDouble(jObject.getString("hsl")));
						arrayList.add(industryPO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return arrayList;
		} catch (Exception e) {
			throw e;
		}

	}

}
