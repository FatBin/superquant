package web.bl.StrategyHandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class StrategyMap {
	private static Map<String, Class<? extends Strategy>> map=new HashMap();
	static{
		map.put("price", Price.class);
		map.put("volume", Volume.class);
		map.put("turnover", Turnover.class);
		map.put("pe", Pe.class);
		map.put("pb", Pb.class);
		map.put("date", Time.class);
	}
	
	public static ArrayList<Strategy> getStrategy(){
		ArrayList<Strategy> arrayList=new ArrayList<>();
		try {
			for(Entry<String, Class<? extends Strategy>> entry:map.entrySet()){
				Class<? extends Strategy> clazz=entry.getValue();
				Strategy strategy=clazz.newInstance();
				arrayList.add(strategy);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
}
