package VO;

import java.util.ArrayList;

import PO.UpToDateStockPO;

public class StockDetailVO {

	private UpToDateStockPO upToDateMessage;
	private ArrayList<StockRecordVO> historyData;
	
	public StockDetailVO() {
		super();
	}
	public UpToDateStockPO getUpToDateMessage() {
		return upToDateMessage;
	}
	public void setUpToDateMessage(UpToDateStockPO upToDateMessage) {
		this.upToDateMessage = upToDateMessage;
	}
	public ArrayList<StockRecordVO> getHistoryData() {
		return historyData;
	}
	public void setHistoryData(ArrayList<StockRecordVO> historyData) {
		this.historyData = historyData;
	}
	
	
}
