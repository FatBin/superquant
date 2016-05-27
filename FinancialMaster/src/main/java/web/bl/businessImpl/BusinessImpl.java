package web.bl.businessImpl;

import java.util.ArrayList;

import PO.industriesPO;
import VO.BusinessListVO;
import VO.BusinessVO;
import data.IndustryData.IndustryData;
import dataservice.IndustryDataService.IndustryDataService;
import web.blservice.businessInfo.BusinessInfo;

public class BusinessImpl implements BusinessInfo {

	@Override
	public BusinessListVO getBusinessList() {
		IndustryDataService industryDataService=new IndustryData();
		ArrayList<industriesPO> industryPOs=industryDataService.getIndustryData();
		BusinessListVO businessListVO=new BusinessListVO();
		businessListVO.setBusinessList(industryPOs);
		return businessListVO;
	}

	@Override
	public BusinessVO getBusiness(String businessname) {
		// TODO Auto-generated method stub
		return null;
	}

}
