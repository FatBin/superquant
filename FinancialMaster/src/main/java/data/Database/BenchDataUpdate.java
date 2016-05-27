package data.Database;

import java.io.IOException;

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
}
