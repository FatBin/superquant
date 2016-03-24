package data.IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileManager {
	// 读文件，参数为文件地址
	public static ArrayList<String> ReadFile(String path) {
		ArrayList<String> list = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String temp = "";
			while ((temp = br.readLine()) != null) {
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 写文件，参数为文件地址和是否覆盖，false为覆盖,true为最后加入
	public static void WriteFile(ArrayList<String> arrayList, String path,
			boolean overwrite) {
		try {
			FileWriter fileWriter = new FileWriter(path, overwrite);
			for (String string : arrayList) {
				fileWriter.write(string+"\n");
			}
			fileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void WriteFile(String string, String path, boolean overwrite) {
		try {
			FileWriter fileWriter = new FileWriter(path, overwrite);
			fileWriter.write(string+"\n");
			fileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
