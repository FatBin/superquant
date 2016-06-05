package data.StockData;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import PO.RiseStockPO;

public class RiseStockUpdate implements Runnable{
	public static final String[] riseStock={
			"http://data.10jqka.com.cn/rank/lxsz/field/lxts/order/desc/page/",
			"/ajax/1/"
			};
	public static final int[] page={1,2,3,4,5,6,7,8,9,10};
	
	public static ArrayList<RiseStockPO> riseStockPOs;
	
	public RiseStockUpdate(){
		riseStockPOs=new ArrayList<>();
		Thread thread=new Thread(this);
		thread.start();
	}
	
	public ArrayList<RiseStockPO> getRiseStock(){
		ArrayList<RiseStockPO> arrayList=new ArrayList<>();
		try {
			for (int i = 0; i < page.length; i++) {
				Document document=Jsoup.connect(riseStock[0]+page[i]+riseStock[1]).get();
				Elements elements=document.select("table[class=m-table J-ajax-table]");
				Elements result=elements.get(0).select("tbody").get(0).select("tr");
				for (int j = 0; j < result.size(); j++) {
					String[] temp=result.get(j).text().split(" ");
					if(temp[1].charAt(0)=='6'){
						temp[1]="sh"+temp[1];
					}else {
						temp[1]="sz"+temp[1];
					}
					RiseStockPO po=new RiseStockPO(
							temp[1],
							temp[2], 
							Double.parseDouble(temp[3]), 
							Double.parseDouble(temp[4]), 
							Double.parseDouble(temp[5]), 
							Integer.parseInt(temp[6]), 
							temp[7], 
							temp[8],
							temp[9]
							);
					arrayList.add(po);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	public static ArrayList<RiseStockPO> getRiseStockPOs() {
		return riseStockPOs;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10000);
				riseStockPOs = getRiseStock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}

}
