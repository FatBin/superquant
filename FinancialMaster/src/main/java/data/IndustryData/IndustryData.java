package data.IndustryData;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxyService.IndustriesDaoProxyService;
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
		return IndustryDataUpdate.getIndustries();
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
						String stockId=jObject.getString("stockcode");
						if (stockId.charAt(0)=='6') {
							stockId="sh"+stockId;
						}else {
							stockId="sz"+stockId;
						}
						industryPO industryPO = new industryPO(stockId,
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

	@Override
	public List getIndustryDuringTime(String industryName, String starttime, String endtime)
			throws Exception {
		IndustriesDaoProxyService industriesDaoProxyService=DaoFactory.getIndustriesDaoProxy();
		try {
			return industriesDaoProxyService.getIndustryRecord(industryName, starttime, endtime);
		} catch (Exception e) {
			throw e;
		}
	}
	
}
