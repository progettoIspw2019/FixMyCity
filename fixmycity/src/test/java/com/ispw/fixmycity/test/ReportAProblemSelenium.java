package com.ispw.fixmycity.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ReportAProblemSelenium {
	
	String username = "admin";
	String password = "admin";
	
	@Test
	public void testReportProblem() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe"); 
	
	WebDriver driver = new ChromeDriver();
	driver.manage().deleteAllCookies();
	driver.manage().window().setSize(new Dimension(1800, 800));
	driver.manage().window().setPosition(new Point(0, 0));
	driver.get("http://localhost:8080/fixmycity/logout.jsp");

	driver.get("http://localhost:8080/fixmycity/login.jsp");
	driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[1]/div/div/input")).sendKeys(username);
	driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[2]/div/div/input")).sendKeys(password);
	driver.findElement(By.xpath("//*[@id=\"login-box\"]/div/form/div[3]/div/button")).click();
	
	WebElement map = driver.findElement(By.xpath("//*[@id=\"mapid\"]"));
	Actions builder = new Actions(driver);   
	builder.moveToElement(map, 10, -10).click().build().perform();
	Thread.sleep(4000);
	System.out.println("Clicking on add report");
	driver.findElement(By.xpath("//*[@id=\"addRepBtn\"]")).click();
	
	driver.findElement(By.xpath("//*[@id=\"reportProblemFormId\"]/div[1]/div[1]/div/div/input")).click();
	driver.findElement(By.xpath("//*[@id=\"reportProblemFormId\"]/div[1]/div[1]/div/div/input")).sendKeys("Spazzatura ovunque!");
	driver.findElement(By.xpath("//*[@id=\"reportProblemFormId\"]/div[1]/div[2]/div/div/textarea")).click();
	driver.findElement(By.xpath("//*[@id=\"reportProblemFormId\"]/div[1]/div[2]/div/div/textarea")).sendKeys("Pieno di carta a terra.");
	driver.findElement(By.xpath("//*[@id=\"inputReportCategoryId\"]")).click();
	driver.findElement(By.xpath("//*[@id=\"inputReportCategoryId\"]/option[5]")).click();
	driver.findElement(By.xpath("//*[@id=\"reportProblemFormId\"]/div[2]/div/div/button")).click();
	driver.findElement(By.xpath("//*[@id=\"sidebar-wrapper\"]/div[2]/a[3]")).click();
	WebElement report = driver.findElement(By.linkText("Spazzatura ovunque!"));
	assertEquals("Spazzatura ovunque!", report.getText());
	}
}
