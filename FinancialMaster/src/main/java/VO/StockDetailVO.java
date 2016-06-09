package VO;

import PO.UpToDateStockPO;

public class StockDetailVO {
    //  最新信息，具体内容见PO
	private UpToDateStockPO upToDateMessage;
	
	//基本分析数据
	private  Analyze_BasicItemsVO analyze_BasicItemsVO;
	
	//技术分析数据
	private Analyze_TechnicalVO analyze_TechnicalVO;

   //	日期、开盘价、收盘价、最高价、最低价、后复权价、成交量、换手率、市盈率、市净率
	private String[][] historyData;
	
	//个股涨跌幅历史数据
	private double[] rise_fallList;
	
    //大盘涨跌幅历史数据
    private double[] bench_rise_fallList;
		
	//所属行业信息VO
	private BusinessVO businessVO;
	
	//高级文本分析结果
	private Analyze_ResultVO analyze_ResultVO;
	
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
	public double[] getRise_fallList() {
		return rise_fallList;
	}
	public void setRise_fallList(double[] rise_fallList) {
		this.rise_fallList = rise_fallList;
	}
	public BusinessVO getBusinessVO() {
		return businessVO;
	}
	public void setBusinessVO(BusinessVO businessVO) {
		this.businessVO = businessVO;
	}
	public Analyze_ResultVO getAnalyze_ResultVO() {
		return analyze_ResultVO;
	}
	public void setAnalyze_ResultVO(Analyze_ResultVO analyze_ResultVO) {
		this.analyze_ResultVO = analyze_ResultVO;
	}
	public double[] getBench_rise_fallList() {
		return bench_rise_fallList;
	}
	public void setBench_rise_fallList(double[] bench_rise_fallList) {
		this.bench_rise_fallList = bench_rise_fallList;
	}
	public Analyze_TechnicalVO getAnalyze_TechnicalVO() {
		return analyze_TechnicalVO;
	}
	public void setAnalyze_TechnicalVO(Analyze_TechnicalVO analyze_TechnicalVO) {
		this.analyze_TechnicalVO = analyze_TechnicalVO;
	}
	
	
}
