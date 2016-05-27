package VO;

public class BenchVO {
	     String[][] data;//历史数据
	     //历史数据顺序依次是：日期，开盘价，最高价，最低价，收盘价，成交量（百万股），成交额（亿元）
	     String volume;//成交量
	     String money;//成交额
	     double changeRange;//涨跌量
	     double ups_and_downs;//涨跌幅
	     double close;//昨收
	     double open;//今开
	     double high;//最高价
	     double low; //最低价
	     double lastest_price;//最新价
	     String time;//数据时间
	     boolean isStop;//是否停盘
		public BenchVO() {
			super();
		}
		public String[][] getData() {
			return data;
		}
		public void setData(String[][] data) {
			this.data = data;
		}
		public String getVolume() {
			return volume;
		}
		public void setVolume(String volume) {
			this.volume = volume;
		}
		public String getMoney() {
			return money;
		}
		public void setMoney(String money) {
			this.money = money;
		}
		public double getChangeRange() {
			return changeRange;
		}
		public void setChangeRange(double changeRange) {
			this.changeRange = changeRange;
		}
		public double getUps_and_downs() {
			return ups_and_downs;
		}
		public void setUps_and_downs(double ups_and_downs) {
			this.ups_and_downs = ups_and_downs;
		}
		public double getClose() {
			return close;
		}
		public void setClose(double close) {
			this.close = close;
		}
		public double getOpen() {
			return open;
		}
		public void setOpen(double open) {
			this.open = open;
		}
		public double getHigh() {
			return high;
		}
		public void setHigh(double high) {
			this.high = high;
		}
		public double getLow() {
			return low;
		}
		public void setLow(double low) {
			this.low = low;
		}
		public double getLastest_price() {
			return lastest_price;
		}
		public void setLastest_price(double lastest_price) {
			this.lastest_price = lastest_price;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public boolean isStop() {
			return isStop;
		}
		public void setStop(boolean isStop) {
			this.isStop = isStop;
		}
	     
	     
	     
	     
}
