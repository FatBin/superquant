package TestStockContrastbl;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import businesslogic.stockContrastbl.StockContrastBL;
import businesslogic.stockcheckbl.StockSearchBL;

@RunWith(Parameterized.class)
public class TestStockContrastBL {
	private StockContrastBL StockContrastBL;
	private String key;
	private double r1;
	private double r2;
	private double r3;
	private double r4;
	@Parameters
	public static Collection DatabaseMetaData(){
		return Arrays.asList(new Object[][] { { "π‚√˜»È“µ",-0.03,4.07,38.09,0.77}});
	}
	
	@Before
	public void SetUp(){
		StockContrastBL= new StockContrastBL();
	}
	public TestStockContrastBL(String key,double r1,double r2,double r3,double r4){
		this.key=key;
		this.r1=r1;
		this.r2=r2;
		this.r3=r3;
		this.r4=r4;
	}
	
	@Test
	public void TestgetList(){
		double[] result=StockContrastBL.getData(key);
		DecimalFormat df=new DecimalFormat("######0.00");
		Assert.assertEquals(r1+"", df.format(result[0])+"");
		Assert.assertEquals(r2+"", df.format(result[1])+"");
		Assert.assertEquals(r3+"", df.format(result[2])+"");
		Assert.assertEquals(r4+"", df.format(result[3])+"");
	}
}
