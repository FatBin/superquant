package data.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import DAO.DaoProxy.BenchdataDaoProxy;
import DAO.DaoProxyService.BenchDataDaoService;
import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;

public class BenchDataUpdate {
	public static final String[] benchId={"sh000001","sz399001","sh000300"};
	public static final String[] benchdataURL=
		{"http://vip.stock.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/",
		 "/type/S.phtml?year=",
		 "&jidu="};

	public ArrayList<Benchdata> getBenchData(Document document,String benchId) {
		ArrayList<Benchdata> arrayList=new ArrayList<>();
		Elements elements = document.select("table[id=FundHoldSharesTable]");
		try {
			if (elements.size() != 0) {
				Element element = elements.first().select("tbody").first();
				Elements tr = element.select("tr");
				tr.remove(0);
				for (int i = tr.size() - 1; i >= 0; i--) {
					String[] temp = tr.get(i).text().split(" ");
					BenchdataId benchdataId = new BenchdataId(benchId, temp[0]);
					Benchdata benchdata = new Benchdata(benchdataId, Double.parseDouble(temp[1]),
							Double.parseDouble(temp[3]), Double.parseDouble(temp[2]), Double.parseDouble(temp[4]),
							Double.parseDouble(temp[6]), Long.parseLong(temp[5]));
					arrayList.add(benchdata);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	
	
	
	public void benchUpdate(){
		Calendar calendar=Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int quarter=calendar.get(Calendar.MONTH)%4+1;
		for(int i=0;i<benchId.length;i++){
			String benchId_part=benchId[i].substring(2);
			String url=benchdataURL[0]+benchId_part+benchdataURL[1]+year+benchdataURL[2]+quarter;
			try {
				Document document=Jsoup.connect(url).get();
				ArrayList<Benchdata> arrayList=getBenchData(document, benchId[i]);
				BenchDataDaoService dao=new BenchdataDaoProxy();
				for (Benchdata benchdata : arrayList) {
					try {
//						dao.insert(benchdata);
						System.out.print(benchdata.getId().getBenchId());
						System.out.println(benchdata.getId().getDate());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
