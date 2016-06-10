package VO;

public class MyStrategyVO {

	  String name;//策略名
      String totalcost;//总成本
	  String perST;;//股票名称、投资成本、开始日期、结束日期、买卖频率
	  String BuyList;// 价格区间、成交量区间、换手率区间、pe区间、pb区间
	  String SoldList;
	public MyStrategyVO() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotalcost() {
		return totalcost;
	}
	public void setTotalcost(String totalcost) {
		this.totalcost = totalcost;
	}
	public String getPerST() {
		return perST;
	}
	public void setPerST(String perST) {
		this.perST = perST;
	}
	public String getBuyList() {
		return BuyList;
	}
	public void setBuyList(String buyList) {
		BuyList = buyList;
	}
	public String getSoldList() {
		return SoldList;
	}
	public void setSoldList(String soldList) {
		SoldList = soldList;
	}
	
	
	  
	  
	  
}
