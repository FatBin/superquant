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
		return Arrays.asList(new Object[][] { { "300","sh600300sh603008"}, { "-","-"}});
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
	public void testgetStockList(){
		String[][] r=stockListBL.getStockList();
		Assert.assertEquals("sh600000,0.0,0.0,0.0,18.45,0", r[0][0]+","+r[0][1]+","+r[0][2]+","+r[0][3]+","+r[0][4]+","+r[0][5]);
	}
	
	@Test
	public void testupdateStockList(){
		String[][] array=stockListBL.updateStockList(key);
		String r="";
		for (int i = 0; i < array.length; i++) {
			r=r+array[i][0];
		}
		System.out.print(array[0][0]);
		Assert.assertEquals(result, r);
	}
}
