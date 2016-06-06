package DAO.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hibernate.persister.walking.spi.MetamodelGraphWalker;

import data.IndustryData.IndustryData;
import data.Initialize.Init;
import presentation.mainui.mainframe;

public class nullpointtest {
	public static void main(String[] args) {
		nullpointtest mNullpointtest=new nullpointtest();
		mNullpointtest.test();
		while(true){
			BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			try {
				String string=reader.readLine();
				if (string.equals("1")) {
					System.out.println("yes");
					IndustryData industryData=new IndustryData();
					try {
						System.out.println(industryData.getIndustryData().size());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void test(){
		Init init=new Init();
	}
}
