package businesslogic.StrategyHandle;

import java.util.ArrayList;

public class test {
	public static void main(String[] args) {
		ArrayList<Strategy> arrayList=StrategyMap.getStrategy();
		for (Strategy strategy : arrayList) {
			System.out.println(strategy.getClass().getName());
		}
	}
}
