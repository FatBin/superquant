package web.bl.simulationImpl;

import java.util.Calendar;
import java.util.Date;

import DAO.connection.DBconnection;
import DAO.pojo.SimulationProfit;
import data.SimulationData.SimulationProfitData;

public class test {
	public static void main(String[] args) {
		DBconnection dBconnection=new DBconnection();
		SimulationProfitData simulationProfitData=new SimulationProfitData();
		SimulationProfit simulationProfit=new SimulationProfit();
		simulationProfit.setOperation("1");
		simulationProfit.setProfit(0);
//		simulationProfit.setState("2");
		simulationProfit.setStockId("3");
		simulationProfit.setUserId("4");
		simulationProfit.setDate(new Date());
		simulationProfitData.persist(simulationProfit);
	}
}
