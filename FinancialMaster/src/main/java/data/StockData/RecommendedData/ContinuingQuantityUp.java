package data.StockData.RecommendedData;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import PO.recommendedStock.ContinuingQuantityPO;
import PO.recommendedStock.breakthroughPO;

public class ContinuingQuantityUp implements Runnable{
	public final String[] url={"http://data.10jqka.com.cn/rank/cxfl/field/count/order/desc/page/","/ajax/1/"};
	public final int page=10;
	public static ArrayList<ContinuingQuantityPO> arrayList;
	
	public ContinuingQuantityUp() {
		arrayList=new ArrayList<>();
		Thread thread=new Thread(this);
		thread.start();
	}
	
	public ArrayList<ContinuingQuantityPO> getDatas(){
		ArrayList<ContinuingQuantityPO> result=new ArrayList<>();
			for (int i = 0; i <page; i++) {
				try {
					Document document = Jsoup.connect(url[0] + i + url[1]).get();
					Elements elements = document.select("tbody").select("tr");
					for (Element element : elements) {
						String[] temp = element.text().split(" ");
						ContinuingQuantityPO po = new ContinuingQuantityPO();
						if (temp[1].charAt(0) == '6') {
							temp[1] = "sh" + temp[1];
						} else {
							temp[1] = "sz" + temp[1];
						}
						if(temp.length==9){
							po.setStockId(temp[1]);
							po.setStockName(temp[2]);
							po.setRiseOrDown(temp[3]);
							po.setUptodate(temp[4]);
							po.setVolumn(temp[5]);
							po.setBaseDateVolumn(temp[6]);
							po.setDays(temp[7]);
							po.setStageRiseOrDown(temp[8]);
							po.setIndustry("");
						}else {
							po.setStockId(temp[1]);
							po.setStockName(temp[2]);
							po.setRiseOrDown(temp[3]);
							po.setUptodate(temp[4]);
							po.setVolumn(temp[5]);
							po.setBaseDateVolumn(temp[6]);
							po.setDays(temp[7]);
							po.setStageRiseOrDown(temp[8]);
							po.setIndustry(temp[9]);
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
