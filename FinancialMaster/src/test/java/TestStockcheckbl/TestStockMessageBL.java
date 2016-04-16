package TestStockcheckbl;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import PO.codeNamePO;
import businesslogic.stockcheckbl.StockMessageBL;
import data.stockcheckdata.StockData;

@RunWith(Parameterized.class)
public class TestStockMessageBL {
	private StockMessageBL StockMessageBL;
	private String key;
	@Parameters
	public static Collection DatabaseMetaData(){
		return Arrays.asList(new Object[][] { { "sz002703"}});
	}
	
	@Before
	public void setUp(){
		StockMessageBL=new StockMessageBL();
	}
	
	public TestStockMessageBL(String key){
		this.key=key;
	}
	
	@Test
	public void testgetCodeName(){
		System.out.println(StockMessageBL.getStockMessage(key).getName());
		System.out.println(StockMessageBL.getStockMessage(key).getDate());
		System.out.println(StockMessageBL.getStockMessage(key).getQuantity_relative_ratio());

		Assert.assertEquals(null, StockMessageBL.getStockMessage(key));
	}
}
