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
//		BusinessInfo businessInfo=new BusinessImpl();
//		businessInfo.getBusiness("化学制药");
		
		IndustryDataService industryDataService=new IndustryData();
		
		List<Industries> historyList;
		try {
			historyList = industryDataService.getIndustryDuringTime("化学制药", "2016-05-15", "2016-06-15");
		
			for (Industries industries : historyList) {
				System.out.println(industries.getId().getDate());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
