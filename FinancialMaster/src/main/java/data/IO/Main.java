package data.IO;

import java.io.File;
import java.util.ArrayList;

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
		ArrayList<String> arrayList=FileManager.ReadFile("src/main/resources/Data/file2.txt");
		for (String string : arrayList) {
			System.out.println(string);
		}
		try {
			File file2=new File("src/main/resources/Data/yes.txt");
			file2.createNewFile();
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    public static void main(String[] args) {
        new Main().renameFile("src/main/resources/Data/file1.txt", "src/main/resources/Data/file2.txt");
    }
}
