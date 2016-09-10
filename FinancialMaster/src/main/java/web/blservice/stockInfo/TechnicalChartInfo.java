package web.blservice.stockInfo;

import org.junit.validator.PublicClassValidator;

import VO.TechnicalChart.BIAS_VO;
import VO.TechnicalChart.CCI_VO;
import VO.TechnicalChart.DMI_VO;
import VO.TechnicalChart.KDJ_VO;
import VO.TechnicalChart.MACD_VO;
import VO.TechnicalChart.ROC_VO;
import VO.TechnicalChart.RSI_VO;
import VO.TechnicalChart.WR_VO;

public interface TechnicalChartInfo {

	
	/**
	 * 所有数据都是从最新到最旧
	 */
	
	//BIAS	
	public BIAS_VO getBIAS(double close[]); 
	
	//CCI
	
	public CCI_VO getCCI(double close[],double high[],double low[]);
	
	//DMI
	
	public DMI_VO getDMI(double close[],double high[],double low[]);
	
	//KDJ
	
	public KDJ_VO getKDJ(double[] close, double[] high, double[] low);
	
	//MACD
	
	public MACD_VO getMACD(double close[]); 
	
	//OBV
	
	public double[] getOBV(double[] close, double[] high, double[] low,double[] volume);
	
	//ROC
	
	public ROC_VO getROC(double close[]);
	
	//W%R
	public WR_VO getWR(double[] close, double[] high, double[] low);
	
	//RSI
	public RSI_VO getRSI(double[] close);
	
	
}
