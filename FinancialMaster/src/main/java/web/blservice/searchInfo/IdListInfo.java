package web.blservice.searchInfo;

import java.util.ArrayList;

import DAO.pojo.Stock;

public interface IdListInfo {

	//返回所有股票的代码和名称
	public ArrayList<Stock> getIdList();
}
