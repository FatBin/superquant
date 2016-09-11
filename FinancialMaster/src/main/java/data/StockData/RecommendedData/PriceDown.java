package data.StockData.RecommendedData;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.test.finaltest;
import PO.recommendedStock.PeakPO;
import PO.recommendedStock.PricePO;
import PO.recommendedStock.breakthroughPO;

public class PriceDown implements Runnable{
	public final String[] url={"http://data.10jqka.com.cn/rank/ljqd/field/count/order/desc/page/","/ajax/1/"};
	public final int page=30;
	public static ArrayList<PricePO> arrayList;
	
	public PriceDown() {
		arrayList=new ArrayList<>();
		Thread thread=new Thread(this);
		thread.start();
	}
	
	public ArrayList<PricePO> getDatas(){
		ArrayList<PricePO> result=new ArrayList<>();
			for (int i = 0; i < page; i++) {
				try {
					Document document = Jsoup.connect(url[0] + i+ url[1]).get();
					Elements elements = document.select("tbody").select("tr");
					for (Element element : elements) {
						String[] temp = element.text().split(" ");
						PricePO po = new PricePO();
						if (temp[1].charAt(0) == '6') {
							temp[1] = "sh" + temp[1];
						} else {
							temp[1] = "sz" + temp[1];
						}
						if (temp.length==8) {
							po.setStockId(temp[1]);
							po.setStockName(temp[2]);
							po.setUptodate(temp[3]);
							po.setDays(temp[4]);
							po.setStageRiseOrDown(temp[5]);
							po.setExchange(temp[6]);
							po.setIndustry(temp[7]);
						}else {
							po.setStockId(temp[1]);
							po.setStockName(temp[2]);
							po.setUptodate(temp[3]);
							po.setDays(temp[4]);
							po.setStageRiseOrDown(temp[5]);
							po.setExchange(temp[6]);
							po.setIndustry("");
						}
	
						result.add(po);
					} 
				} catch (Exception e) {
				}
			}
		return result;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(10000);
				arrayList=getDatas();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}
