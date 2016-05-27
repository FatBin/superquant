package web.blservice.searchInfo;

import VO.IdListVO;

public interface IdListInfo {

	//根据关键字得到包含关键字的所有股票的代码和名称
	public IdListVO getIdList(String key);
}
