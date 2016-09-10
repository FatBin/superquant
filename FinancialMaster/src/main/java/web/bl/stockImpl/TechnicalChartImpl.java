package web.bl.stockImpl;

import VO.TechnicalChart.BIAS_VO;
import VO.TechnicalChart.CCI_VO;
import VO.TechnicalChart.DMI_VO;
import VO.TechnicalChart.KDJ_VO;
import VO.TechnicalChart.MACD_VO;
import VO.TechnicalChart.ROC_VO;
import VO.TechnicalChart.RSI_VO;
import VO.TechnicalChart.WR_VO;
import web.blservice.stockInfo.TechnicalChartInfo;

public class TechnicalChartImpl implements TechnicalChartInfo {

	@Override
	public BIAS_VO getBIAS(double[] close) {
		
		double BIAS6[]=getBIAShelper(6, close);
		double BIAS12[]=getBIAShelper(12, close);
		double BIAS24[]=getBIAShelper(24, close);
		
		BIAS_VO bias_VO=new BIAS_VO();
		bias_VO.setBIAS6(BIAS6);
		bias_VO.setBIAS12(BIAS12);
		bias_VO.setBIAS24(BIAS24);
		
		return bias_VO;
	}

	private double[] getBIAShelper(int n,double[] close){
		double result[] =new double[close.length-n+1];
		
		for (int i = 0; i < result.length; i++) {
			double ma=0;
			for (int j = 0; j < n; j++) {
				ma+=close[i+j];
			}
			ma/=n;
			
			result[i]=(close[i]-ma)/ma*100;
		}
		
		return result;
	}

	
	@Override
	public CCI_VO getCCI(double[] close, double[] high, double[] low) {

		 int length=close.length-6;
		 double cci[]=new double[length];
		 double tp,ma,md;
		 for (int i = 0; i < length; i++) {
			tp=(close[i]+high[i]+low[i])/3;
			ma=0;
			for (int j = 0; j < 7; j++) {
				ma+=close[i+j];			
			}
			ma/=7;
			md=0;
			for (int j = 0; j < 7; j++) {
				md+=Math.abs(close[i+j]-ma);			
			}
			md/=7;
			
			cci[i]=(tp-ma)/md/0.015;
		}
		 
		length-=5;
		double macci[]=new double[length];
		
		for (int i = 0; i < length; i++) {
			macci[i]=0;
			for (int j = 0; j < 6; j++) {
				macci[i]+=cci[i+j];
			}
			macci[i]/=6;
		}
		CCI_VO cci_VO=new CCI_VO();
		cci_VO.setCci(cci);
		cci_VO.setMacci(macci);
		return cci_VO;
	}

	
	@Override
	public DMI_VO getDMI(double[] close, double[] high, double[] low) {
		
		 int length=close.length;
		 double PDM[]=new double[length-1];
		 double MDM[]=new double[length-1];
		 double TR[]=new double[length-1];
		 
		 double A[]=new double[3];
		 for (int i = 0; i < length-1; i++) {
			PDM[i]=high[i]>high[i+1]?high[i]-high[i+1]:0;
			MDM[i]=low[i]>low[i+1]?low[i]-low[i+1]:0;
			if(PDM[i]!=0&&PDM[i]==MDM[i]){
				PDM[i]=0;
				MDM[i]=0;
			}
			TR[i]=0;
			A[0]=Math.abs(high[i]-low[i+1]);
			A[1]=Math.abs(high[i]-close[i+1]);
			A[2]=Math.abs(low[i]-close[i+1]);
			for (int j = 0; j < 3; j++) {
				if(A[j]>TR[i]){
					TR[i]=A[j];
				}
			}					
		}	 
		 
		 double Psum=0;
		 double Msum=0;
		 double Tsum=0;
		 for (int i = 0; i < 12; i++) {
			 Psum+=PDM[i];
			 Msum+=MDM[i];
		}
		 double PDI12[]=new double[length-12];
		 double MDI12[]=new double[length-12];
		 PDI12[0]=Tsum==0?0:Psum/(12*Tsum);
		 MDI12[0]=Tsum==0?0:Msum/(12*Tsum);
		 for (int i = 1; i < length-12; i++) {
			 Tsum=-TR[i-1]+TR[i+11];
			PDI12[i]=Tsum==0?0:(Psum-PDM[i-1]+PDM[i+11])/(12*Tsum);
			MDI12[i]=Tsum==0?0:(Msum-MDM[i-1]+MDM[i+11])/(12*Tsum);
		}
		 
		 double ADX12[]=new double[length-12];
		 for (int i = 0; i < length-12; i++) {
			ADX12[i]=PDI12[i]==0?0:(Math.abs(PDI12[i]-MDI12[i])/(PDI12[i]+MDI12[i]))*100;
		}

		 double ADXR[]=new double[length-13];
		 for (int i = 0; i < length-13; i++) {
			 ADXR[i]=(ADX12[i]+ADX12[i+1])/2;
		 }		
		 DMI_VO dmi_VO=new DMI_VO();
		 dmi_VO.setPDI12(PDI12);
		 dmi_VO.setMDI12(MDI12);
		 dmi_VO.setADX12(ADX12);
		 dmi_VO.setADXR(ADXR);		 		 
		 return dmi_VO;
	}


	@Override
	public KDJ_VO getKDJ(double[] close, double[] high, double[] low) {
		
		
		 int result_length=close.length-8;
		 double rsv[]=new double[result_length];
		 double k[]=new double[result_length];
		 double d[]=new double[result_length];
		 double j[]=new double[result_length];
		 
		 for (int i = 0; i < result_length; i++) {
			double h9=Double.MIN_VALUE;
			double l9=Double.MAX_VALUE;
			for (int l = 0; l < 9; l++) {
				if(high[i+l]>h9){
					h9=high[i+l];
				}
				if(low[i+l]<l9){
					l9=low[i+l];
				}
			}
			
			rsv[i]=(close[i]-l9)/(h9-l9)*100;			
			
		}	 
		 
	    k[result_length-1]=50.0*2/3+rsv[result_length-1]/3;
	    d[result_length-1]=50.0*2/3+k[result_length-1]/3;
	    j[result_length-1]=3*k[result_length-1]-2*d[result_length-1];
	    
		for (int i = result_length-2; i >=0; i--) {
			k[i]=k[i+1]*2/3+rsv[i]/3;
		    d[i]=d[i+1]*2/3+k[i]/3;
		    j[i]=3*k[i]-2*d[i];
			
		}
		
		KDJ_VO kdj_VO=new KDJ_VO();
		kdj_VO.setK(k);
		kdj_VO.setD(d);
		kdj_VO.setJ(j);
		return kdj_VO;
	}


	@Override
	public MACD_VO getMACD(double[] close) {
		int length=close.length;
		double EMA12[]=getEMA(close,12);
		 double EMA26[]=getEMA(close,26);
		 
		 length=EMA26.length;
		 double DIFs[]=new double[length];
		 for (int i = 0; i < length; i++) {
			DIFs[i]=EMA12[i]-EMA26[i];
		 }
		 double DEAs[]=getEMA(DIFs, 9);
		 length=DEAs.length;
		 double DIFFs[]=new double[length];
		 for (int i = 0; i < length; i++) {
			DIFFs[i]=DIFs[i]-DEAs[i];
		}
		 
		MACD_VO macd_VO=new MACD_VO();
		macd_VO.setDIFs(DIFs);
		macd_VO.setDEAs(DEAs);
		macd_VO.setDIFFs(DIFFs);
		return macd_VO;
	}
	
	private double[] getEMA(double[] close,int dayCount){
		int size=close.length-dayCount+1;
		double result[]=new double[size];
		for (int i = 0; i < size; i++) {
			double sum=0;
			for (int j = 0; j < dayCount; j++) {
				sum+=close[i+j];
			}
			result[i]=sum/dayCount;
		}
		return result;
	}


	@Override
	public double[] getOBV(double[] close, double[] high, double[] low, double[] volume) {
		int length=close.length;
		double obv[]=new double[length];
		 for(int i=0;i<length;i++){	
	         obv[i]=((close[i]-low[i])-(high[i]-close[i]))/(high[i]-low[i])*volume[i];
		 }
		return obv;
	}


	@Override
	public ROC_VO getROC(double[] close) {
		
		int length=close.length;
		int N=12,M=6,X=9;
		 
		 int result_length=length-N+1;
		 double roc12[]=new double[result_length];
		 double rocMA[]=new double[result_length-M+1];
		 double rocEMA[]=new double[result_length-M+1];
		 
		 for (int i = result_length-1; i >=0; i--) {
		
			roc12[i]=(close[i]-close[i+N-1])/close[i+N-1];		
		}	 
		 
		 for (int i = 0; i < rocEMA.length; i++) {
			rocMA[i]=0;
			for (int j = 0; j < M; j++) {
				rocMA[i]+=roc12[i+j];
			}
			rocMA[i]/=M;		
			rocEMA[i]=(2*roc12[i]+(X-1)*roc12[i+1])/(X+1);
		}
		
		ROC_VO roc_VO=new ROC_VO();
		roc_VO.setRoc12(roc12);
		roc_VO.setRocMA(rocMA);
		roc_VO.setRocEMA(rocEMA);
		return roc_VO;
	}


	@Override
	public WR_VO getWR(double[] close, double[] high, double[] low) {
		double wr9[]=getWRhelper(9, close, high, low);
		double wr14[]=getWRhelper(14, close, high, low);
		double wr20[]=getWRhelper(20, close, high, low);
		WR_VO wr_VO=new WR_VO();
		wr_VO.setWr9(wr9);
		wr_VO.setWr14(wr14);
		wr_VO.setWr20(wr20);
		return wr_VO;
	}
		
	private double[] getWRhelper(int N,double close[],double high[],double low[]) {
		int length=close.length-N+1;
		double[] result=new double[length];
		
		 for (int i = 0; i < length; i++) {
				double hn=Double.MIN_VALUE;
				double ln=Double.MAX_VALUE;
				for (int l = 0; l < N; l++) {
					if(high[i+l]>hn){
						hn=high[i+l];
					}
					if(low[i+l]<ln){
						ln=low[i+l];
					}
				}
				
				result[i]=100-(close[i]-ln)/(hn-ln)*100;			
				
			}	
				
		return result;
		
	}

	
	@Override
	public RSI_VO getRSI(double[] close) {
		double rsi6[]=getRSIhelper(6, close);
		double rsi12[]=getRSIhelper(12, close);
		double rsi24[]=getRSIhelper(24, close);
		RSI_VO rsi_VO=new RSI_VO();
		rsi_VO.setRsi6(rsi6);
		rsi_VO.setRsi12(rsi12);
		rsi_VO.setRsi24(rsi24);
		return rsi_VO;
	}
	
	private double[] getRSIhelper(int count,double close[]) {
		int length=close.length-count;
		double result[]=new double[length];
		for (int i=0; i < length; i++) {
			double risesum = 0;
			double declinesum = 0;
			for (int j = 0; j < count; j++) {
				double difference = close[i+j] - close[i + j + 1];
				if (difference > 0) {
					risesum += difference;
				} else {
					declinesum -= difference;
				}
			}
			result[i]=(100 * risesum / (risesum + declinesum));
		}
		return result;		
	}
	
}
