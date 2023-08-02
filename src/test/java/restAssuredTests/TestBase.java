package restAssuredTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class TestBase {
	
	public int ResponseStatusCode_200 = 200;
	public int ResponseStatusCode_201 = 201;
	public int ResponseStatusCode_400 = 400;
	public int ResponseStatusCode_404 = 404;
	public int ResponseStatusCode_500 = 500;
	
	
	public Properties prop; 

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/config/config.properties");
			prop.load(ip);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
