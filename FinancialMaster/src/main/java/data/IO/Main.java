package data.IO;

import java.io.File;
import java.util.ArrayList;

import servlet.factory.InitFactoryServlet;

public class Main {

    public void renameFile(String file, String toFile) {

        File toBeRenamed = new File(file);
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {

            System.out.println("File does not exist: " + file);
            return;
        }

        File newFile = new File(toFile);

        if (toBeRenamed.renameTo(newFile)) {
            System.out.println("File has been renamed.");
        } else {
            System.out.println("Error renmaing file");
        }
		ArrayList<String> arrayList=FileManager.ReadFile("Data/file2.txt");
		for (String string : arrayList) {
			System.out.println(string);
		}
		try {
			String path=InitFactoryServlet.getPath();
			File file2=new File(path+"Data/yes.txt");
			file2.createNewFile();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    public static void main(String[] args) {
        new Main().renameFile("Data/file1.txt", "Data/file2.txt");
    }
}
