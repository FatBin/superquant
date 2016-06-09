package web.bl.stockImpl;

import java.util.ArrayList;

import org.hibernate.id.SelectGenerator.SelectGeneratorDelegate;

import PO.UpToDateStockPO;
import PO.industriesPO;
import VO.Analyze_BasicItemsVO;
import VO.Analyze_ResultVO;
import VO.Analyze_TechnicalVO;
import VO.BenchVO;
import VO.BusinessItemVO;
import VO.BusinessVO;
import VO.StockDetailVO;
import presentation.stockcheckui.selectPanel;
import web.bl.benchImpl.BenchImpl;
import web.bl.businessImpl.BusinessImpl;
import web.blservice.benchInfo.BenchInfo;
import web.blservice.businessInfo.BusinessInfo;

public class StockAnalyze {

	/**
	 * 
	 * 综合分析模块 综合下面五方面，给出总的评论与得分
	 * 
	 */
	public void comprehensiveAnalyze(StockDetailVO stockDetailVO) {
		double total_points=0;
		
		total_points+=technicalAnalyze(stockDetailVO);
		total_points+=benchAnalyze(stockDetailVO);
		total_points+=businessAnalyze(stockDetailVO);
		total_points+=basicAnalyze(stockDetailVO);
		total_points+=inflowsAnalyze(stockDetailVO);
		
		Analyze_ResultVO analyze_ResultVO=stockDetailVO.getAnalyze_ResultVO();
		analyze_ResultVO .setScore_of_comprehensive_analyze(total_points);
		
	}

	/**
	 * 技术分析模块（日k线图、均线图、成交量、RSI）
	 * 
	 * 日k线图： 最新成交价与支撑线和阻力线比较 
	 * 均线图：（移动平均线，主要用MA10和MA30）
	 * 当证券价格上涨，高于其移动平均线，则产生购买信号。(格兰维尔法则) 当证券价格下跌，低于其移动平均线，则产生出售信号。
	 * -短期移动平均线从下向上突破中长期移动平均线，形成黄金交叉，预示股价将上涨；
	 * -短期移动平均线从上向下跌破中长期移动平均线，形成死亡交叉，预示股价将下跌；
	 * -在上升行情进入稳定期，5日、10日、30日移动平均线从上而下依次顺序排列，向右上方移动，称为多头排列。预示股价将大幅上涨。
	 * -在下跌行情中，5日、10日、30日移动平均线自下而上依次顺序排列，向右下方移动，称为空头排列，预示股价将大幅下跌。
	 * -移动平均线由上升转为下降出现最高点，和由下降转为上升出现最低点时，是移动平均线的转折点。预示股价走势将发生反转。
	 * -当MA10由上升移动而向右下方反折下移时，MA30却仍向右上方移动，表示此段下跌是多头市场的技术性回档，涨势并未结束。
	 *  成交量：
	 * 最新成交量与MA5和MA10比较，判断近期市场萎缩还是活跃。
	 *  RSI：（相对强弱指数，目前用RSI6、RSI12和RSI24）
	 * 根据一定时期内上涨点数和涨跌点数之和的比率制作出的一种技术曲线。能够反映出市场在一定时期内的景气程度，适合做短线差价操作。
	 * 简化计算公式：100×n天内收市价上涨数之和÷(n天内收市价上涨数之和+n天内收市价下跌数之和）。
	 *  - 顶点及底点 70 及30通常为超买及超卖讯号。
	 *   RSI值市场特征投资操作 80—100极强卖出 50—80强买入 20—50弱观望 0—20极弱买入
	 * 短期RSI在20以下水平，由下往上交叉长期RSI，为买进信号。 短期RSI在80以上水平，由上往下交叉长期RSI，为卖出信号。
	 * 价格一波比一波低，相反的，RSI却一波比一波高时，价格很容易反转上涨。 
	 * 价格一波比一波高，相反的，RSI却一波比一波低时，价格很容易反转下跌。
	 * RSI在50以下为弱势区，50以上为强势区。 由下向上突破50线为由弱转强，由上向下突破50线为由强转弱。 一般认为RSI在50以上准确性较高。
	 * 
	 */
	public double technicalAnalyze(StockDetailVO stockDetailVO) {
		double score=10;
		UpToDateStockPO upToDateStockPO=stockDetailVO.getUpToDateMessage();
		Analyze_TechnicalVO analyze_TechnicalVO=stockDetailVO.getAnalyze_TechnicalVO();
		Analyze_BasicItemsVO basicItemsVO=stockDetailVO.getAnalyze_BasicItemsVO();
		double rise_fall=basicItemsVO.getUps_and_downs();//涨跌幅
		double quantity_relative_ratio=upToDateStockPO.getQuantity_relative_ratio();//量比
		double[] closes=analyze_TechnicalVO.getClose();
		double[] volumes=analyze_TechnicalVO.getVolume();
		int length=closes.length;//数据长度
		double[] price_MA=new double[3];
		double[] volume_MA=new double[3];
		double[] RSIs=new double[3];
		price_MA[0]=upToDateStockPO.getNow();//最新价
		volume_MA[0]=volumes[0];//最新成交量
		double resistance=Double.MIN_VALUE;//阻力线
		double support=Double.MAX_VALUE;//支撑线
		
		String result="";
		for (int i = 0; i < length/6; i++) {
			if(closes[i]>resistance){
				resistance=closes[i];
			}
			if(closes[i]<support){
				support=closes[i];
			}
		}
		if(price_MA[0]>resistance){
			result+="最新成交价突破短期阻力线";
			score+=2;
		}else if(price_MA[0]<support){
			result+="最新成交价跌破短期支撑线";
			score-=2;
		}else{
			result+="最新成交价处于短期支撑线和阻力线之间";
		}
		
		for (int i = length/6; i < length; i++) {
			if(closes[i]>resistance){
				resistance=closes[i];
			}
			if(closes[i]<support){
				support=closes[i];
			}
		}
		if(price_MA[0]>resistance){
			result+="，突破长期阻力线";
			score+=2;
		}else if(price_MA[0]<support){
			result+="，跌破长期支撑线";
			score-=2;
		}else{
			result+="，处于长期支撑线和阻力线之间";
		}
		result+="。\n";
		price_MA[1]=0;
		volume_MA[1]=0;
		for (int i = 0; i < 10; i++) {
			price_MA[1]+=closes[i];
			volume_MA[1]+=volumes[i];
		}
		price_MA[2]=price_MA[1];
		volume_MA[2]=volume_MA[1];
		price_MA[1]/=10;
		volume_MA[1]/=10;		
		for (int i = 10; i < 30; i++) {
			price_MA[2]+=closes[i];
			volume_MA[2]+=volumes[i];
		}
		price_MA[2]/=30;
		volume_MA[2]/=30;
		
		if(rise_fall>0&&price_MA[0]>price_MA[1]){
			result+="股票价格上涨，突破短期移动平均线，产生购买信号，建议买入。\n";
			score+=2;
		}
		if(rise_fall<0&&price_MA[0]<price_MA[1]){
			result+="股票价格下跌，跌破短期移动平均线，产生出售信号，建议卖出。\n";
			score-=2;
		}
		if(quantity_relative_ratio>1&&volume_MA[0]>volume_MA[1]){
			result+="成交量上升，突破短期移动平均线，交易市场将日渐活跃。\n";
			score+=2;
		}
		if(quantity_relative_ratio<1&&volume_MA[0]<volume_MA[1]){
			result+="成交量下跌跌，跌破短期移动平均线，交易市场将日渐萎靡。\n";
			score-=2;
		}
		
		String rsi_message[]={"低于25，产生极弱买入信号"," 位于25—50之间，产生弱观望信号",
				"位于50—75之间，产生强买入信号","高于75，产生极强卖出信号" 
				};
		
		RSIs[0]=getRSI(6, closes);
		RSIs[1]=getRSI(12, closes);
		RSIs[2]=getRSI(24, closes);
		int index=(int) (RSIs[0]/25);
		result+="短期RSI"+rsi_message[index];
		score+=index-1;
		
		analyze_TechnicalVO.setPrice_MA(price_MA);
		analyze_TechnicalVO.setVolume_MA(volume_MA);
		analyze_TechnicalVO.setRSI(RSIs);
		

		Analyze_ResultVO analyze_ResultVO=stockDetailVO.getAnalyze_ResultVO();
		analyze_ResultVO.setResult_of_technical_analyze(result);
		analyze_ResultVO.setScore_of_technical_analyze(score);
		
		return score;
		
	}

	private double getRSI(int count,double[] closes){
		double risesum = 0;
		double declinesum = 0;
		for (int i = 0; i < count; i++) {
		
			double difference = closes[i] - closes[i+1];
				if (difference > 0) {
					risesum += difference;
				} else {
					declinesum -= difference;
				}
			
		}
        return (100 * risesum / (risesum + declinesum));
	}
	
	
	/**
	 * 大盘对比分析模块 一：涨跌率历史趋势走势图比较 二：与大盘总体水平比较：涨跌率、换手率、量比
	 * 
	 */

	public double benchAnalyze(StockDetailVO stockDetailVO) {
		//    短期期7分/中期7分/长期6分
		double score[] = {4,3,3};
		// 得到沪深300大盘历史数据
		BenchInfo benchInfo = new BenchImpl();
		BenchVO benchVO = benchInfo.getStockMarket("沪深300");

		double[] rise_falls = stockDetailVO.getRise_fallList();
		int size = rise_falls.length;
		double[] bench_close = new double[size + 1];
		double[] bench_rise_falls = new double[size];
		String[][] historyData = benchVO.getData();
		for (int i = 0; i < size + 1; i++) {
			bench_close[i] = Double.parseDouble(historyData[i][4]);
		}
		for (int i = 0; i < size; i++) {
			bench_rise_falls[i] = (bench_close[i] - bench_close[i + 1]) / bench_close[i + 1];
		}
		stockDetailVO.setBench_rise_fallList(bench_rise_falls);

		UpToDateStockPO upToDateStockPO = stockDetailVO.getUpToDateMessage();
		Analyze_BasicItemsVO analyze_BasicItemsVO = stockDetailVO.getAnalyze_BasicItemsVO();



		String result = "短期表现来看，";
		if (analyze_BasicItemsVO.getUps_and_downs() > benchVO.getRise_fall_percent()) {
			result += "涨势优于大盘总体涨势。\n";
			score[0]+=3;
		}else{
			result += "涨势劣于大盘总体涨势。\n";
			score[0]-=3;
		}
				
		double stock_avg = 0;
		double bench_avg = 0;
		int higher=0;
		int length = size / 6;

		for (int i = 0; i < length; i++) {
			if(rise_falls[i]>bench_rise_falls[i]){
				higher++;
			}
			stock_avg += rise_falls[i];
			bench_avg += bench_rise_falls[i];
		}
		stock_avg /= length;
		bench_avg /= length;
		
		result = "中期表现来看，";
		if(higher>=length/2){
			result += "涨幅多数高于大盘，";
			score[1]+=2;
		}else{
			result += "涨幅多数低于大盘，";
			score[1]-=2;
		}
		if (analyze_BasicItemsVO.getUps_and_downs() > benchVO.getRise_fall_percent()) {
			result += "总体涨势优于大盘。\n";
			score[1]+=2;
		}else{
			result += "总体涨势劣于大盘。\n";
			score[1]-=2;
		}
		
		stock_avg = 0;
		bench_avg = 0;
		higher=0;

		for (int i = 0; i < size; i++) {
			if(rise_falls[i]>bench_rise_falls[i]){
				higher++;
			}
			stock_avg += rise_falls[i];
			bench_avg += bench_rise_falls[i];
		}
		stock_avg /= size;
		bench_avg /= size;
		
		result = "长期表现来看，";
		if(higher>=size/2){
			result += "涨幅多数高于大盘，";
			score[2]+=2;
		}else{
			result += "涨幅多数低于大盘，";
			score[2]-=2;
		}
		if (analyze_BasicItemsVO.getUps_and_downs() > benchVO.getRise_fall_percent()) {
			result += "总体涨势优于大盘。\n";
			score[2]+=1;
		}else{
			result += "总体涨势劣于大盘。\n";
			score[2]-=1;
		}
		
		Analyze_ResultVO analyze_ResultVO=stockDetailVO.getAnalyze_ResultVO();
		analyze_ResultVO.setResult_of_bench_analyze(result);
		double sumScore=score[0]+score[1]+score[2];
		analyze_ResultVO.setScore_of_bench_analyze(sumScore);
		
		return sumScore;
	}

	/**
	 * 行业对比分析模块 对比包括：
	 *  一、与行业总体水平对比（平均价格、涨跌率、换手率）
	 *  二、涨跌幅在同行业所有公司中的排名
	 *  三、与行业涨跌率历史趋势走势图比较
	 * 
	 */
	public double businessAnalyze(StockDetailVO stockDetailVO) {
          //	    短期期12分/中期4分/长期4分
		double score[] = {6,2,2};
		
		UpToDateStockPO upToDateMessage = stockDetailVO.getUpToDateMessage();
		Analyze_BasicItemsVO analyze_BasicItemsVO = stockDetailVO.getAnalyze_BasicItemsVO();

		// 得到所属行业的vo
		BusinessInfo businessInfo = new BusinessImpl();
		String businessName=upToDateMessage.getIndustry();
		BusinessVO businessVO = businessInfo.getBusiness(businessName);
		//行业总体最新信息
		industriesPO businessMessage=businessVO.getUptodate_message();
		//行业所有公司最新信息
		ArrayList<BusinessItemVO> businessItemVOs=businessVO.getBusinessItemVOs();
		int companyNum=businessItemVOs.size();
		double rank=0;
		for (BusinessItemVO businessItemVO : businessItemVOs) {
			rank++;
			if(businessItemVO.getStockId().equals(upToDateMessage.getStockId())){
				break;
			}
		}
		rank/=companyNum;
				
		// 设置所属行业VO
		stockDetailVO.setBusinessVO(businessVO);
		
		//与行业总体水平对比
		String result = "短期表现来看，";
		if (analyze_BasicItemsVO.getUps_and_downs() > businessMessage.getRise_fall()) {
			result += "涨势优于行业总体涨势,";
			score[0]+=3;
		}else{
			result += "涨势劣于行业总体涨势,";
			score[0]-=3;
		}
		
		result+="涨幅较于同行业其他公司，位于";
		score[0]+=rank*6-3;
		if (rank<0.33) {
			result += "前列。\n";
		}else if (rank>0.67) {
			result += "后列。\n";
		}else{
			result += "中游。\n";
		}
		
		Analyze_ResultVO analyze_ResultVO=stockDetailVO.getAnalyze_ResultVO();
		analyze_ResultVO.setResult_of_business_analyze(result);
		double sumScore=score[0]+score[1]+score[2];
		analyze_ResultVO.setScore_of_business_analyze(sumScore);
		
		return sumScore;
	}

	/**
	 * 基本分析模块
	 * 
	 * 基本项包括： 
	 * 成交价稳定性：近30个交易日成交价标准差/均值-------风险性，越接近1，风险性越小
	 * 量比：最新量比---------越大流入的资金越多，活跃度越高 
	 * 换手率：近10个交易日换手率均值-------越大活跃度越高
	 *       熊市不低于3%的换手算正常，平衡市不低于5%，牛市不低于8%
	 * 涨跌幅：最新股价涨跌幅 
	 * 市净率：最新市净率---pb（股票价格与每股净资产的比例）越低的股票，安全系数越高。通常越小，投资价值越高。
	 *         但市价低于帐面价值时，要考虑企业经营情况。 
	 * 市盈率：最新市盈率---pe（股票价格和每股收益的比例）越小越被低估，表明潜在上升空间越大
	 *  <0：指该公司盈利为负（因盈利为负，计算市盈率没有意义，所以一般软件显示为“—”）
	 *  0-13 ：即价值被低估 
	 *  14-20：即正常水平
	 *  21-28：即价值被高估
	 *  28+ ：反映股市出现投机性泡沫 
	 *  投资界常常会在牛市推崇市盈率，到熊市则往往拿市净率说事
	 * 
	 */

	public double basicAnalyze(StockDetailVO stockDetailVO) {
       
		Analyze_BasicItemsVO analyze_BasicItemsVO=stockDetailVO.getAnalyze_BasicItemsVO();
		double quantity_relative_ratio=analyze_BasicItemsVO.getQuantity_relative_ratio();  //量比
		double priceStability=analyze_BasicItemsVO.getPriceStability();   //股价稳定性
		double turnOver=analyze_BasicItemsVO.getTurnOver();   //换手率
		double ups_and_downs=analyze_BasicItemsVO.getUps_and_downs();  //涨跌幅
		double pe=analyze_BasicItemsVO.getPe();  //市盈率
		double pb=analyze_BasicItemsVO.getPb();  //市净率
		
		String result="就该股票近期表现来看：价格波动";
		if(priceStability>0.7){
			result+="较小，投资风险相对较低；";			
		}else{
			result+="较大，投资风险相对较高；";	
		}
		if(turnOver>5){
			result+="换手频繁，表现活跃。";
		}else if(turnOver<2){
			result+="换手不多，表现低迷。";
		}else{
			result+="换手频率正常。";
		}
		
		result="\n就最新信息显示：今日该股";
		
		if(ups_and_downs>3){
			result+="股价上涨明显；";			
		}else if(ups_and_downs<-2){
			result+="股价下跌明显；";			
		}else {
			result+="股价正常波动；";	
		}
		if(quantity_relative_ratio>1.5){
			result+="交易市场活跃，成交量大幅提升；";			
		}else if(quantity_relative_ratio<0.8){
			result+="交易市场萎缩，成交量大幅下降；";			
		}else {
			result+="交易市场正常，成交量轻微波动；";	
		}
		
		result="\n就股票价值来看：";
		String[] pe_message={"公司盈利为负","股票价值被低估 ","股票价值处于正常水平","股票价值被高估","股市出现投机性泡沫"};
		int index=(int) (pe/12);
		index++;
		if(index>4){
			index=4;
		}
		result+=pe_message[index];

		 result=";\n就投资价值来看：";
		if(pb>5){
			result+="该股安全系数低，投资价值与风险性并举。";
		}else if(pb<1){
			result+="出现破净现象，建议参考企业经营情况谨慎考虑。";
		}else{
			result+="该股安全系数较高，有较高提升空间。";
		}
		Analyze_ResultVO analyze_ResultVO=stockDetailVO.getAnalyze_ResultVO();
		analyze_ResultVO.setResult_of_basic_analyze(result);
		double sumScore=quantity_relative_ratio-4-index+pb+ups_and_downs+turnOver+priceStability*3;
		if(sumScore>20){
			sumScore=20;
		}
		analyze_ResultVO.setScore_of_basic_analyze(sumScore);
		return sumScore;
	}

	/**
	 * 资金流向分析模块
	 * 
	 * DDX/DDY/DDZ都是越大越好
	 * 
	 * DDX指标实际上指的就是大单动向，通常我们将委托单的大小， 反映不同资金能力的投资者的交易方向称为DDX指标。
	 * DDX指标是一项以Level-2的逐单分析为基础的短中线兼顾的技术指标。
	 * DDX指标在形态上用红绿柱来表示，红柱表示大单买入量较大，绿柱表示大单卖出量较大， 通常情况下，DDX指标翻红是买入的好时机。
	 * 
	 * DDY红绿柱线是每日卖出单数和买入单数的差占持仓人数的比例（估算值）， DDY是单数差的60日平滑累加值
	 *
	 * DDZ即大单差分指标红色彩带表示了大资金买入强度，色带越宽、 越高表示买入强度越大。 当彩带突然升高放宽时往往预示短线将快速上涨。
	 * 
	 * 单差：
	 * 
	 * 特大单差：（单位：%）（特大买入-特大卖出）（数值越大说明大机构买入越多，数值越大越好）
	 * 大单差：（单位：%）（大单买入-大单卖出）（数值越大说明中机构买入越多，数值越大越好）
	 * 中单差：（单位：%）（中单买入-中单卖出）（数值越大说明中散户买入越多，数值越负越好）
	 * 小单差：（单位：%）（小单买入-小单卖出）（数值越大说明小散户买入越多，数值越负越好）
	 */
	public double inflowsAnalyze(StockDetailVO stockDetailVO) {
		String result="就该股票资金流向来看：";
		UpToDateStockPO upToDateMessage = stockDetailVO.getUpToDateMessage();
		double ddx=upToDateMessage.getDdx();
		double ddy=upToDateMessage.getDdy();
		double ddz=upToDateMessage.getDdz();
		double gaps[]=new double[4];
		gaps[0]=upToDateMessage.getExtraGap();
		gaps[1]=upToDateMessage.getLargeGap();
		gaps[2]=upToDateMessage.getMediumGap();
		gaps[3]=upToDateMessage.getSmallGap();
		
		if(ddx>0.3){
			result+="大单买入量较大，建议买入；";
		}else if(ddx<-0.22){
			result+="大单卖出量较大，不建议买入；";
		}
		if(ddz>10){
			result+="大资金买入强度高，预计短线将快速上涨。";
		}else if(ddz<-0.22){
			result+="大资金买入强度低，预计短线将开始下降。";
		}
		
		double score=ddx+ddy+ddz/5+gaps[0]+gaps[1]-gaps[2]-gaps[3];
		if(score<0){
			score=0;
		}else if(score>20){
			score=20;
		}
		Analyze_ResultVO analyze_ResultVO=stockDetailVO.getAnalyze_ResultVO();
		analyze_ResultVO.setResult_of_inflows_analyze(result);
		analyze_ResultVO.setScore_of_inflows_analyze(score);
		return score;
	}
}
