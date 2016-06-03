package VO;

import org.eclipse.jdt.internal.compiler.ast.DoubleLiteral;

import PO.UpToDateStockPO;

public class StockDetailVO {
    //  最新信息，具体内容见PO
	private UpToDateStockPO upToDateMessage;
	private double volumeStability;
	private double priceStability;
	private double turnOver;			
	private double ups_and_downs;
	private double pe;
	private double pb;
   //	日期、开盘价、收盘价、最高价、最低价、后复权价、成交量、换手率、市盈率、市净率
	private String[][] historyData;
	
	public StockDetailVO() {
		super();
	}
	public UpToDateStockPO getUpToDateMessage() {
		return upToDateMessage;
	}
	public void setUpToDateMessage(UpToDateStockPO upToDateMessage) {
		this.upToDateMessage = upToDateMessage;
	}
	public String[][] getHistoryData() {
		return historyData;
	}
	public void setHistoryData(String[][] historyData) {
		this.historyData = historyData;
	}
	public double getVolumeStability() {
		return volumeStability;
	}
	public void setVolumeStability(double volumeStability) {
		this.volumeStability = volumeStability;
	}
	public double getPriceStability() {
		return priceStability;
	}
	public void setPriceStability(double priceStability) {
		this.priceStability = priceStability;
	}
	public double getTurnOver() {
		return turnOver;
	}
	public void setTurnOver(double turnOver) {
		this.turnOver = turnOver;
	}
	public double getUps_and_downs() {
		return ups_and_downs;
	}
	public void setUps_and_downs(double ups_and_downs) {
		this.ups_and_downs = ups_and_downs;
	}
	public double getPe() {
		return pe;
	}
	public void setPe(double pe) {
		this.pe = pe;
	}
	public double getPb() {
		return pb;
	}
	public void setPb(double pb) {
		this.pb = pb;
	}
	
	
}
