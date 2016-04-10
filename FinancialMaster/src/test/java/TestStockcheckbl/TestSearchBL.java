package TestStockcheckbl;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import businesslogic.stockcheckbl.StockSearchBL;

@RunWith(Parameterized.class)
public class TestSearchBL {
	private StockSearchBL StockSearchBL;
	private String key;
	private String result;
	@Parameters
	public static Collection DatabaseMetaData(){
		return Arrays.asList(new Object[][] { { "2600","sz002600"}, { "1002","sh601002"}, { "145","sz002145sz300145sh600145"}});
	}
	
	@Before
	public void SetUp(){
		StockSearchBL= new StockSearchBL();
	}
	public TestSearchBL(String key,String result){
		this.key=key;
		this.result=result;
	}
	
	@Test
	public void TestgetList(){
		String r="";
		String[][] strings=StockSearchBL.getList(key);
		for (int i=0;i<strings.length;i++) {
			r+=strings[i][0];
		}
		Assert.assertEquals(result, r);
	}
}
