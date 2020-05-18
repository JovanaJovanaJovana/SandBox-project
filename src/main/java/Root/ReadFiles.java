package Root;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadFiles {
	
	public static Map<String, String> readLocators() {
		
		Map<String, String> locators = new HashMap<>();
		File myObj = new File("locators.txt"); 
		Scanner myReader;
		
		try {
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String values[] = data.split("`");
				locators.put(values[0], values[1]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occured!");
			e.printStackTrace();
		}

		return locators;

	}

}
