package data.stockmarketdata;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import PO.benchmarkPO;
import PO.benchmarkStatisticPO;
import data.IO.HttpRequest;
import dataservice.stockmarketdataservice.BenchDataService;

public class BenchData implements BenchDataService{
	//获取大盘指数代号
	@Override
	public benchmarkPO getBenchmark() {
		// TODO Auto-generated method stub
		ArrayList<String> arrayList=new ArrayList<String>();
		try {
			String result=HttpRequest.sendGet("http://121.41.106.89:8010/api/benchmark/all", "");
			JSONObject jsonObject=new JSONObject(result);
			JSONArray jsonArray=jsonObject.getJSONArray("data");
			for(int i=0;i<jsonArray.length();i++){
				JSONObject jsonObject2=jsonArray.getJSONObject(i);
				arrayList.add(jsonObject2.getString("name"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		benchmarkPO benchmarkPO=new benchmarkPO(arrayList);
		return benchmarkPO;
	}

	//根据大盘代号和起始终止时间获取大盘指数数据
	@Override
	public ArrayList<benchmarkStatisticPO> getStatisticOfBenchmark(String benchCode, String start, String end) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ArrayList<benchmarkStatisticPO> arrayList=new ArrayList<benchmarkStatisticPO>();
				try {
					String result=HttpRequest.sendGet("http://121.41.106.89:8010/api/benchmark/"+benchCode, "start="+start+"&end="+end+"&fields=open+close+high+volume+adj_price+low");
					JSONObject jsonObject=new JSONObject(result);
					JSONObject jsonObject2=jsonObject.getJSONObject("data");
					JSONArray jsonArray=jsonObject2.getJSONArray("trading_info");
					for(int i=0;i<jsonArray.length();i++){
						JSONObject jsonObject3=jsonArray.getJSONObject(i);
						Double low=-1.0,high=-1.0,adj_price=-1.0;
						long volume=-1;
						try {
							low=jsonObject3.getDouble("low");
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						try {
							high=jsonObject3.getDouble("high");
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						try {
							adj_price=jsonObject3.getDouble("adj_price");
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						try {
							volume=jsonObject3.getLong("volume");
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						benchmarkStatisticPO benchmarkStatisticPO=new benchmarkStatisticPO(
								jsonObject3.getString("date"), 
								jsonObject3.getDouble("close"), 
								jsonObject3.getDouble("open"),
								low,
								high,
								adj_price,
								volume);
						arrayList.add(benchmarkStatisticPO);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return arrayList;
	}

	
}
