package TestManageStockBL;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ENUM.ManageState;
import ENUM.attentionState;
import businesslogic.managestockbl.ManageStockBL;
import businesslogic.stockcheckbl.StockSearchBL;

@RunWith(Parameterized.class)
public class TestManageStockBL {
	private ManageStockBL managestockbl;
	private String key;
	private attentionState result;
	
	@Parameters
	public static Collection DatabaseMetaData(){
		return Arrays.asList(new Object[][] { 
			{ attentionState.Yes,"sh600115"}, 
			{ attentionState.No,"sh601002"}, 
			{ attentionState.No,"sz30022"},
			{ attentionState.No,"sh601001"}});
	}
	
	@Before
	public void SetUp(){
		managestockbl=new ManageStockBL();
	}
	public TestManageStockBL(attentionState state,String key) {
		this.key=key;
		this.result=state;
	}
	
	@Test
	public void TestgetList(){
		Assert.assertEquals(result, managestockbl.isAttented(key));
		
	}
}
