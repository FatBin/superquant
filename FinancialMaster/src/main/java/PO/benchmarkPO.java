package PO;

import java.util.ArrayList;

public class benchmarkPO {

	private ArrayList<String> benchmark;

	public benchmarkPO(ArrayList<String> benchmark) {
		super();
		setBenchmark(benchmark);
	}

	public ArrayList<String> getBenchmark() {
		return benchmark;
	}

	private void setBenchmark(ArrayList<String> newbenchmark) {
		benchmark=new ArrayList<String>();
		for (String string : newbenchmark) {
			benchmark.add(string);
		}
	}
	
}
