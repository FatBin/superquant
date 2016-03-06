package PO;
import java.util.ArrayList;

public class codeNamePO {
	//所有股票代号
	private ArrayList<String> result;
	public codeNamePO(ArrayList<String> list){
		result=new ArrayList<String>();
		setResult(list);
	}
	public ArrayList<String> getResult() {
		return result;
	}
	private void setResult(ArrayList<String> list) {
		for(int i=0;i<list.size();i++){
			result.add(list.get(i));
		}
	}
	
	
}
