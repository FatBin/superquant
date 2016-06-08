package web.bl.businessImpl;

import java.util.ArrayList;
import java.util.List;

import DAO.pojo.Industries;
import DAO.pojo.Stock;
import PO.industriesPO;
import PO.industryPO;
import VO.BusinessItemVO;
import data.IndustryData.IndustryData;
import data.Initialize.Init;
import dataservice.IndustryDataService.IndustryDataService;
import servlet.factory.InitFactoryServlet;
import web.blservice.businessInfo.BusinessInfo;

public class test1 {

	public static void main(String[] args) {
		Init init=new Init();
		BusinessInfo businessInfo=new BusinessImpl();
		businessInfo.getBusiness("信息技术");
	}
}
