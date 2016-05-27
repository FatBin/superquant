package VO;

import java.util.ArrayList;

import PO.industriesPO;

public class BusinessListVO {

	private ArrayList<industriesPO> businessList;

	public BusinessListVO() {
		super();
	}

	public ArrayList<industriesPO> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(ArrayList<industriesPO> businessList) {
		this.businessList = businessList;
	}			
	
}
