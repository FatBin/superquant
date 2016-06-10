package web.bl.stockImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import DAO.pojo.Stock;
import DAO.pojo.TradeRecord;
import ENUM.ManageState;
import PO.UpToDateStockPO;
import VO.Analyze_BasicItemsVO;
import VO.Analyze_ResultVO;
import VO.Analyze_TechnicalVO;
import VO.BenchVO;
import VO.BusinessItemVO;
import VO.BusinessVO;
import VO.StockDetailVO;
import data.StockData.StockData;
import dataservice.StockDataService.StockDataService;
import servlet.factory.InitFactoryServlet;
import web.bl.benchImpl.BenchImpl;
import web.bl.businessImpl.BusinessImpl;
import web.blservice.benchInfo.BenchInfo;
import web.blservice.businessInfo.BusinessInfo;
import web.blservice.stockInfo.StockInfo;

public class StockImpl implements StockInfo {

	@Override
	public StockDetailVO getStock(String code) {
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String endtime=format.format(calendar.getTime());
		calendar.add(Calendar.YEAR, -1);
		String starttime=format.format(calendar.getTime());
		
		StockDataService stockDataService=new StockData();
		StockDetailVO stockDetailVO=new StockDetailVO();
		
		try {
			//最新数据获取
			UpToDateStockPO upToDateMessage=stockDataService.getUpToDateStockPO(code);
			Stock stock=InitFactoryServlet.getStock(code);
			upToDateMessage.setIndustry(stock.getIndustry());
			
			List<TradeRecord> records=stockDataService.getStockRecord(code, starttime, endtime);			
			int size=records.size();
			
			String[][] historyData=new String[size][10];
			double[] closes=new double[size];
			double[] volume=new double[size];
			double[] rise_fallList=new double[size-1];
			double[] turnovers=new double[size];
			
			//历史数据模块
                  //日期、开盘价、收盘价、最高价、最低价、后复权价、成交量、换手率、市盈率、市净率
			int index=0;
			for (TradeRecord tradeRecord : records) {
				historyData[index][0]=tradeRecord.getId().getDate();				
				historyData[index][1]=tradeRecord.getOpen()+"";
				historyData[index][2]=tradeRecord.getClose()+"";
				closes[index]=tradeRecord.getClose();
				historyData[index][3]=tradeRecord.getHigh()+"";
				historyData[index][4]=tradeRecord.getLow()+"";
				if(tradeRecord.getAdjPrice()==0){
					historyData[index][5]="-";
				}else{
				historyData[index][5]=tradeRecord.getAdjPrice()+"";
				}
				historyData[index][6]=tradeRecord.getVolume()+"";
				volume[index]=tradeRecord.getVolume();
				if(tradeRecord.getAdjPrice()==0){
					historyData[index][7]="-";
				}else{
					historyData[index][7]=tradeRecord.getTurnover()+"";
				}
				turnovers[index]=tradeRecord.getTurnover();
				if(tradeRecord.getAdjPrice()==0){
					historyData[index][8]="-";
				}else{
					historyData[index][8]=tradeRecord.getPe()+"";
				}
				if(tradeRecord.getAdjPrice()==0){
					historyData[index][9]="-";;
				}else{
					historyData[index][9]=tradeRecord.getPb()+"";
				}
				
                index++;
			}
			
			for(int i=0;i<size-1;i++){
				rise_fallList[i]=(closes[i]-closes[i+1])/closes[i+1];
			}
			
			//计算基本项
			int m=size,n=size;
			if(size>10){
				n=10;
			}
			if(size>30){
				m=30;
			}

			//基本分析数据获取与计算
			double priceStability=1-getAmendatoryStandardDevition(turnovers,m);
			double turnOver=getAvg(turnovers,n);
			double ups_and_downs=0;
			double quantity_relative_ratio=0;
			if(upToDateMessage.getRise_fall()!=null){
				String rise_fall=(upToDateMessage.getRise_fall()).split("%")[0];
				ups_and_downs=Double.parseDouble(rise_fall);
				quantity_relative_ratio=upToDateMessage.getQuantity_relative_ratio();
			}

			double pe=0;
			double pb=0;
			if(size>0){
				TradeRecord lastRecord=records.get(0);
				pe=lastRecord.getPe();
				pb=lastRecord.getPb();
			}
			
			Analyze_BasicItemsVO analyze_BasicItemsVO=new Analyze_BasicItemsVO();
			analyze_BasicItemsVO.setQuantity_relative_ratio(quantity_relative_ratio);			
			analyze_BasicItemsVO.setPriceStability(priceStability);
			analyze_BasicItemsVO.setUps_and_downs(ups_and_downs);
			analyze_BasicItemsVO.setTurnOver(turnOver);
			analyze_BasicItemsVO.setPe(pe);
			analyze_BasicItemsVO.setPb(pb);
			
			Analyze_TechnicalVO analyze_TechnicalVO=new Analyze_TechnicalVO();
			analyze_TechnicalVO.setClose(closes);
			analyze_TechnicalVO.setVolume(volume);;
			
			//声明空的分析结果的VO
			Analyze_ResultVO analyze_ResultVO=new Analyze_ResultVO();

			//设置股票最新信息
			stockDetailVO.setUpToDateMessage(upToDateMessage);
			//设置历史数据
			stockDetailVO.setHistoryData(historyData);			
			//设置基本分析初步
			stockDetailVO.setAnalyze_BasicItemsVO(analyze_BasicItemsVO);
			//设置历史涨跌率列表
			stockDetailVO.setRise_fallList(rise_fallList);					
			//设置空的综合分析结果
			stockDetailVO.setAnalyze_ResultVO(analyze_ResultVO);
			//设置技术分析数据
		   stockDetailVO.setAnalyze_TechnicalVO(analyze_TechnicalVO);
			
			//分析该支股票
			analyze(stockDetailVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockDetailVO;
	}

	@Override
	public ManageState updateHistoryData(StockDetailVO sv, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageState filterHistoryData(StockDetailVO sv, int item, double lowerLimit, double upperLimit) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 得到数组修正后的标准差
	private double getAmendatoryStandardDevition(double[] data, int n) {
		double result = 0;
		double avg = getAvg(data, n);

		for (int i = 0; i < n; i++) {
			result += (data[i] - avg) * (data[i] - avg);
		}
		result /= n - 1;
		result = Math.sqrt(result);
		if(avg==0){
			result=1;
		}else{
		   result /= avg;
		}
		return result;
	}

	//得到平均值
	private double getAvg(double[] data, int n) {
		double avg = 0;

		for (int i = 0; i < n; i++) {
			avg += data[i];
		}
		avg /= n;
		
		return avg;
		
	}
	
	private void analyze(StockDetailVO stockDetailVO) {
		StockAnalyze stockAnalyze=new StockAnalyze();
		stockAnalyze.comprehensiveAnalyze(stockDetailVO);
	}

}
