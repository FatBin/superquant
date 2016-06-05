package data.StockData;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import PO.UpToDateStockPO;

public class UpToDateStocksUpdate implements Runnable{
	public static final String[] UpToDateStocks={"http://www.shdjt.com/",".htm"};
	
	public static final String[] exchanges={"sh","sz"};
	
	public static ArrayList<UpToDateStockPO>[] arrayLists;
	
	public UpToDateStocksUpdate(){
		arrayLists=new ArrayList[2];
		for (int i = 0; i < exchanges.length; i++) {
			arrayLists[i]=new ArrayList<UpToDateStockPO>();
		}
		Thread thread=new Thread(this);
		thread.start();
	}
	
	public ArrayList<UpToDateStockPO> getToDateStockPOs(String exchange){
		try {
			ArrayList<UpToDateStockPO> arrayList=new ArrayList<>();
			Document document=Jsoup.connect(UpToDateStocks[0]+exchange+UpToDateStocks[1]).get();
			Elements elements=document.select("tr[height=25]");
			for (Element element : elements) {
				String[] temp=element.text().replace(",", "").split(" ");
				if(temp[1].charAt(0)=='6'){
					temp[1]="sh"+temp[1];
				}else {
					temp[1]="sz"+temp[1];
				}
				UpToDateStockPO po=new UpToDateStockPO(
						temp[1], 
						temp[2], 
						temp[4], 
						Double.parseDouble(temp[5]), 
						temp[6], 
						Double.parseDouble(temp[8]), 
						Double.parseDouble(temp[9]), 
						Double.parseDouble(temp[10]), 
						Double.parseDouble(temp[11]), 
						Double.parseDouble(temp[12]),
						Double.parseDouble(temp[15]),
						Double.parseDouble(temp[16]),
						Double.parseDouble(temp[17]),
						Double.parseDouble(temp[18]),
						Double.parseDouble(temp[21]), 
						Double.parseDouble(temp[22]), 
						Double.parseDouble(temp[23]), 
						Double.parseDouble(temp[24]), 
						Double.parseDouble(temp[25]), 
						Double.parseDouble(temp[26]), 
						Double.parseDouble(temp[27]), 
						Double.parseDouble(temp[28]), 
						Double.parseDouble(temp[29]), 
						Double.parseDouble(temp[30]));
				arrayList.add(po);
			}
			return arrayList;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public static ArrayList<UpToDateStockPO> getArrayLists(String exchange) {
		for (int i = 0; i < exchange.length(); i++) {
			if (exchanges[i].equals(exchange)) {
				return arrayLists[i];
			}
		}
		return new ArrayList<>();
	}
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(10000);
				for(int i=0;i<exchanges.length;i++){
					try {
						arrayLists[i]=getToDateStockPOs(exchanges[i]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
