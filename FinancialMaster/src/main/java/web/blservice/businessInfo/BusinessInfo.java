package web.blservice.businessInfo;

import VO.BusinessListVO;
import VO.BusinessVO;

public interface BusinessInfo {

	//返回所有行业的最新信息
	public BusinessListVO getBusinessList();
	
	//通过行业名，返回对应行业的信息及历史数据
	public BusinessVO getBusiness(String businessname);
}
