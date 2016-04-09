package businesslogicservice.stockcheckblservice;

import java.util.ArrayList;

import VO.StockItemVO;

public interface StockItemRankBLService {
	//根据string参数（指想查看的单项数据）获得观察股票最近时间（一个月内）的排行（后期修改为"根据string参数获得所有股中的前十"）
	//包括open,high,low,close,adj_price,volume,turnover,pe,pb
	public ArrayList<StockItemVO> getRank(String item);
}
