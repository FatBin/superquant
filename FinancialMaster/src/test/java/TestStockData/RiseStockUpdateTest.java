package TestStockData;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import DAO.connection.DBconnection;

public class RiseStockUpdateTest {
	DBconnection dBconnection;
	@Before
	public void init(){
		dBconnection=new DBconnection();
	}
	
	
	@Test
	public void testRiseStockUpdate() {
		
	}

//	@Test
//	public void testGetRiseStock() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetRiseStockPOs() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRun() {
//		fail("Not yet implemented");
//	}

}
