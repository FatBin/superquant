package data.FieldData;

import java.util.ArrayList;

import data.stockcheckdata.StockData;

public class test {

	public static void main(String[] args) {

		
		StockData f = new StockData();
		String[] name_list = { "sh600000", "sh600004", "sh600005", "sh600006",
				"sh600007", "sh600008", "sh600009", "sh600300", "sh600316",
				"sh600769", "sh600975", "sh600622", "sh600803", "sh600365",
				"sh600223", "sh600071", "sh603008", "sh600085", "sh600827",
				"sh600077" };
		for (String string : name_list) {
			System.out.println(string);
			f.getStatisitcOfStock(string, "2016-03-07", "2016-03-08");
		}
		
	}

}
