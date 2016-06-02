package VO;

import java.util.ArrayList;

import DAO.pojo.Industries;
import PO.industriesPO;
import PO.industryPO;

public class BusinessVO {
	//该行业最新信息
	private industriesPO uptodate_message;
	
	//该行业的历史数据
	private ArrayList<Industries> historyData;
	
	//该行业包含的公司的最新的数据
	private ArrayList<BusinessItemVO> businessItemVOs;

	public BusinessVO() {
		super();
	}

	public industriesPO getUptodate_message() {
		return uptodate_message;
	}

	public void setUptodate_message(industriesPO uptodate_message) {
		this.uptodate_message = uptodate_message;
	}

	public ArrayList<Industries> getHistoryData() {
		return historyData;
	}

	public void setHistoryData(ArrayList<Industries> historyData) {
		this.historyData = historyData;
	}

	public ArrayList<BusinessItemVO> getBusinessItemVOs() {
		return businessItemVOs;
	}

	public void setBusinessItemVOs(ArrayList<BusinessItemVO> businessItemVOs) {
		this.businessItemVOs = businessItemVOs;
	}


	
	
	
}
