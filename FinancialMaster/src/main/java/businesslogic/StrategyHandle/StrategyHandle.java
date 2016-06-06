package businesslogic.StrategyHandle;

import java.util.ArrayList;

public class StrategyHandle {
	public ArrayList<String[]> handle(ArrayList<String[]> arrayList){
		ArrayList<String[]> eachResult=new ArrayList<>();
		for (String[] strings : arrayList) {
			eachResult.add(countEachDay(strings));
		}
		return null;
	}

	private String[] countEachDay(String[] strings) {
		
		return null;
	}
}
