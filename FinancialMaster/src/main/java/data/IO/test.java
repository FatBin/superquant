package data.IO;

import java.io.FileWriter;
import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> arrayList=FileManager.ReadFile("Data/ObservedStock.txt");
		arrayList.set(0, "1");
		FileManager.WriteFile(arrayList, "Data/ObservedStock.txt", false);
		arrayList=FileManager.ReadFile("Data/ObservedStock.txt");
		for (String string : arrayList) {
			System.out.println(string);
		}
	}

}
