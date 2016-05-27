package web.bl.benchImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.pojo.Bench;
import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;
import VO.BenchListVO;
import VO.BenchVO;
import data.BenchData.BenchData;
import data.BenchData.BenchRecord;
import dataservice.BenchDataService.BenchDataService;
import dataservice.BenchDataService.BenchRecordService;
import web.blservice.benchInfo.BenchInfo;

public class BenchImpl implements BenchInfo{

	ArrayList<Bench> marketList=new ArrayList<Bench>();
	@Override
 	public BenchListVO getBenchCode() {
		BenchDataService bds=new BenchData();
		BenchListVO vo=new BenchListVO();
		try {
			List<Bench>  benchList=bds.getBench();
			int length=benchList.size();
			String[] nameList=new String[length];
			int index=0;
			for (Bench bench : benchList) {
				marketList.add(bench);
				nameList[index]=bench.getBenchName();
				index++;
			}
			vo.setBenchList(nameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public BenchVO getStockMarket(String benchCode) {
		String id="";
		for (Bench bench : marketList) {
			if(bench.getBenchName()==benchCode){
				id=bench.getBenchId();
				break;
			}
		}
		BenchRecordService br=new BenchRecord();
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DATE, 1);
		String endDay = format.format(calendar.getTime());
		BenchVO benchVO=new BenchVO();
		try {
			List<Benchdata> historyData=br.getBenchData(id, "2006-01-01", endDay);
			int size=historyData.size();
			String data[][]=new String[size][7];
			int index=0;
			for (Benchdata benchPO : historyData) {
				BenchdataId benchdataId=benchPO.getId();
				data[index][0]=benchdataId.getDate();
				data[index][1]=benchPO.getOpen()+"";
				data[index][2]=benchPO.getHigh()+"";
				data[index][3]=benchPO.getLow()+"";
				data[index][4]=benchPO.getClose()+"";
				data[index][5]=benchPO.getAdjPrice()/1000000+"";
				data[index][6]=benchPO.getVolume()/100000000+"";
				index++;
			}
			benchVO.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return benchVO;
	}

}
