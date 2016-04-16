package TestStockcheckbl;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import businesslogic.stockcheckbl.StockListBL;

@RunWith(Parameterized.class)
public class TestStockListBL {
	private StockListBL stockListBL;
	private String result;
	private String key;
	@Parameters
	public static Collection DatabaseMetaData(){
		return Arrays.asList(new Object[][] { { "300","sz300027"}, { "-",""}});
	}
	
	@Before
	public void setUp(){
		stockListBL=new StockListBL();
	}
	public TestStockListBL(String key,String result){
		this.key=key;
		this.result=result;
	}
	
	
	@Test
	public void testupdateStockList(){
		String[][] array=stockListBL.updateStockList(key);
		String r="";
		for (int i = 0; i < array.length; i++) {
			r=r+array[i][0];
		}
		Assert.assertEquals(result, r);
	}
}
