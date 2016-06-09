package PO;

public class profitPO {
	private String date;
	private double profit;

	public profitPO(String date, double profit) {
		super();
		this.date = date;
		this.profit = profit;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

}
