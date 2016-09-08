package web.bl.benchImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import DAO.pojo.Bench;
import DAO.pojo.Benchdata;
import DAO.pojo.BenchdataId;
import ENUM.ManageState;
import PO.benchCurrentDataPO;
import VO.BenchListVO;
import VO.BenchVO;
import data.BenchData.BenchData;
import data.BenchData.BenchRecord;
import dataservice.BenchDataService.BenchDataService;
import dataservice.BenchDataService.BenchRecordService;
import web.blservice.benchInfo.BenchInfo;
import web.blservice.benchInfo.BenchUpdateInfo;

public class BenchImpl implements BenchInfo,BenchUpdateInfo{
	BenchDataService bds=new BenchData();
	@Override
 	public BenchListVO getBenchCode() {
		BenchListVO vo=new BenchListVO();
		try {
			List<Bench>  benchList=bds.getBench();
			int length=benchList.size();
			String[] nameList=new String[length];
			int index=0;
			for (Bench bench : benchList) {
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
		List<Bench> benchList;
		try {
			benchList = bds.getBench();
			for (Bench bench : benchList) {
				if(bench.getBenchName().equals(benchCode)){
					id=bench.getBenchId();
					break;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		BenchRecordService br=new BenchRecord();
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DATE, 1);
		String endDay = format.format(calendar.getTime());
		BenchVO benchVO=new BenchVO();
		try {
			List<Benchdata> historyData=br.getBenchData(id, "2013-01-01", endDay);
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
				data[index][5]=benchPO.getVolume()/1000000+"";
				data[index][6]=benchPO.getAdjPrice()/100000000+"";
				index++;
			}
			
			benchVO.setData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		update(benchVO,benchCode);
		return benchVO;
	}

	@Override
	public ManageState update(BenchVO benchVO,String benchName) {
		String id="";
		List<Bench> benchList;
		try {
			benchList = bds.getBench();
			for (Bench bench : benchList) {
				if(bench.getBenchName().equals(benchName)){
					id=bench.getBenchId();
					break;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		BenchRecordService benchRecordService=new BenchRecord();
		try {
			benchCurrentDataPO currentBenchPO=benchRecordService.getLastestRecord(id);
			benchVO.setStatus(currentBenchPO.getStatus());
			benchVO.setTime(currentBenchPO.getTime());
			benchVO.setCompany(currentBenchPO.getCompany());
			benchVO.setFall_company(currentBenchPO.getFall_company());
			benchVO.setHigh(currentBenchPO.getHigh());
			benchVO.setLow(currentBenchPO.getLow());
			benchVO.setNow(currentBenchPO.getNow());
			benchVO.setOpen(currentBenchPO.getOpen());
			benchVO.setPrice(currentBenchPO.getPrice());
			benchVO.setRise_company(currentBenchPO.getRise_company());
			benchVO.setRise_fall_percent(currentBenchPO.getRise_fall_percent());
			benchVO.setRise_fall_price(currentBenchPO.getRise_fall_price());
			benchVO.setVolume(currentBenchPO.getVolume());
			benchVO.setYesterday_close(currentBenchPO.getYesterday_close());
			return ManageState.Succeed;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ManageState.Fail;
	}

	@Override
	public String[][] getTimeSharingData(String benchCode) {
		String id="";
		List<Bench> benchList;
		try {
			benchList = bds.getBench();
			for (Bench bench : benchList) {
				if(bench.getBenchName().equals(benchCode)){
					id=bench.getBenchId();
					break;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		
		return null;
	}

	@Override
	public String getState() {
		BenchRecordService benchRecordService=new BenchRecord();
		try {
			benchCurrentDataPO currentBenchPO=benchRecordService.getLastestRecord("sh000300");
			return currentBenchPO.getStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "数据获取失败";
	}

}
