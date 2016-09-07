package strategyHandle;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.connection.DBconnection;
import PO.profitPO;
import web.bl.StrategyHandle.PerfectStrategyHandle;

public class TestPerfectStrategyHandle {
	DBconnection dBconnection;
	PerfectStrategyHandle perfectStrategyHandle;
	@Before
	public void init(){
		dBconnection=new DBconnection();
		perfectStrategyHandle=new PerfectStrategyHandle();
	}
	
	@Test
	public void TestGetProfit(){
		ArrayList<profitPO> arrayList=perfectStrategyHandle.getProfit("sh600000", "2011-01-01", "2011-01-31", 1000); 
//		for (profitPO profitPO : arrayList) {
//			System.out.println(profitPO.getDate()+" ; "+profitPO.getProfit()); 
//		}
	}
}
