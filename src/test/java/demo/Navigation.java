package demo;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import demo.excel;

public class Navigation {
	public static WebDriver driver;
	String parentwindowid;
	String previousTabtitle;
	String file=(System.getProperty("user.dir")+"/src/test/resources/Book1.xlsx");
	
	public void setup() {
		driver=DriverSetup.getWebDriver();
	}
	
	public void searchbox() throws IOException {
		WebElement searchBox=driver.findElement(By.name("q"));
		
		searchBox.sendKeys(excel.getCellData(file, "Sheet1", 1, 0));
		searchBox.sendKeys(Keys.ENTER);
		// TODO Auto-generated method stub
		
	}
	
	public void navigateverify() throws InterruptedException {
		driver.navigate().back();
	    Thread.sleep(2000);
	  
		String currentPage=driver.getTitle();
		System.out.println("current Title after navigating to back:" +currentPage);
		
		driver.navigate().forward();
		//Thread.sleep(2000);
		currentPage=driver.getTitle();
		System.out.println("current Title after navigating to forward:" +currentPage);
		// TODO Auto-generated method stub
		
	}
	
	public void orangehrm() {
		WebElement link=driver.findElement(By.xpath("//h3[normalize-space()='OrangeHRM Demo.']"));
		 //previousTabtitle=driver.findElement(By.xpath("//h3[normalize-space()='OrangeHRM Demo.']")).getText();
		 previousTabtitle=driver.getTitle();
        
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(link).keyUp(Keys.CONTROL).perform();
		
		Set<String> windowid = driver.getWindowHandles();
        List<String>windowList =new ArrayList(windowid);
        parentwindowid = windowList.get(0);
        String childwindowid = windowList.get(1);
        driver.switchTo().window(childwindowid);
		
	}
	
	public void credentials() throws IOException {
		
		driver.findElement(By.xpath("//input[@name='username']" )).sendKeys(excel.getCellData(file, "Sheet1", 1, 1));
		
		
		driver.findElement(By.xpath("//input[@name='password']" )).sendKeys(excel.getCellData(file, "Sheet1", 1, 2));
		
		driver.findElement(By.xpath("//button[@type='submit']" )).click();
		
		
	}
	
	public void logo() {
		driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[1]/a/div[2]/img")).click();
	}
	
	public void contactsales() {
		driver.findElement(By.linkText("Contact Sales")).click();
		
	}
	private void requireddetails() throws InterruptedException, IOException {
		
        driver.findElement(By.xpath("//input[@class='text'][@name='FullName']")).sendKeys( excel.getCellData(file, "Sheet1", 1, 3));
		
		
        driver.findElement(By.xpath("//input[@class='text'][@name='Contact']")).sendKeys( excel.getCellData(file, "Sheet1", 1, 4));
		
		
        driver.findElement(By.xpath("//input[@id='Form_getForm_Email']")).sendKeys( excel.getCellData(file, "Sheet1", 1, 5));
		
		Select sc = new Select(driver.findElement(By.name("Country")));
		sc.selectByValue("India");
		
		Select sc1 = new Select(driver.findElement(By.name("NoOfEmployees")));
		sc1.selectByValue("11 - 15");
	
		
		driver.findElement(By.xpath("//input[@id='Form_getForm_JobTitle']")).sendKeys(excel.getCellData(file, "Sheet1", 1, 6));
		
		driver.findElement(By.xpath("/html/body/div[3]/div/div/section[1]/div[2]/div/div[2]/div/div/form/fieldset/div[7]/div/textarea")).sendKeys(" ");
		
		
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[4]/div[2]/div/a")).click();
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
	
		    WebElement iframecap = driver.findElement(By.xpath("//div[@id='Form_getForm_Captcha_Holder']//iframe[@title='reCAPTCHA']"));
	        driver.switchTo().frame(iframecap);//Switch to the iframe

	        WebElement captcha = driver.findElement(By.xpath("//span[@id=\"recaptcha-anchor\"]"));

	        js.executeScript("arguments[0].click();", captcha);
	        driver.switchTo().defaultContent();
	        Thread.sleep(40000);
	        
		  driver.findElement(By.name("action_submitForm")).click();
		
	}
	
	public void close() {
		driver.close();
		
	}
	public void verification() {
		driver.switchTo().window(parentwindowid);
		  
		    //String expectedTabtitle=driver.findElement(By.xpath("//h3[normalize-space()='OrangeHRM Demo.']")).getText();
		    String expectedTabtitle=driver.getTitle();
		  if(expectedTabtitle.equalsIgnoreCase(previousTabtitle)) {
			  System.out.println("It is navigating to previous tab");
		  }
		  else {
			  System.out.println("It's not navigating to previuos tab");
		  }
		
		
	}
	public void closebrowser() {
		driver.quit();
		
	}

public static void main(String[] args) throws InterruptedException, IOException
{
	
	Navigation hrm = new Navigation();
	
	hrm.setup();
	hrm.searchbox();
	hrm.navigateverify();
	hrm.orangehrm();
	hrm.credentials();
	hrm.logo();
	hrm.contactsales();
	hrm.requireddetails();
	hrm.close();
	hrm.verification();
	hrm.closebrowser();
	
}
}

