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
		BusinessListVO businessListVO=new BusinessListVO();
		ArrayList<industriesPO> industryPOs;
		try {
			industryPOs = industryDataService.getIndustryData();
			businessListVO.setBusinessList(industryPOs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return businessListVO;
	}

	@Override
	public BusinessVO getBusiness(String businessname) {
		// TODO Auto-generated method stub
		return null;
	}

}
