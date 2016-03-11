package TestStockmarketdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import PO.benchmarkStatisticPO;
import data.stockmarketdata.BenchData;
@RunWith(Parameterized.class)
public class TestBenchData {
	private BenchData benchData;
	private String benchCode,start,end;
	private double result;
	
	@Parameters
	public static Collection DatabaseMetaData(){
		return Arrays.asList(new Object[][] { { 3533.71,"hs300","2015-01-01","2015-01-30"}});
	}
	
	@Before
	public void setUp(){
		benchData=new BenchData();
	}
	public TestBenchData(double result,String benchCode,String start,String end) {
		// TODO Auto-generated constructor stub
		this.result=result;
		this.benchCode=benchCode;
		this.start=start;
		this.end=end;
	}
	
	@Test
	public void testgetStatisticOfBenchimark(){
		ArrayList<benchmarkStatisticPO> arrayList=benchData.getStatisticOfBenchmark(benchCode, start, end);
		benchmarkStatisticPO benchmarkStatisticPO=arrayList.get(0);
		Assert.assertEquals(""+result, ""+benchmarkStatisticPO.getClose());
	}
}
