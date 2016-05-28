package data.Database;

import java.io.IOException;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DAOfactory.DaoFactory;
import DAO.DaoProxy.BenchdataDaoProxy;
import DAO.connection.DBconnection;
import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;

public class BenchDataUpdate {
	public static final String[] benchId={"sh000001","sh000300","sz399001"};
	public static final String[] benchdataURL=
		{"http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/",
		 "/type/S.phtml?year=",
		 "&jidu="};

	public void updateBench(Document document,String benchId) {
		Elements elements = document.select("table[id=FundHoldSharesTable]");
		if (elements.size() != 0) {
			Element element = elements.first().select("tbody").first();
			Elements tr = element.select("tr");
			tr.remove(0);
			BenchdataDaoProxy benchdataDao = DaoFactory.getBenchdataDaoProxy();
			for (int i = tr.size() - 1; i >= 0; i--) {
				String[] temp = tr.get(i).text().split(" ");
				BenchdataId benchdataId = new BenchdataId(benchId, temp[0]);
				Benchdata benchdata = new Benchdata(benchdataId, Double.parseDouble(temp[1]),
						Double.parseDouble(temp[3]), Double.parseDouble(temp[2]), Double.parseDouble(temp[4]),
						Double.parseDouble(temp[6]), Long.parseLong(temp[5]));
				try {
					benchdataDao.insert(benchdata);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public Document getDocument(String url){
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	public void benchUpdate(){
		Calendar calendar=Calendar.getInstance();
		int hour=calendar.get(Calendar.HOUR);
		int minute=calendar.get(Calendar.MINUTE);
		int year=calendar.get(Calendar.YEAR);
		int quarter=calendar.get(Calendar.MONTH)%4+1;
		for(int i=0;i<benchId.length;i++){
			String benchId_part=benchId[i].substring(2);
			String url=benchdataURL[0]+benchId_part+benchdataURL[1]+year+benchdataURL[2]+quarter;
			Document document=getDocument(url);
			BenchDataUpdate benchDataUpdate=new BenchDataUpdate();
			benchDataUpdate.updateBench(document, benchId[i]);
		}
	}
}
