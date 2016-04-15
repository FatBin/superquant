package data.manageStockData;

import java.util.ArrayList;

import ENUM.ManageState;
import data.IO.FileManager;
import dataservice.manageStockService.manageStockDataService;

public class ManageStockData implements manageStockDataService{
	//返回以观察的所有股票的代号
	@Override
	public ArrayList<String> getCodeOfStock(){
		// TODO Auto-generated method stub
		try {
			ArrayList<String>  arrayList = FileManager.ReadFile("src/main/resources/Data/ObservedStock.txt");
			return arrayList;
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<String>();
		}

	}

	//增加要观察的股票
	@Override
	public ManageState addStock(String code) {
		// TODO Auto-generated method stub
		try {
			ArrayList<String> arrayList=FileManager.ReadFile("src/main/resources/Data/ObservedStock.txt");
//			//大于等于10时无效
//			if(arrayList.size()>9){
//				return ManageState.Fail;
//			}else{
				//有重复时无效
				for (String string : arrayList) {
					if (string.equals(code)) {
						return ManageState.Fail;
					}
				}
				
				arrayList.add(code);
				FileManager.WriteFile(arrayList, "src/main/resources/Data/ObservedStock.txt", false);
				return ManageState.Succeed;
//			}
		} catch (Exception e) {
			return ManageState.Succeed;
		}
	}
	
	//删除已观察的股票
	@Override
	public ManageState deleteStock(String code) {
		// TODO Auto-generated method stub
		try {
			//若有相同的，删除，其余均失败
			ArrayList<String> arrayList=FileManager.ReadFile("src/main/resources/Data/ObservedStock.txt");
			for (int i=0;i<arrayList.size();i++) {
				if(arrayList.get(i).equals(code)){
					arrayList.remove(i);
					FileManager.WriteFile(arrayList, "src/main/resources/Data/ObservedStock.txt", false);
					return ManageState.Succeed;
				}
			}
			return ManageState.Fail;
		} catch (Exception e) {
			// TODO: handle exception
			return ManageState.Fail;
		}
	}
	
}
