package web.blservice.benchInfo;

import ENUM.ManageState;
import VO.BenchVO;

public interface BenchUpdateInfo {

	//更新大盘的最新信息
	public ManageState update(BenchVO benchVO,String benchName);
	
	//得到大盘开闭盘信息
	public String getState();
}
