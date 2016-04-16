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
		System.out.println(StockMessageBL.getStockMessage(key).getOpen());
		System.out.println(StockMessageBL.getStockMessage(key).getHigh());
		System.out.println(StockMessageBL.getStockMessage(key).getLow());
		System.out.println(StockMessageBL.getStockMessage(key).getClose());
		System.out.println(StockMessageBL.getStockMessage(key).getAdj_price());
		System.out.println(StockMessageBL.getStockMessage(key).getVolume());
		System.out.println(StockMessageBL.getStockMessage(key).getTurnover());
		System.out.println(StockMessageBL.getStockMessage(key).getPe());
		System.out.println(StockMessageBL.getStockMessage(key).getPb());
		System.out.println(StockMessageBL.getStockMessage(key).getUps_and_lows());
		System.out.println(StockMessageBL.getStockMessage(key).getStockMarketVO().getChangeRange());
		System.out.println(StockMessageBL.getStockMessage(key).getTamplitude());
		System.out.println(StockMessageBL.getStockMessage(key).getQuantity_relative_ratio());
		System.out.println(StockMessageBL.getStockMessage(key).getKLine_data()[0][0]);
		System.out.println(StockMessageBL.getStockMessage(key).getHistory_data()[0][0]);
		System.out.println(StockMessageBL.getStockMessage(key).getRatio());

		Assert.assertEquals(null, StockMessageBL.getStockMessage(key));
	}
}
