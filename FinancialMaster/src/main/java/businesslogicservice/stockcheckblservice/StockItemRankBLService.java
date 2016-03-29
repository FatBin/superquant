package businesslogicservice.stockcheckblservice;

import java.util.ArrayList;

import VO.StockItemVO;

public interface StockItemRankBLService {
	//根据string参数（指想查看的单项数据）获得观察股票的排行（后期修改为"根据string参数获得所有股中的前十"）
	public ArrayList<StockItemVO> getRank();
}
