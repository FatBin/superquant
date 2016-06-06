package VO;

import PO.UpToDateStockPO;

public class StockDetailVO {
    //  最新信息，具体内容见PO
	private UpToDateStockPO upToDateMessage;
	
	//基本项分析内容
	private  Analyze_BasicItemsVO analyze_BasicItemsVO;

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
	public Analyze_BasicItemsVO getAnalyze_BasicItemsVO() {
		return analyze_BasicItemsVO;
	}
	public void setAnalyze_BasicItemsVO(Analyze_BasicItemsVO analyze_BasicItemsVO) {
		this.analyze_BasicItemsVO = analyze_BasicItemsVO;
	}

	
	
}
