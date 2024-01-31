package demo;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetup {

	public static WebDriver driver;
	public static WebDriver getWebDriver() {
		
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Navigating to google 
		driver.get("https://google.com");
		driver.manage().window().maximize();
		return driver;
		
	}
}
