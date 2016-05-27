package VO;

import java.util.ArrayList;

import PO.industryPO;

public class BusinessListVO {

	private ArrayList<industryPO> businessList;

	public BusinessListVO() {
		super();
	}

	public ArrayList<industryPO> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(ArrayList<industryPO> businessList) {
		this.businessList = businessList;
	}			
	
}
