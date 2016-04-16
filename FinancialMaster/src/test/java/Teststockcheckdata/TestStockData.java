package Teststockcheckdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import PO.codeNamePO;
import PO.stockStatisticPO;
import data.stockcheckdata.StockData;

@RunWith(Parameterized.class)
public class TestStockData {
	private StockData stockData;
	private String result;
	private String exchange;
	private int year;
	@Parameters
	public static Collection DatabaseMetaData(){
		return Arrays.asList(new Object[][] { { 2014,"sh","sh600000"}, { 2015,"sh","sh600216"}});
	}
	
	@Before
	public void setUp(){
		stockData=new StockData();
	}
	
	public TestStockData(int year,String exchange,String result){
		this.year=year;
		this.exchange=exchange;
		this.result=result;
	}
	
	@Test
	public void testgetCodeName(){
		codeNamePO codeNamePO=stockData.getCodeName(year, exchange);
		Assert.assertEquals(result, codeNamePO.getResult().get(0));
	}
	
	@Test
	public void testgetStatisticOfStock(){
		ArrayList<stockStatisticPO> arrayList=stockData.getStatisitcOfStock("sh600000", "2014-10-10", "2015-10-10");
		stockStatisticPO statisticPO1=arrayList.get(0),statisticPO2=arrayList.get(1);
		Assert.assertEquals(""+9.91, ""+statisticPO1.getHigh());
		Assert.assertEquals(""+9.83, ""+statisticPO2.getHigh());
	}
}
