package data.StockData.RecommendedData;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.test.finaltest;
import PO.recommendedStock.PeakPO;
import PO.recommendedStock.breakthroughPO;

public class PeakDown implements Runnable{
	public final String[] url={"http://data.10jqka.com.cn/rank/cxd/board/4/field/stockcode/order/asc/page/","/ajax/1/"};
	public final int page=10;
	public static ArrayList<PeakPO> arrayList;
	
	public PeakDown() {
		arrayList=new ArrayList<>();
		Thread thread=new Thread(this);
		thread.start();
	}
	
	public ArrayList<PeakPO> getDatas(){
		ArrayList<PeakPO> result=new ArrayList<>();
			for (int i = 0; i < page; i++) {
				try {
					Document document = Jsoup.connect(url[0] + i+ url[1]).get();
					Elements elements = document.select("tbody").select("tr");
					for (Element element : elements) {
						String[] temp = element.text().split(" ");
						PeakPO po = new PeakPO();
						if (temp[1].charAt(0) == '6') {
							temp[1] = "sh" + temp[1];
						} else {
							temp[1] = "sz" + temp[1];
						}
						po.setStockId(temp[1]);
						po.setStockName(temp[2]);
						po.setRiseOrDown(temp[3]);
						po.setExchange(temp[4]);
						po.setUptodate(temp[5]);
						po.setHigh(temp[6]);
						po.setDate(temp[7]);
	
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
