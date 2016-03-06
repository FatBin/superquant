package data.FieldData;

import org.json.JSONArray;
import org.json.JSONObject;

import PO.fieldStatisticPO;
import data.stockcheckdata.HttpRequest;
import dataservice.stockcheckdataservice.FieldDataService;

public class FieldData implements FieldDataService{
	@Override
	public fieldStatisticPO getStatisticOfField() {
		// TODO Auto-generated method stub
		fieldStatisticPO fieldStatisticPO;
		try {
			String result=HttpRequest.sendGet("http://121.41.106.89:8010/api/stock/fields", "");
			JSONObject jsonObject=new JSONObject(result);
			JSONArray jsonArray=jsonObject.getJSONArray("data");
			fieldStatisticPO=new fieldStatisticPO(jsonArray.getString(0).toString(), jsonArray.getString(1).toString(), jsonArray.getString(2).toString(),
					jsonArray.getString(3).toString(), jsonArray.getString(4).toString(), jsonArray.getString(5).toString(), jsonArray.getString(6).toString(), jsonArray.getString(7).toString(), jsonArray.getString(8).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fieldStatisticPO=new fieldStatisticPO("", "", "", "", "", "", "", "", "");
		}
		return fieldStatisticPO;
	}
}
