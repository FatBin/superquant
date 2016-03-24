package dataservice.fieldDataService;

import PO.fieldStatisticPO;

public interface FieldDataService {

	//查看股票的可用交易数据字段，例如开盘价，收盘价等。
	public fieldStatisticPO getStatisticOfField();
}
